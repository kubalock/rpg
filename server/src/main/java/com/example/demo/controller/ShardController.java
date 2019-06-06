/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.repository.ShardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grzegorz
 */
@RestController
public class ShardController {
    
    @Autowired
    private ShardRepository shardRepository;
    
    public ShardController(ShardRepository shardRepository) {
        this.shardRepository = shardRepository;
    }
}
