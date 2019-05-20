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
@Table(name = "prefix")
public class Prefix {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prefix_id;
    
    private String name;
    private String type;
    private Integer level;
    
    private Integer agility_percent;
    private Integer armor;
    private Integer armor_percent;
    private Integer attack_speed;
    private Integer block_percent;
    private Integer damage_cold;
    private Integer damage_electric;
    private Integer damage_fire;
    private Integer damage_percent;
    private Integer def_ability_percent;
    private Integer dmg_percent_bleed;
    private Integer dmg_percent_cold;
    private Integer dmg_percent_electric;
    private Integer dmg_percent_fire;
    private Integer health_percent;
    private Integer health_regen;
    private Integer intelligence_percent;
    private Integer max_bleed;
    private Integer max_cold;
    private Integer max_damage;
    private Integer max_electric;
    private Integer max_fire;
    private Integer max_poison;
    private Integer min_bleed;
    private Integer min_cold;
    private Integer min_damage;
    private Integer min_electric;
    private Integer min_fire;
    private Integer min_poison;
    private Integer off_ability;
    private Integer off_ability_percent;
    private Integer res_bleed;
    private Integer res_cold;
    private Integer res_electric;
    private Integer res_fire;
    private Integer res_poison;
    private Integer res_stun;
    private Integer slow;
    private Integer strength_percent;
    private Integer strength;
}
