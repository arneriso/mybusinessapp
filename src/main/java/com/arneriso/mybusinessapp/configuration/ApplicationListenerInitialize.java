package com.arneriso.mybusinessapp.configuration;


import com.arneriso.mybusinessapp.Constants;
import com.arneriso.mybusinessapp.domain.Sectors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class ApplicationListenerInitialize implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private MongoOperations mongoOperations;

    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!sectorsInitialized()) {
            mongoOperations.insert(loadSectorsFromResource());
        }
    }

    private Sectors loadSectorsFromResource() {
        Gson gson = new GsonBuilder().create();
        Sectors sectors = gson.fromJson(getStringFromResource("sectors_v1.json"), Sectors.class);
        sectors.setId(Constants.SECTORS_ID);
        return sectors;
    }

    private boolean sectorsInitialized() {
        return mongoOperations.exists(new Query(where("id").is(Constants.SECTORS_ID)), Sectors.class);
    }

    private String getStringFromResource(String resourceName) {
        String result = "";
        try {
            result = new String(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(resourceName)).readAllBytes());

        } catch (IOException ignore) {}
        return result;
    }
}

