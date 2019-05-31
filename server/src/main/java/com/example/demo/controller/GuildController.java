/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Guild;
import com.example.demo.model.Hero;
import com.example.demo.repository.GuildRepository;
import com.example.demo.repository.HeroRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class GuildController {

    @Autowired
    private GuildRepository guildRepository;

    @Autowired
    private HeroRepository heroRepository;

    public GuildController(GuildRepository guildRepository,
            HeroRepository heroRepository) {
        this.guildRepository = guildRepository;
        this.heroRepository = heroRepository;
    }

    @PostMapping("/newGuild/{name}/{description}/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void newGuild(@PathVariable(value = "name") String name,
            @PathVariable(value = "description") String description,
            @PathVariable(value = "id") Long id) {
        Guild guild = new Guild(name, description, id);
        guildRepository.save(guild);
        Hero hero = heroRepository.getOne(id);
        hero.setGuild(guild);
        heroRepository.save(hero);
    }

    @GetMapping("/getAllGuilds")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Guild> getAllGuilds() {
        return guildRepository.getAllGuilds();
    }

    @GetMapping("/getGuild/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Guild getGuild(@PathVariable(value = "id") Long id) {
        return guildRepository.getOne(id);
    }

    @DeleteMapping("/deleteGuild/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteGuild(@PathVariable(value = "id") Long id) {
        Collection<Hero> heroes = heroRepository.getGuildHeroes(id);
        heroes.stream().forEach(hero -> hero.setGuild(null));
        heroes.stream().forEach(hero -> heroRepository.save(hero));
        guildRepository.delete(guildRepository.getOne(id));
    }

    @PutMapping("/joinGuild/{id}/{myId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void joinGuild(@PathVariable(value = "id") Long guild_id,
            @PathVariable(value = "myId") Long hero_id) {
        Hero hero = heroRepository.getOne(hero_id);
        Guild guild = guildRepository.getOne(guild_id);
        hero.setGuild(guild);
        heroRepository.save(hero);
    }

    @PutMapping("/leaveGuild/{myId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void leaveGuild(@PathVariable(value = "myId") Long hero_id) {
        Hero hero = heroRepository.getOne(hero_id);
        hero.setGuild(null);
        heroRepository.save(hero);
    }

}
