/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Grzegorz
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "char_skills")
public class CharSkill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long char_skill_id;
    
    @OneToOne
    @JoinColumn(name = "char_id")
    @JsonManagedReference
    private Hero hero;
    
    @OneToOne
    @JoinColumn(name = "skill_id")
    @JsonManagedReference
    private Skill skill;
    
    private Integer level;
}
