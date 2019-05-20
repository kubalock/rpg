/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "suffix")
public class Suffix {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suffix_id;
    
    private String name;
    private String type;
    private Integer level;
    
    private Integer agility;
    private Integer agility_percent;
    private Integer armor_percent;
    private Integer attack_speed;
    private Integer damage;
    private Integer damage_cold;
    private Integer damage_electric;
    private Integer damage_fire;
    private Integer damage_percent;
    private Integer dmg_percent_bleed;
    private Integer dmg_percent_cold;
    private Integer dmg_percent_electric;
    private Integer dmg_percent_fire;
    private Integer dmg_percent_poison;
    private Integer def_ability;
    private Integer health;
    private Integer health_percent;
    private Integer health_regen;
    private Integer intelligence;
    private Integer intelligence_percent;
    private Integer off_ability;
    private Integer res_bleed;
    private Integer res_cold;
    private Integer res_electric;
    private Integer res_fire;
    private Integer res_stun;
    private Integer slow;
    private Integer strength_percent;
    private Integer strength;
    private Integer stun_chance;
}
