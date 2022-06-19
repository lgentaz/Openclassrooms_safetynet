package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.JsonDB;
import com.safetynet.alerts.service.JsonDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class JsonDBController {
    private final JsonDBService jsonDBService;

    public JsonDBController(JsonDBService jsonDBService) {
        this.jsonDBService = jsonDBService;
    }

    @GetMapping("/jsonDB")
    public Iterable<JsonDB> list() {
        return jsonDBService.list();
    }
}
