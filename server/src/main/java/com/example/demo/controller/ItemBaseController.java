/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.ItemBaseRepository;

/**
 *
 * @author Grzegorz
 */
@RestController
public class ItemBaseController {
    
    @Autowired
    private ItemBaseRepository itemBaseRepository;
    
    public ItemBaseController(ItemBaseRepository itemBaseRepository) {
        this.itemBaseRepository = itemBaseRepository;
    }
}
