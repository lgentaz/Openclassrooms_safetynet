package com.safetynet.alerts.service;

import com.safetynet.alerts.model.JsonDB;
import com.safetynet.alerts.repository.JsonDBRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Service
@Configuration
public class JsonDBService {

    @Autowired private final JsonDBRepository jsonDBRepository;

    public JsonDBService(JsonDBRepository jsonDBRepository) {
        this.jsonDBRepository = jsonDBRepository;
    }

    public Iterable<JsonDB> list() {
        return jsonDBRepository.findAll();
    }

    public JsonDB save(JsonDB jsonDB) {
        return jsonDBRepository.save(jsonDB);
    }
}
