/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.CharMastery;
import com.example.demo.repository.CharMasteryRepository;
import com.example.demo.repository.HeroRepository;
import com.example.demo.repository.MasteryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grzegorz
 */
@RestController
public class CharMasteryController {

    @Autowired
    private CharMasteryRepository charMasteryRepo;

    @Autowired
    private MasteryRepository masteryRepo;

    @Autowired
    private HeroRepository heroRepo;

    public CharMasteryController(CharMasteryRepository charMasteryRepo,
            MasteryRepository masteryRepo, HeroRepository heroRepo) {
        this.charMasteryRepo = charMasteryRepo;
        this.masteryRepo = masteryRepo;
        this.heroRepo = heroRepo;
    }

    @GetMapping("/getMasteries/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ArrayList<CharMastery> getMasteries(@PathVariable(value = "id") Long char_id) {
        ArrayList<CharMastery> masteries = new ArrayList<CharMastery>();
        masteries = charMasteryRepo.getMasteries(char_id);
        return masteries;
    }

    public List<CharMastery> getCharsMasteries(Long char_id) {
        List<CharMastery> masteries = new ArrayList<CharMastery>();
        masteries = charMasteryRepo.getMasteries(char_id);
        return masteries;
    }

    @PutMapping("/updateMastery/{id}/{mastery_level}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void updateMastery(@PathVariable(value = "id") Long char_mastery_id,
            @PathVariable(value = "mastery_level") Integer mastery_level) {
        CharMastery charMastery = charMasteryRepo.getOne(char_mastery_id);
        charMastery.setMastery_level(mastery_level);
        charMasteryRepo.save(charMastery);
    }

    @PostMapping("/selectMastery/{char_id}/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void selectMastery(@PathVariable(value = "char_id") Long char_id,
            @PathVariable(value = "id") Long mastery_id) {
        CharMastery charMastery = new CharMastery();
        charMastery.setMastery(masteryRepo.getOne(mastery_id));
        charMastery.setHero(heroRepo.getOne(char_id));
        charMastery.setMastery_level(0);
        charMasteryRepo.save(charMastery);
    }
}
