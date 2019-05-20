/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.CharMastery;
import com.example.demo.model.Mastery;
import com.example.demo.repository.CharMasteryRepository;
import com.example.demo.repository.MasteryRepository;
import java.util.ArrayList;
import java.util.List;
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
public class MasteryController {
    
    @Autowired
    private MasteryRepository masteryRepository;
    
    @Autowired
    private CharMasteryRepository charMasteryRepository;
    
    public MasteryController(MasteryRepository masteryRepository, CharMasteryRepository charmasteryRepository) {
        this.masteryRepository = masteryRepository;
        this.charMasteryRepository = charMasteryRepository;
    }
    
    @GetMapping("/getAvailableMasteries/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Mastery> getAvailableMasteries(@PathVariable(value="id") Long char_id) {
        List<Mastery> masteries = masteryRepository.findAll();
        List<CharMastery> charMastery = charMasteryRepository.getMasteries(char_id);
        if(charMastery != null) {
            Mastery usedMastery = charMastery.get(0).getMastery();
            masteries.remove(usedMastery);
        }
        return masteries;
    }
    
    @GetMapping("/getAllMasteries/")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Mastery> getAllMasteries() {
        List<Mastery> masteries = masteryRepository.findAll();
        return masteries;
    }
}
