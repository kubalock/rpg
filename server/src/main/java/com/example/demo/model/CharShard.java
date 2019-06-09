/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "char_shards")
public class CharShard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long char_shards_id;

    @OneToOne
    @JoinColumn(name = "char_id")
    @JsonBackReference
    private Hero hero;

    @OneToOne
    @JoinColumn(name = "shard_id")
    @JsonManagedReference
    private Shard shard;
    
    private String assigned;
    
    private Integer level;
    
    private Integer armor;
    private Integer agility;
    private Integer attack_speed;
    private Integer def_ability;
    private Integer dmg_bleed;
    private Integer dmg_bleed_percent;
    private Integer dmg_cold;
    private Integer dmg_cold_percent;
    private Integer dmg_elemental;
    private Integer dmg_electric_percent;
    private Integer dmg_fire;
    private Integer dmg_fire_percent;
    private Integer dmg_poison;
    private Integer dmg_poison_percent;
    private Integer dmg_percent;
    private Integer health;
    private Integer health_regen;
    private Integer intelligence;
    private Integer intelligence_percent;
    private Integer min_damage;
    private Integer max_damage;
    private Integer min_cold;
    private Integer max_cold;
    private Integer max_fire;
    private Integer min_fire;
    private Integer max_poison;
    private Integer min_poison;
    private Integer off_ability;
    private Integer res_bleed;
    private Integer res_cold;
    private Integer res_electric;
    private Integer res_fire;
    private Integer res_poison;
    private Integer res_stun;
    private Integer slow;
    private Integer stun_chance;
    private Integer strength;
    private Integer strength_percent;
}
