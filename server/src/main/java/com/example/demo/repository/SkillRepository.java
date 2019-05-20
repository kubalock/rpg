/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Skill;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Grzegorz
 */
@RepositoryRestResource
public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    @Query("select s from Skill s where mastery_id = ?1")
    Collection<Skill> getMasterySkills(Long mastery_id);
}
