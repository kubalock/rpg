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
@Table(name = "shards")
public class Shard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shard_id;
    
    private String name;
    private String type;
    private String type_eq;
    
    private Integer armor;
    private Integer agility;
    private Integer agility_percent;
    private Integer attack_speed;
    private Integer block_chance;
    private Integer block_damage;
    private Integer def_ability;
    private Integer dmg;
    private Integer dmg_bleed;
    private Integer dmg_bleed_percent;
    private Integer dmg_cold;
    private Integer dmg_cold_percent;
    private Integer dmg_electric;
    private Integer dmg_electric_percent;
    private Integer dmg_fire;
    private Integer dmg_fire_percent;
    private Integer dmg_poison;
    private Integer dmg_poison_percent;
    private Integer dmg_percent;
    private Integer endurance_percent;
    private Integer health;
    private Integer health_percent;
    private Integer health_regen;
    private Integer ignore_dmg;
    private Integer intelligence;
    private Integer intelligence_percent;
    private Integer off_ability;
    private Integer max_dmg;
    private Integer min_dmg;
    private Integer min_fire;
    private Integer max_fire;
    private Integer res_bleed;
    private Integer res_cold;
    private Integer res_electric;
    private Integer res_fire;
    private Integer res_stun;
    private Integer res_poison;
    private Integer slow;
    private Integer strength;
    private Integer strength_percent;
    private Integer stun_chance;
}
