/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.CharSkill;
import com.example.demo.model.Hero;
import com.example.demo.model.Skill;
import com.example.demo.repository.CharSkillRepository;
import com.example.demo.repository.HeroRepository;
import com.example.demo.repository.SkillRepository;
import java.util.Collection;
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
public class CharSkillController {

    @Autowired
    private CharSkillRepository charSkillRepository;

    @Autowired
    private HeroRepository heroRepo;

    @Autowired
    private SkillRepository skillRepo;

    public CharSkillController(CharSkillRepository charSkillRepository, HeroRepository heroRepo, SkillRepository skillRepo) {
        this.charSkillRepository = charSkillRepository;
        this.heroRepo = heroRepo;
        this.skillRepo = skillRepo;
    }

    @PostMapping("/insertSkills/{char_id}/{mastery_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void insertSkills(@PathVariable(name = "char_id") Long char_id, @PathVariable(name = "mastery_id") Long mastery_id) {
        SkillController skillController = null;
        Hero hero = heroRepo.getOne(char_id);
        System.out.println(hero);
        Collection<Skill> skills = skillRepo.getMasterySkills(mastery_id);
        System.out.println(skills);
        for (Skill skill : skills) {
            CharSkill charSkill = new CharSkill();
            charSkill.setHero(hero);
            charSkill.setSkill(skill);
            charSkill.setLevel(0);
            System.out.println(charSkill);
            charSkillRepository.save(charSkill);
        }
    }

    @PutMapping("/updateSkill/{skill_id}/{level}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void updateSkill(@PathVariable(name = "skill_id") Long skill_id,
            @PathVariable(name = "level") Integer level) {
        CharSkill skill = charSkillRepository.getOne(skill_id);
        skill.setLevel(level);
        charSkillRepository.save(skill);
    }

    @GetMapping("/getCharSkills/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<CharSkill> getCharSkills(@PathVariable(value = "id") Long char_id) {
        Collection<CharSkill> charSkills = charSkillRepository.getCharSkills(char_id);
        return charSkills;
    }
}
