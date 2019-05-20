/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Hero;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.HeroRepository;
import com.example.demo.repository.UserRepository;
import java.util.Collection;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Grzegorz
 */
@RestController
public class HeroController {

    @Autowired
    private HeroRepository heroRepository;
    
    @Autowired
    private UserRepository userRepository;

    public HeroController(HeroRepository heroRepository, UserRepository userRepository) {
        this.heroRepository = heroRepository;
        this.userRepository = userRepository;
    }
   
    @GetMapping("/getAllHeroes/{hero}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Hero> getAllHeroes(@PathVariable(value="hero") String heroName) {
        Collection<Hero> heroes = heroRepository.findAll();
        for(Hero hero : heroes) {
            if(hero.getChar_name().equals(heroName)) {
                heroes.remove(hero);
                break;
            }
        }
        return heroes;
    }

    @GetMapping("/hero/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Hero getCharByName(@PathVariable(value = "id") String charname) {
        Hero hero = heroRepository.getCharByName(charname);
        return hero;
    }
    
    @PostMapping("/newHero/{name}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void createNewHero(@PathVariable(value = "name") String name) {
        Hero hero = new Hero(name);
        System.out.println(hero);
        heroRepository.save(hero);
    }
    
    @PutMapping("/assignHero/{id}/{hero}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void assignHero(@PathVariable(value="id") Long user_id,
            @PathVariable(value="hero") String heroName) {
        User user = userRepository.getOne(user_id);
        Hero hero = heroRepository.getCharByName(heroName);
        user.setHero(hero);
        userRepository.save(user);
    }
}
