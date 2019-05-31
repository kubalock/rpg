/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.micrometer.core.lang.NonNull;
import java.util.ArrayList;
import java.util.Collection;
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
@Table(name = "characters")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long char_id;
    
    @OneToOne
    @JoinColumn(name = "guild_id")
    @JsonBackReference
    private Guild guild;
    
    private @NonNull
    String char_name;
    private @NonNull
    String char_class;
    private @NonNull
    Integer level;
    private @NonNull
    Integer experience;
    private @NonNull
    Integer next_level;
    private @NonNull
    Integer points;
    private @NonNull
    Integer mastery_points;
    private @NonNull
    Integer health;
    private @NonNull
    Integer strength;
    private @NonNull
    Integer agility;
    private @NonNull
    Integer intelligence;
    private @NonNull
    Integer endurance;
    private @NonNull
    Integer luck;
    private @NonNull
    Integer res_fire;
    private @NonNull
    Integer res_cold;
    private @NonNull
    Integer res_electric;
    private @NonNull
    Integer res_poison;
    private @NonNull
    Integer defense;
    private @NonNull
    Integer block_chance;
    private @NonNull
    Integer block;
    private @NonNull
    Integer res_bleed;
    private @NonNull
    Integer res_stun;
    private @NonNull
    Integer health_regen;
    private @NonNull
    Integer off_ability;
    private @NonNull
    Integer def_ability;
    private @NonNull
    Integer min_damage;
    private @NonNull
    Integer max_damage;
    private @NonNull
    Integer min_cold;
    private @NonNull
    Integer max_cold;
    private @NonNull
    Integer min_fire;
    private @NonNull
    Integer max_fire;
    private @NonNull
    Integer min_electric;
    private @NonNull
    Integer max_electric;
    private @NonNull
    Integer min_bleed;
    private @NonNull
    Integer max_bleed;
    private @NonNull
    Integer min_poison;
    private @NonNull
    Integer max_poison;
    private @NonNull
    Integer stun_chance;
    private @NonNull
    Integer attack_speed;
    private @NonNull
    Integer dmg_percent;
    private @NonNull
    Integer hp_percent;
    private @NonNull
    Integer agility_percent;
    private @NonNull
    Integer fire_percent;
    private @NonNull
    Integer endurance_percent;
    private @NonNull
    Integer ignore_dmg_percent;
    private @NonNull
    Integer intelligence_percent;
    private @NonNull
    Integer def_ability_percent;
    private @NonNull
    Integer cold_percent;
    private @NonNull
    Integer electric_percent;
    private @NonNull
    Integer poison_percent;
    private @NonNull
    Integer bleed_percent;
    private @NonNull
    Integer off_ability_percent;
    private @NonNull
    Integer slow;
    private @NonNull
    Integer strength_percent;

    public Hero(String name) {
        this.char_name = name;
        this.char_class = "None";
        this.guild = null;
        this.level = 1;
        this.experience = 0;
        this.next_level = 100;
        this.points = 0;
        this.mastery_points = 0;
        this.health = 200;
        this.strength = 10;
        this.agility = 10;
        this.intelligence = 10;
        this.endurance = 10;
        this.luck = 0;
        this.res_fire = 0;
        this.res_cold = 0;
        this.res_electric = 0;
        this.res_poison = 0;
        this.defense = 0;
        this.block_chance = 0;
        this.block = 0;
        this.res_bleed = 0;
        this.res_stun = 0;
        this.health_regen = 0;
        this.off_ability = 10;
        this.def_ability = 10;
        this.min_damage = 10;
        this.max_damage = 20;
        this.min_cold = 0;
        this.max_cold = 0;
        this.min_electric = 0;
        this.max_electric = 0;
        this.min_fire = 0;
        this.max_fire = 0;
        this.min_poison = 0;
        this.max_poison = 0;
        this.min_bleed = 0;
        this.max_bleed = 0;
        this.stun_chance = 0;
        this.attack_speed = 80;
        this.dmg_percent = 0;
        this.hp_percent = 0;
        this.agility_percent = 0;
        this.fire_percent = 0;
        this.endurance_percent = 0;
        this.ignore_dmg_percent = 0;
        this.intelligence_percent = 0;
        this.def_ability_percent = 0;
        this.cold_percent = 0;
        this.electric_percent = 0;
        this.poison_percent = 0;
        this.bleed_percent = 0;
 
        this.off_ability_percent = 0;
        this.slow = 0;
        this.strength_percent = 0;
    }
   
    
    public Collection<String> info() {
        Collection<String> output = new ArrayList<String>();
        output.add(this.char_name);
        output.add("Class: " + this.char_class);
        output.add("Level: " + this.level);
        output.add("Health: " + this.health);
        output.add("Defense: " + this.defense);
        output.add("Block chance: " + this.block_chance);
        output.add("Attack speed: " + this.attack_speed);
        output.add("Offensive Ability: " + this.off_ability);
        output.add("Defensive Ability: " + this.def_ability);
        output.add("");
        output.add("Fire Resistance: " + this.res_fire);
        output.add("Electric Resistance: " + this.res_electric);
        output.add("Cold Resistance: " + this.res_cold);
        output.add("Poison Resistance: " + this.res_poison);
        output.add("Bleed Resistance: " + this.res_bleed);
        output.add("Stun Resistance: " + this.res_stun);
        return output;
    }
}
