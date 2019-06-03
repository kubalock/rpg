/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.Stones;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.StonesRepository;
import java.util.Collection;
import java.util.Random;
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
public class StonesController {

    @Autowired
    private StonesRepository stonesRepository;

    @Autowired
    private ItemRepository itemRepository;

    public StonesController(StonesRepository stonesRepository, ItemRepository itemRepository) {
        this.stonesRepository = stonesRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/getCharStones/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Stones getCharStones(@PathVariable(value = "id") Long char_id) {
        return stonesRepository.getCharStones(char_id);
    }

    @GetMapping("/upgradeItem/{id}/{char_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public boolean upgradeItem(@PathVariable(value = "id") Long item_id,
            @PathVariable(value = "char_id") Long char_id) {
        Item item = itemRepository.getOne(item_id);
        Stones stones = stonesRepository.getCharStones(char_id);

        Random rand = new Random();

        int random = rand.nextInt(100);

        if (item.getLevel() < 4) {
            stones.setBlood_stone(stones.getBlood_stone() - 1);
            if (random < 80) {
                item.setLevel(item.getLevel() + 1);
                itemRepository.save(item);
                return true;
            } else {
                if (item.getLevel() > 0) {
                    item.setLevel(item.getLevel() - 1);
                    itemRepository.save(item);
                    return false;
                } else {
                    return false;
                }
            }
        } else if (item.getLevel() == 4 || item.getLevel() == 9) {
            stones.setLife_stone(stones.getLife_stone() - 1);
            if (random < 50) {
                item.setLevel(item.getLevel() + 1);
                itemRepository.save(item);
                return true;
            } else {
                item.setLevel(item.getLevel() - 3);
                itemRepository.save(item);
                return false;
            }
        } else if (item.getLevel() > 4 && item.getLevel() < 9) {
            stones.setHeart_stone(stones.getHeart_stone() - 1);
            if (random < 60) {
                item.setLevel(item.getLevel() + 1);
                itemRepository.save(item);
                return true;
            } else {
                item.setLevel(item.getLevel() - 2);
                itemRepository.save(item);
                return false;
            }
        } else if (item.getLevel() > 9) {
            stones.setSoul_stone(stones.getSoul_stone() - 1);
            if (random < 40) {
                item.setLevel(item.getLevel() + 1);
                itemRepository.save(item);
                return true;
            } else {
                item.setLevel(item.getLevel() - 2);
                itemRepository.save(item);
                return false;
            }
        }
        stonesRepository.save(stones);
        System.out.println(random);
        return true;
    }
}
