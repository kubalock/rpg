/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class SkillController {
    
    @Autowired
    private SkillRepository skillRepository;
    
    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }
    
    @GetMapping("/getMasterySkills/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Skill> getMasterySkills(@PathVariable(name="id") Long mastery_id) {
        Collection<Skill> skills = skillRepository.getMasterySkills(mastery_id);
        return skills;
    }
   
    
    @GetMapping("/getSkillInfo/{skill_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Skill getSkillInfo(@PathVariable(name="skill_id") Long skill_id) {
        Skill skill = skillRepository.getOne(skill_id);
        return skill;
    }
}
