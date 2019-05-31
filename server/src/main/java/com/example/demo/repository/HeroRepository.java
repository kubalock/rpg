/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Hero;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author Grzegorz
 */
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface HeroRepository extends JpaRepository<Hero, Long> {
    
    @Query("select h from Hero h  where char_name = ?1")
    Hero getCharByName(String username);
    
    @Query("select h from Hero h where guild_id = ?1 order by level desc, experience desc")
    Collection<Hero> getGuildHeroes(Long guild_id);
    
    @Query("select h from Hero h where char_name != ?1 order by level desc, experience desc")
    Collection<Hero> getAllHeroes(String charName);
}
