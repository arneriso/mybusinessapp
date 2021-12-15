package com.arneriso.mybusinessapp.controllers;

import com.arneriso.mybusinessapp.controllers.exeptions.InvalidInputException;
import com.arneriso.mybusinessapp.controllers.exeptions.NotFoundException;
import com.arneriso.mybusinessapp.domain.BusinessUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
public class BusinessUserController {

    @Autowired
    private MongoOperations mongoOperations;

    @PostMapping("/api/generate-business-user")
    public ResponseEntity<BusinessUser> generateBusinessUser() {
        BusinessUser newBusinessUser = new BusinessUser();
        newBusinessUser.setSectorIds(new ArrayList<>());
        newBusinessUser.setId(UUID.randomUUID().toString());
        return new ResponseEntity<>(newBusinessUser, HttpStatus.OK);
    }

    @PutMapping("/api/business-user")
    public ResponseEntity<BusinessUser> upsertBusinessUser(@RequestBody BusinessUser businessUser) throws InvalidInputException {
        validateUser(businessUser);
        mongoOperations.save(businessUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/business-user/{id}")
    public ResponseEntity<BusinessUser> getUser(@PathVariable String id) throws NotFoundException {
        BusinessUser result = mongoOperations.findOne(new Query(where("id").is(id)), BusinessUser.class);
        if (result == null) {
            throw new NotFoundException(String.format("user %s not found", id));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private void validateUser(BusinessUser businessUser) throws InvalidInputException {
        if (!businessUser.getAgreementToTerms()) {
            throw new InvalidInputException("agreement has to be accepted");
        }
        if (businessUser.getUserName() == null || businessUser.getUserName().isBlank()) {
            throw new InvalidInputException("user name is mandatory");
        }
        if (businessUser.getId() == null || businessUser.getId().isBlank()) {
            throw new InvalidInputException("user id is mandatory");
        }
        if (businessUser.getSectorIds() == null || businessUser.getSectorIds().isEmpty()) {
            throw new InvalidInputException("at least one sector has to be selected");
        }
    }
}
