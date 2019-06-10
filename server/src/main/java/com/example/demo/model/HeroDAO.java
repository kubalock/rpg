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
public class HeroDAO {

    /**
     * @return the max_health
     */
    public Integer getMax_health() {
        return max_health;
    }

    /**
     * @param max_health the max_health to set
     */
    public void setMax_health(Integer max_health) {
        this.max_health = max_health;
    }

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

    public HeroDAO() {

    }

    public HeroDAO(Hero hero) {
        this.char_name = hero.getChar_name();
        this.char_class = hero.getChar_class();
        this.level = hero.getLevel();
        this.max_health = hero.getHealth();
        this.health = hero.getHealth();
        this.strength = hero.getStrength();
        this.agility = hero.getAgility();
        this.intelligence = hero.getIntelligence();
        this.endurance = hero.getEndurance();
        this.luck = hero.getLuck();
        this.res_fire = hero.getRes_fire();
        this.res_cold = hero.getRes_cold();
        this.res_electric = hero.getRes_electric();
        this.res_poison = hero.getRes_poison();
        this.defense = hero.getDefense();
        this.block_chance = hero.getBlock_chance();
        this.block = hero.getBlock();
        this.res_bleed = hero.getRes_bleed();
        this.res_stun = hero.getRes_stun();
        this.health_regen = hero.getHealth_regen();
        this.off_ability = hero.getOff_ability();
        this.def_ability = hero.getDef_ability();
        this.min_damage = hero.getMin_damage();
        this.max_damage = hero.getMax_damage();
        this.min_cold = hero.getMin_cold();
        this.max_cold = hero.getMax_cold();
        this.min_electric = hero.getMin_electric();
        this.max_electric = hero.getMax_electric();
        this.min_fire = hero.getMin_fire();
        this.max_fire = hero.getMax_fire();
        this.min_poison = hero.getMin_poison();
        this.max_poison = hero.getMax_poison();
        this.min_bleed = hero.getMin_bleed();
        this.max_bleed = hero.getMax_bleed();
        this.stun_chance = hero.getStun_chance();
        this.attack_speed = hero.getAttack_speed();
        this.dmg_percent = hero.getDmg_percent();
        this.hp_percent = hero.getHp_percent();
        this.agility_percent = hero.getAgility_percent();
        this.fire_percent = hero.getFire_percent();
        this.endurance_percent = hero.getEndurance_percent();
        this.ignore_dmg_percent = hero.getIgnore_dmg_percent();
        this.intelligence_percent = hero.getIntelligence_percent();
        this.def_ability_percent = hero.getDef_ability_percent();
        this.cold_percent = hero.getCold_percent();
        this.electric_percent = hero.getElectric_percent();
        this.poison_percent = hero.getPoison_percent();
        this.bleed_percent = hero.getBleed_percent();
        this.off_ability_percent = hero.getOff_ability_percent();
        this.slow = hero.getSlow();
        this.strength_percent = hero.getStrength_percent();
        this.stunned = 0;
        this.slowed = "no";
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

    /**
     * @return the char_id
     */
    public Long getChar_id() {
        return char_id;
    }

    /**
     * @param char_id the char_id to set
     */
    public void setChar_id(Long char_id) {
        this.char_id = char_id;
    }

    /**
     * @return the char_name
     */
    public String getChar_name() {
        return char_name;
    }

    /**
     * @param char_name the char_name to set
     */
    public void setChar_name(String char_name) {
        this.char_name = char_name;
    }

    /**
     * @return the char_class
     */
    public String getChar_class() {
        return char_class;
    }

    /**
     * @param char_class the char_class to set
     */
    public void setChar_class(String char_class) {
        this.char_class = char_class;
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

    /**
     * @return the next_level
     */
    public Integer getNext_level() {
        return next_level;
    }

    /**
     * @param next_level the next_level to set
     */
    public void setNext_level(Integer next_level) {
        this.next_level = next_level;
    }

    /**
     * @return the points
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * @return the mastery_points
     */
    public Integer getMastery_points() {
        return mastery_points;
    }

    /**
     * @param mastery_points the mastery_points to set
     */
    public void setMastery_points(Integer mastery_points) {
        this.mastery_points = mastery_points;
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
     * @return the strength
     */
    public Integer getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    /**
     * @return the agility
     */
    public Integer getAgility() {
        return agility;
    }

    /**
     * @param agility the agility to set
     */
    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    /**
     * @return the intelligence
     */
    public Integer getIntelligence() {
        return intelligence;
    }

    /**
     * @param intelligence the intelligence to set
     */
    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * @return the endurance
     */
    public Integer getEndurance() {
        return endurance;
    }

    /**
     * @param endurance the endurance to set
     */
    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    /**
     * @return the luck
     */
    public Integer getLuck() {
        return luck;
    }

    /**
     * @param luck the luck to set
     */
    public void setLuck(Integer luck) {
        this.luck = luck;
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
     * @return the dmg_percent
     */
    public Integer getDmg_percent() {
        return dmg_percent;
    }

    /**
     * @param dmg_percent the dmg_percent to set
     */
    public void setDmg_percent(Integer dmg_percent) {
        this.dmg_percent = dmg_percent;
    }

    /**
     * @return the hp_percent
     */
    public Integer getHp_percent() {
        return hp_percent;
    }

    /**
     * @param hp_percent the hp_percent to set
     */
    public void setHp_percent(Integer hp_percent) {
        this.hp_percent = hp_percent;
    }

    /**
     * @return the agility_percent
     */
    public Integer getAgility_percent() {
        return agility_percent;
    }

    /**
     * @param agility_percent the agility_percent to set
     */
    public void setAgility_percent(Integer agility_percent) {
        this.agility_percent = agility_percent;
    }

    /**
     * @return the fire_percent
     */
    public Integer getFire_percent() {
        return fire_percent;
    }

    /**
     * @param fire_percent the fire_percent to set
     */
    public void setFire_percent(Integer fire_percent) {
        this.fire_percent = fire_percent;
    }

    /**
     * @return the endurance_percent
     */
    public Integer getEndurance_percent() {
        return endurance_percent;
    }

    /**
     * @param endurance_percent the endurance_percent to set
     */
    public void setEndurance_percent(Integer endurance_percent) {
        this.endurance_percent = endurance_percent;
    }

    /**
     * @return the ignore_dmg_percent
     */
    public Integer getIgnore_dmg_percent() {
        return ignore_dmg_percent;
    }

    /**
     * @param ignore_dmg_percent the ignore_dmg_percent to set
     */
    public void setIgnore_dmg_percent(Integer ignore_dmg_percent) {
        this.ignore_dmg_percent = ignore_dmg_percent;
    }

    /**
     * @return the intelligence_percent
     */
    public Integer getIntelligence_percent() {
        return intelligence_percent;
    }

    /**
     * @param intelligence_percent the intelligence_percent to set
     */
    public void setIntelligence_percent(Integer intelligence_percent) {
        this.intelligence_percent = intelligence_percent;
    }

    /**
     * @return the def_ability_percent
     */
    public Integer getDef_ability_percent() {
        return def_ability_percent;
    }

    /**
     * @param def_ability_percent the def_ability_percent to set
     */
    public void setDef_ability_percent(Integer def_ability_percent) {
        this.def_ability_percent = def_ability_percent;
    }

    /**
     * @return the cold_percent
     */
    public Integer getCold_percent() {
        return cold_percent;
    }

    /**
     * @param cold_percent the cold_percent to set
     */
    public void setCold_percent(Integer cold_percent) {
        this.cold_percent = cold_percent;
    }

    /**
     * @return the electric_percent
     */
    public Integer getElectric_percent() {
        return electric_percent;
    }

    /**
     * @param electric_percent the electric_percent to set
     */
    public void setElectric_percent(Integer electric_percent) {
        this.electric_percent = electric_percent;
    }

    /**
     * @return the poison_percent
     */
    public Integer getPoison_percent() {
        return poison_percent;
    }

    /**
     * @param poison_percent the poison_percent to set
     */
    public void setPoison_percent(Integer poison_percent) {
        this.poison_percent = poison_percent;
    }

    /**
     * @return the bleed_percent
     */
    public Integer getBleed_percent() {
        return bleed_percent;
    }

    /**
     * @param bleed_percent the bleed_percent to set
     */
    public void setBleed_percent(Integer bleed_percent) {
        this.bleed_percent = bleed_percent;
    }

    /**
     * @return the off_ability_percent
     */
    public Integer getOff_ability_percent() {
        return off_ability_percent;
    }

    /**
     * @param off_ability_percent the off_ability_percent to set
     */
    public void setOff_ability_percent(Integer off_ability_percent) {
        this.off_ability_percent = off_ability_percent;
    }

    /**
     * @return the slow
     */
    public Integer getSlow() {
        return slow;
    }

    /**
     * @param slow the slow to set
     */
    public void setSlow(Integer slow) {
        this.slow = slow;
    }

    /**
     * @return the strength_percent
     */
    public Integer getStrength_percent() {
        return strength_percent;
    }

    /**
     * @param strength_percent the strength_percent to set
     */
    public void setStrength_percent(Integer strength_percent) {
        this.strength_percent = strength_percent;
    }

    private Long char_id;

    private String char_name;
    private String char_class;
    private Integer level;
    private Integer experience;
    private Integer next_level;
    private Integer points;
    private Integer mastery_points;
    private Integer health;
    private Integer max_health;
    private Integer strength;
    private Integer agility;
    private Integer intelligence;
    private Integer endurance;
    private Integer luck;
    private Integer res_fire;
    private Integer res_cold;
    private Integer res_electric;
    private Integer res_poison;
    private Integer defense;
    private Integer block_chance;
    private Integer block;
    private Integer res_bleed;
    private Integer res_stun;
    private Integer health_regen;
    private Integer off_ability;
    private Integer def_ability;
    private Integer min_damage;
    private Integer max_damage;
    private Integer min_cold;
    private Integer max_cold;
    private Integer min_fire;
    private Integer max_fire;
    private Integer min_electric;
    private Integer max_electric;
    private Integer min_bleed;
    private Integer max_bleed;
    private Integer min_poison;
    private Integer max_poison;
    private Integer stun_chance;
    private Integer attack_speed;
    private Integer dmg_percent;
    private Integer hp_percent;
    private Integer agility_percent;
    private Integer fire_percent;
    private Integer endurance_percent;
    private Integer ignore_dmg_percent;
    private Integer intelligence_percent;
    private Integer def_ability_percent;
    private Integer cold_percent;
    private Integer electric_percent;
    private Integer poison_percent;
    private Integer bleed_percent;
    private Integer off_ability_percent;
    private Integer slow;
    private Integer strength_percent;
    
    private Integer stunned;
    private String slowed;
}
