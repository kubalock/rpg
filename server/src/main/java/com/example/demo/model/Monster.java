/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import io.micrometer.core.lang.NonNull;
import java.util.ArrayList;
import java.util.Collection;
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
@Table(name = "monsters")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long monster_id;

    private @NonNull
    String monster_name;
    private @NonNull
    Integer level;
    private @NonNull
    Integer health;
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
    Integer experience;

    public Monster(String name) {
        this.monster_name = name;
        this.level = 1;
        this.health = 200;
        this.res_fire = 0;
        this.res_cold = 0;
        this.res_electric = 0;
        this.res_poison = 0;
        this.defense = 5;
        this.block_chance = 0;
        this.block = 0;
        this.res_bleed = 0;
        this.res_stun = 0;
        this.health_regen = 1;
        this.off_ability = 20;
        this.def_ability = 20;
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
        this.experience = 10;
    }

    public Collection<String> info() {
        Collection<String> output = new ArrayList<String>();
        output.add(this.monster_name);
        output.add("");
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
