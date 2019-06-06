/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
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
@Table(name = "skills")
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skill_id;

    private String name;
    private Integer agility;
    private Integer attack_speed;
    private Integer block;
    private Integer bleed_percent;
    private Integer cold_damage;
    private Integer damage;
    private Integer ele_dmg;
    private Integer endurance;
    private Integer fire_damage;
    private Integer fire_percent;
    private Integer intelligence;
    private Integer hp;
    private Integer ignore_dmg;
    private Integer poison_percent;
    private Integer res_elemental;
    private Integer strength;

    @OneToOne
    @JoinColumn(name = "mastery_id")
    @JsonManagedReference
    private Mastery mastery;
}
