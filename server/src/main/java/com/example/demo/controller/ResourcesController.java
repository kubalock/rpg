/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Resources;
import com.example.demo.repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grzegorz
 */
@RestController
public class ResourcesController {

    @Autowired
    private ResourcesRepository resourceRepository;

    public ResourcesController(ResourcesRepository resRepository) {
        this.resourceRepository = resRepository;
    }

    @GetMapping("/getCharResources/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Resources getCharResources(@PathVariable(value = "id") Long char_id) {
        Resources resources = resourceRepository.getCharResources(char_id);
        return resources;
    }
}
