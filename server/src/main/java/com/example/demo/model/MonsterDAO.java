/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Grzegorz
 */
public class MonsterDAO {

    /**
     * @return the slowed
     */
    public String getSlowed() {
        return slowed;
    }

    /**
     * @param slowed the slowed to set
     */
    public void setSlowed(String slowed) {
        this.slowed = slowed;
    }

    /**
     * @return the stunned
     */
    public Integer getStunned() {
        return stunned;
    }

    /**
     * @param stunned the stunned to set
     */
    public void setStunned(Integer stunned) {
        this.stunned = stunned;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the monster_name
     */
    public String getMonster_name() {
        return monster_name;
    }

    /**
     * @param monster_name the monster_name to set
     */
    public void setMonster_name(String monster_name) {
        this.monster_name = monster_name;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return the health
     */
    public Integer getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(Integer health) {
        this.health = health;
    }

    /**
     * @return the res_fire
     */
    public Integer getRes_fire() {
        return res_fire;
    }

    /**
     * @param res_fire the res_fire to set
     */
    public void setRes_fire(Integer res_fire) {
        this.res_fire = res_fire;
    }

    /**
     * @return the res_cold
     */
    public Integer getRes_cold() {
        return res_cold;
    }

    /**
     * @param res_cold the res_cold to set
     */
    public void setRes_cold(Integer res_cold) {
        this.res_cold = res_cold;
    }

    /**
     * @return the res_electric
     */
    public Integer getRes_electric() {
        return res_electric;
    }

    /**
     * @param res_electric the res_electric to set
     */
    public void setRes_electric(Integer res_electric) {
        this.res_electric = res_electric;
    }

    /**
     * @return the res_poison
     */
    public Integer getRes_poison() {
        return res_poison;
    }

    /**
     * @param res_poison the res_poison to set
     */
    public void setRes_poison(Integer res_poison) {
        this.res_poison = res_poison;
    }

    /**
     * @return the defense
     */
    public Integer getDefense() {
        return defense;
    }

    /**
     * @param defense the defense to set
     */
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    /**
     * @return the block_chance
     */
    public Integer getBlock_chance() {
        return block_chance;
    }

    /**
     * @param block_chance the block_chance to set
     */
    public void setBlock_chance(Integer block_chance) {
        this.block_chance = block_chance;
    }

    /**
     * @return the block
     */
    public Integer getBlock() {
        return block;
    }

    /**
     * @param block the block to set
     */
    public void setBlock(Integer block) {
        this.block = block;
    }

    /**
     * @return the res_bleed
     */
    public Integer getRes_bleed() {
        return res_bleed;
    }

    /**
     * @param res_bleed the res_bleed to set
     */
    public void setRes_bleed(Integer res_bleed) {
        this.res_bleed = res_bleed;
    }

    /**
     * @return the res_stun
     */
    public Integer getRes_stun() {
        return res_stun;
    }

    /**
     * @param res_stun the res_stun to set
     */
    public void setRes_stun(Integer res_stun) {
        this.res_stun = res_stun;
    }

    /**
     * @return the health_regen
     */
    public Integer getHealth_regen() {
        return health_regen;
    }

    /**
     * @param health_regen the health_regen to set
     */
    public void setHealth_regen(Integer health_regen) {
        this.health_regen = health_regen;
    }

    /**
     * @return the off_ability
     */
    public Integer getOff_ability() {
        return off_ability;
    }

    /**
     * @param off_ability the off_ability to set
     */
    public void setOff_ability(Integer off_ability) {
        this.off_ability = off_ability;
    }

    /**
     * @return the def_ability
     */
    public Integer getDef_ability() {
        return def_ability;
    }

    /**
     * @param def_ability the def_ability to set
     */
    public void setDef_ability(Integer def_ability) {
        this.def_ability = def_ability;
    }

    /**
     * @return the min_damage
     */
    public Integer getMin_damage() {
        return min_damage;
    }

    /**
     * @param min_damage the min_damage to set
     */
    public void setMin_damage(Integer min_damage) {
        this.min_damage = min_damage;
    }

    /**
     * @return the max_damage
     */
    public Integer getMax_damage() {
        return max_damage;
    }

    /**
     * @param max_damage the max_damage to set
     */
    public void setMax_damage(Integer max_damage) {
        this.max_damage = max_damage;
    }

    /**
     * @return the min_cold
     */
    public Integer getMin_cold() {
        return min_cold;
    }

    /**
     * @param min_cold the min_cold to set
     */
    public void setMin_cold(Integer min_cold) {
        this.min_cold = min_cold;
    }

    /**
     * @return the max_cold
     */
    public Integer getMax_cold() {
        return max_cold;
    }

    /**
     * @param max_cold the max_cold to set
     */
    public void setMax_cold(Integer max_cold) {
        this.max_cold = max_cold;
    }

    /**
     * @return the min_fire
     */
    public Integer getMin_fire() {
        return min_fire;
    }

    /**
     * @param min_fire the min_fire to set
     */
    public void setMin_fire(Integer min_fire) {
        this.min_fire = min_fire;
    }

    /**
     * @return the max_fire
     */
    public Integer getMax_fire() {
        return max_fire;
    }

    /**
     * @param max_fire the max_fire to set
     */
    public void setMax_fire(Integer max_fire) {
        this.max_fire = max_fire;
    }

    /**
     * @return the min_electric
     */
    public Integer getMin_electric() {
        return min_electric;
    }

    /**
     * @param min_electric the min_electric to set
     */
    public void setMin_electric(Integer min_electric) {
        this.min_electric = min_electric;
    }

    /**
     * @return the max_electric
     */
    public Integer getMax_electric() {
        return max_electric;
    }

    /**
     * @param max_electric the max_electric to set
     */
    public void setMax_electric(Integer max_electric) {
        this.max_electric = max_electric;
    }

    /**
     * @return the min_bleed
     */
    public Integer getMin_bleed() {
        return min_bleed;
    }

    /**
     * @param min_bleed the min_bleed to set
     */
    public void setMin_bleed(Integer min_bleed) {
        this.min_bleed = min_bleed;
    }

    /**
     * @return the max_bleed
     */
    public Integer getMax_bleed() {
        return max_bleed;
    }

    /**
     * @param max_bleed the max_bleed to set
     */
    public void setMax_bleed(Integer max_bleed) {
        this.max_bleed = max_bleed;
    }

    /**
     * @return the min_poison
     */
    public Integer getMin_poison() {
        return min_poison;
    }

    /**
     * @param min_poison the min_poison to set
     */
    public void setMin_poison(Integer min_poison) {
        this.min_poison = min_poison;
    }

    /**
     * @return the max_poison
     */
    public Integer getMax_poison() {
        return max_poison;
    }

    /**
     * @param max_poison the max_poison to set
     */
    public void setMax_poison(Integer max_poison) {
        this.max_poison = max_poison;
    }

    /**
     * @return the stun_chance
     */
    public Integer getStun_chance() {
        return stun_chance;
    }

    /**
     * @param stun_chance the stun_chance to set
     */
    public void setStun_chance(Integer stun_chance) {
        this.stun_chance = stun_chance;
    }

    /**
     * @return the attack_speed
     */
    public Integer getAttack_speed() {
        return attack_speed;
    }

    /**
     * @param attack_speed the attack_speed to set
     */
    public void setAttack_speed(Integer attack_speed) {
        this.attack_speed = attack_speed;
    }

    /**
     * @return the experience
     */
    public Integer getExperience() {
        return experience;
    }

    /**
     * @param experience the experience to set
     */
    public void setExperience(Integer experience) {
        this.experience = experience;
    }
    
    private int id;
    
    private 
    String monster_name;
    private 
    Integer level;
    private 
    Integer health;
    private 
    Integer res_fire;
    private 
    Integer res_cold;
    private 
    Integer res_electric;
    private 
    Integer res_poison;
    private 
    Integer defense;
    private 
    Integer block_chance;
    private 
    Integer block;
    private 
    Integer res_bleed;
    private 
    Integer res_stun;
    private 
    Integer health_regen;
    private 
    Integer off_ability;
    private 
    Integer def_ability;
    private 
    Integer min_damage;
    private 
    Integer max_damage;
    private 
    Integer min_cold;
    private 
    Integer max_cold;
    private 
    Integer min_fire;
    private 
    Integer max_fire;
    private 
    Integer min_electric;
    private 
    Integer max_electric;
    private 
    Integer min_bleed;
    private 
    Integer max_bleed;
    private 
    Integer min_poison;
    private 
    Integer max_poison;
    private 
    Integer stun_chance;
    private 
    Integer attack_speed;
    private 
    Integer experience;
    private 
    Integer stunned;
    private
    String slowed;
    
    

    public Collection<String> info() {
        Collection<String> output = new ArrayList<String>();
        output.add(this.getMonster_name());
        output.add("");
        output.add("Level: " + this.getLevel());
        output.add("Health: " + this.getHealth());
        output.add("Defense: " + this.getDefense());
        output.add("Block chance: " + this.getBlock_chance());
        output.add("Attack speed: " + this.getAttack_speed());
        output.add("Offensive Ability: " + this.getOff_ability());
        output.add("Defensive Ability: " + this.getDef_ability());
        output.add("");
        output.add("Fire Resistance: " + this.getRes_fire());
        output.add("Electric Resistance: " + this.getRes_electric());
        output.add("Cold Resistance: " + this.getRes_cold());
        output.add("Poison Resistance: " + this.getRes_poison());
        output.add("Bleed Resistance: " + this.getRes_bleed());
        output.add("Stun Resistance: " + this.getRes_stun());
        return output;
    }
    
    public MonsterDAO() {
        
    }
    
    public MonsterDAO(MonsterDAO monster) {
        this.monster_name = monster.getMonster_name();
        this.level = monster.getLevel();
        this.experience = monster.getExperience();
        this.health = monster.getHealth();
        this.res_fire = monster.getRes_fire();
        this.res_cold = monster.getRes_cold();
        this.res_electric = monster.getRes_electric();
        this.res_poison = monster.getRes_poison();
        this.defense = monster.getDefense();
        this.block_chance = monster.getBlock_chance();
        this.block = monster.getBlock();
        this.res_bleed = monster.getRes_bleed();
        this.res_stun = monster.getRes_stun();
        this.health_regen = monster.getHealth_regen();
        this.off_ability = monster.getOff_ability();
        this.def_ability = monster.getDef_ability();
        this.min_damage = monster.getMin_damage();
        this.max_damage = monster.getMax_damage();
        this.min_cold = monster.getMin_cold();
        this.max_cold = monster.getMax_cold();
        this.min_electric = monster.getMin_electric();
        this.max_electric = monster.getMax_electric();
        this.min_fire = monster.getMin_fire();
        this.max_fire = monster.getMax_fire();
        this.min_poison = monster.getMin_poison();
        this.max_poison = monster.getMax_poison();
        this.min_bleed = monster.getMin_bleed();
        this.max_bleed = monster.getMax_bleed();
        this.stun_chance = monster.getStun_chance();
        this.attack_speed = monster.getAttack_speed();
        this.stunned = 0;
        this.slowed = "no";
    }
}
