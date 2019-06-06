/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.CharShard;
import com.example.demo.repository.CharShardRepository;
import java.util.Collection;
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
public class CharShardController {

    @Autowired
    private CharShardRepository repository;

    public CharShardController(CharShardRepository repo) {
        this.repository = repo;
    }

    @GetMapping("/getCharShards/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<CharShard> getCharShards(@PathVariable(value = "id") Long char_id) {
        return repository.getCharShards(char_id);
    }
}
