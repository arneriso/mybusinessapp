package com.arneriso.mybusinessapp.controllers;

import com.arneriso.mybusinessapp.Constants;
import com.arneriso.mybusinessapp.controllers.exeptions.InternalErrorException;
import com.arneriso.mybusinessapp.domain.BusinessSector;
import com.arneriso.mybusinessapp.domain.Sectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SectorsController {

    @Autowired
    private MongoOperations mongoOperations;

    @GetMapping("api/sectors")
    public ResponseEntity<List<BusinessSector>> getSectors() throws InternalErrorException {
        Sectors sectors = mongoOperations.findById(Constants.SECTORS_ID, Sectors.class);
        if (sectors == null) {
            throw new InternalErrorException("sectors have not been set up");
        } else {
            return new ResponseEntity<>(sectors.getValue(), HttpStatus.OK);
        }
    }
}
