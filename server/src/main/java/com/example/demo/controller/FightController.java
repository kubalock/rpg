/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Hero;
import com.example.demo.model.HeroDAO;
import com.example.demo.repository.HeroRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PrefixRepository;
import com.example.demo.repository.SuffixRepository;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.ItemBaseRepository;

/**
 *
 * @author Grzegorz
 */
@RestController
public class FightController {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PrefixRepository prefixRepository;

    @Autowired
    private SuffixRepository suffixRepository;

    @Autowired
    private ItemBaseRepository uniqueItemRepository;

    public FightController(HeroRepository hero, ItemRepository item, PrefixRepository prefix, SuffixRepository suffix, ItemBaseRepository unique) {
        this.heroRepository = hero;
        this.itemRepository = item;
        this.prefixRepository = prefix;
        this.suffixRepository = suffix;
        this.uniqueItemRepository = unique;
    }

    @GetMapping("/fight/{my_id}/{other_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<String> fight1v1(@PathVariable(value = "my_id") Long my_id, @PathVariable(value = "other_id") Long other_id) {
        Collection<String> report = new ArrayList<String>();
        Random rand = new Random();

        Hero originalMe = heroRepository.getOne(my_id);
        Hero originalYou = heroRepository.getOne(other_id);

        HeroDAO me = new HeroDAO(originalMe);
        HeroDAO you = new HeroDAO(originalYou);

        this.assignPercentages(me);
        this.assignPercentages(you);

        int round = 1;

        do {
            int random = rand.nextInt(100);
            report.add("Round " + round);
            if (me.getStunned() == 0) {
                report.add(this.oneRound(me, you));
                if (me.getAttack_speed() > 100) {
                    if (random < me.getAttack_speed() - 100) {
                        report.add(this.oneRound(me, you));
                    }
                    if (random < me.getAttack_speed() - 200) {
                        report.add(this.oneRound(me, you));
                    }
                }
            } else {
                report.add(me.getChar_name() + " is stunned and cannot attack.");
                me.setStunned(me.getStunned() - 1);
            }
            if (you.getStunned() == 0) {
            report.add(this.oneRound(you, me));
            if (you.getAttack_speed() > 100) {
                if (random < you.getAttack_speed() - 100) {
                    report.add(this.oneRound(you, me));
                }
                if (random < you.getAttack_speed() - 200) {
                    report.add(this.oneRound(you, me));
                }
            }
            } else {
                report.add(you.getChar_name() + " is stunned and cannot attack.");
                me.setStunned(you.getStunned() - 1);
            }

            if (me.getHealth() > 0) {
                int healthRegenerated = (me.getEndurance() / 2) + me.getHealth_regen();
                report.add(me.getChar_name() + " regenerates " + healthRegenerated + " health points");
                int myHealth = me.getHealth() + healthRegenerated;
                me.setHealth(myHealth);
            }
            if (you.getHealth() > 0) {
                int healthRegenerated = (you.getEndurance() / 2) + you.getHealth_regen();
                report.add(you.getChar_name() + " regenerates " + healthRegenerated + " health points");
                int yourHealth = you.getHealth() + healthRegenerated;
                you.setHealth(yourHealth);
            }
            report.add("");
            round++;
        } while (me.getHealth() > 0 && you.getHealth() > 0);

        if (me.getHealth() > you.getHealth()) {
            this.addExperience(originalMe.getChar_id(), 30);
            this.addExperience(originalYou.getChar_id(), 5);
        } else {
            this.addExperience(originalMe.getChar_id(), 5);
            this.addExperience(originalYou.getChar_id(), 30);
        }

        report.add("");
        report.add(this.finalReport(me, you));
        return report;
    }

    public String finalReport(HeroDAO me, HeroDAO you) {
        String output;

        if (me.getHealth() > you.getHealth()) {
            output = me.getChar_name() + " won the fight and gained 30 EXP (5 for " + you.getChar_name() + ").";
        } else {
            output = you.getChar_name() + " won the fight and gained 30 EXP (5 for " + me.getChar_name() + ").";
        }

        return output;
    }

    public HeroDAO assignPercentages(HeroDAO hero) {
        if (hero.getStrength_percent() > 0) {
            int bonusStrength = hero.getStrength() * hero.getStrength_percent() / 100;
            hero.setStrength(hero.getStrength() + bonusStrength);
        }
        if (hero.getDmg_percent() > 0) {
            int bonusMin = hero.getMin_damage() * hero.getDmg_percent() / 100;
            hero.setMin_damage(bonusMin + hero.getMin_damage() + hero.getStrength());
            int bonusMax = hero.getMax_damage() * hero.getDmg_percent() / 100;
            hero.setMax_damage(bonusMax + hero.getMax_damage() + hero.getStrength());
        } else {
            hero.setMin_damage(hero.getMin_damage() + hero.getStrength());
            hero.setMax_damage(hero.getMax_damage() + hero.getStrength());
        }
        if (hero.getHp_percent() > 0) {
            int bonusHealth = hero.getHealth() * hero.getHp_percent() / 100;
            hero.setHealth(bonusHealth + hero.getHealth());
        }
        if (hero.getOff_ability_percent() > 0) {
            int bonusAbility = hero.getOff_ability() * hero.getOff_ability_percent() / 100;
            hero.setOff_ability(hero.getOff_ability() + bonusAbility + hero.getAgility());
        } else {
            hero.setOff_ability(hero.getOff_ability() + hero.getAgility());
        }
        if (hero.getDef_ability_percent() > 0) {
            int bonusAbility = hero.getDef_ability() * hero.getDef_ability_percent() / 100;
            hero.setDef_ability(hero.getDef_ability() + bonusAbility + hero.getAgility());
        } else {
            hero.setDef_ability(hero.getDef_ability() + hero.getAgility());
        }
        if (hero.getAgility_percent() > 0) {
            int bonusAgility = hero.getAgility() * hero.getAgility_percent() / 100;
            hero.setAgility(bonusAgility + hero.getAgility());
            hero.setDef_ability(bonusAgility + hero.getDef_ability());
            hero.setOff_ability(bonusAgility + hero.getOff_ability());
        }
        if (hero.getFire_percent() > 0) {
            int bonusMin = hero.getMin_fire() * hero.getFire_percent() / 100;
            int bonusMax = hero.getMax_fire() * hero.getFire_percent() / 100;
            hero.setMin_fire(bonusMin + hero.getMin_fire());
            hero.setMax_fire(bonusMax + hero.getMax_fire());
        }
        if (hero.getEndurance_percent() > 0) {
            int bonusEndurance = hero.getEndurance() * hero.getEndurance_percent() / 100;
            hero.setEndurance(hero.getEndurance() + bonusEndurance);
            hero.setDefense(hero.getDefense() + (hero.getEndurance() / 2));
            hero.setHealth_regen(hero.getHealth_regen() + (hero.getEndurance() / 2));
        } else {
            hero.setDefense(hero.getDefense() + (hero.getEndurance() / 2));
            hero.setHealth_regen(hero.getHealth_regen() + (hero.getEndurance() / 2));
        }
        if (hero.getIntelligence_percent() > 0) {
            int bonus = hero.getIntelligence() * hero.getIntelligence_percent() / 100;
            hero.setIntelligence(hero.getIntelligence() + bonus);
        }
        if (hero.getCold_percent() > 0) {
            int bonusMin = hero.getMin_cold() * hero.getCold_percent() / 100;
            int bonusMax = hero.getMax_cold() * hero.getCold_percent() / 100;
            hero.setMin_cold(bonusMin + hero.getMin_cold());
            hero.setMax_cold(bonusMax + hero.getMax_cold());
        }
        if (hero.getElectric_percent() > 0) {
            int bonusMin = hero.getMin_electric() * hero.getElectric_percent() / 100;
            int bonusMax = hero.getMax_electric() * hero.getElectric_percent() / 100;
            hero.setMin_electric(bonusMin + hero.getMin_electric());
            hero.setMax_electric(bonusMax + hero.getMax_electric());
        }
        if (hero.getBleed_percent() > 0) {
            int bonusMin = hero.getMin_bleed() * hero.getBleed_percent() / 100;
            int bonusMax = hero.getMax_bleed() * hero.getBleed_percent() / 100;
            hero.setMin_bleed(bonusMin + hero.getMin_bleed());
            hero.setMax_bleed(bonusMax + hero.getMax_bleed());
        }
        if (hero.getPoison_percent() > 0) {
            int bonusMin = hero.getMin_poison() * hero.getPoison_percent() / 100;
            int bonusMax = hero.getMax_poison() * hero.getPoison_percent() / 100;
            hero.setMin_poison(bonusMin + hero.getMin_poison());
            hero.setMax_poison(bonusMax + hero.getMax_poison());
        }
        return hero;
    }

    public boolean tryHit(HeroDAO me, HeroDAO you) {
        Random rand = new Random();
        int random = rand.nextInt(100);

        int myOffensive = me.getOff_ability();
        int yourDeffensive = you.getDef_ability();

        int difference = Math.abs(myOffensive - yourDeffensive);

        if (myOffensive == yourDeffensive) {
            if (random < 60) {
                return true;
            } else {
                return false;
            }
        } else if (myOffensive > yourDeffensive) {
            if (difference > 30) {
                if (random < 90) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (random < 60 + difference) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if (myOffensive < yourDeffensive) {
            if (difference > 30) {
                if (random < 30) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (random < 60 - difference) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public String oneRound(HeroDAO me, HeroDAO you) {
        String slowed = you.getSlowed();
        String output;
        if (this.tryHit(me, you) == true) {
            Collection<Integer> damageDealt = this.damageDealt(me, you);
            if (!damageDealt.toArray()[1].toString().equals("0")) {
                output = me.getChar_name() + " uses (weapon) and deals " + damageDealt.toArray()[0] + " damage to " + you.getChar_name() + "(" + damageDealt.toArray()[1] + " blocked by (shield)). (" + you.getHealth() + " health remaining). ";
            } else {
                output = me.getChar_name() + " uses (weapon) and deals " + damageDealt.toArray()[0] + " damage to " + you.getChar_name() + "(" + you.getHealth() + " health remaining). ";
            }
            if (slowed.equals("no") && me.getSlow() > 0) {
                output = output + me.getChar_name() + " slows " + you.getChar_name() + " by " + me.getSlow() + "%.";
            }
        } else {
            output = me.getChar_name() + " uses (weapon) to hit " + you.getChar_name() + " but it was a miss";
        }
        return output;
    }

    public Collection<Integer> damageDealt(HeroDAO me, HeroDAO you) {
        int minDamage, maxDamage;

        Collection<Integer> output = new ArrayList<>();

        int damageDealt = 0;
        Random ran = new Random();

        if (me.getMin_damage() <= you.getDefense()) {
            minDamage = 1;
        } else {
            minDamage = me.getMin_damage() - you.getDefense();
            if (you.getIgnore_dmg_percent() > 0) {
                minDamage = Math.round(minDamage - ((you.getIgnore_dmg_percent() / 100) * minDamage));
                if (minDamage <= 0) {
                    minDamage = 1;
                }
            }
        }
        if (me.getMax_damage() <= you.getDefense()) {
            maxDamage = 1;
        } else {
            maxDamage = me.getMax_damage() - you.getDefense();
            if (you.getIgnore_dmg_percent() > 0) {
                maxDamage = Math.round(maxDamage - ((you.getIgnore_dmg_percent() / 100) * maxDamage));
                if (maxDamage <= 0) {
                    maxDamage = 1;
                }
            }
        }

        damageDealt = ran.nextInt(maxDamage) + minDamage;
        int blockedDamage = 0;

        if (you.getBlock_chance() > 0) {
            if (ran.nextInt(100) < you.getBlock_chance()) {
                damageDealt = damageDealt - you.getBlock();
                if (damageDealt <= 0) {
                    damageDealt = 0;
                }
                blockedDamage = you.getBlock();
            }
        }

        if (me.getMin_bleed() > 0) {
            minDamage = Math.round((1 - (you.getRes_bleed() / 100)) * me.getMin_bleed());
            maxDamage = Math.round((1 - (you.getRes_bleed() / 100)) * me.getMax_bleed());
            damageDealt = damageDealt + ran.nextInt(maxDamage) + minDamage;
        }
        if (me.getMin_cold() > 0) {
            minDamage = Math.round((1 - (you.getRes_cold() / 100)) * me.getMin_cold());
            maxDamage = Math.round((1 - (you.getRes_cold() / 100)) * me.getMax_cold());
            damageDealt = damageDealt + ran.nextInt(maxDamage) + minDamage;
        }
        if (me.getMin_electric() > 0) {
            minDamage = Math.round((1 - (you.getRes_electric() / 100)) * me.getMin_electric());
            maxDamage = Math.round((1 - (you.getRes_electric() / 100)) * me.getMax_electric());
            damageDealt = damageDealt + ran.nextInt(maxDamage) + minDamage;
        }
        if (me.getMin_fire() > 0) {
            minDamage = Math.round((1 - (you.getRes_fire() / 100)) * me.getMin_fire());
            maxDamage = Math.round((1 - (you.getRes_fire() / 100)) * me.getMax_fire());
            damageDealt = damageDealt + ran.nextInt(maxDamage) + minDamage;
        }
        if (me.getMin_poison() > 0) {
            minDamage = Math.round((1 - (you.getRes_poison() / 100)) * me.getMin_poison());
            maxDamage = Math.round((1 - (you.getRes_poison() / 100)) * me.getMax_poison());
            damageDealt = damageDealt + ran.nextInt(maxDamage) + minDamage;
        }

        if (me.getStun_chance() > 0) {
            if (ran.nextInt(100) < me.getStun_chance() - you.getRes_stun()) {
                you.setStunned(1);
                //
            }
        }

        if (me.getSlow() > 0 && you.getSlowed() == "no") {
            you.setSlowed("yes");
            you.setAttack_speed(you.getAttack_speed() - me.getSlow());
        }

        you.setHealth(you.getHealth() - damageDealt);
        if (you.getHealth() <= 0) {
            you.setHealth(0);
        }
        output.add(damageDealt);
        output.add(blockedDamage);
        return output;
    }

    public void addExperience(Long char_id, Integer experience) {
        Hero hero = heroRepository.getOne(char_id);
        experience = experience + hero.getExperience();
        hero.setExperience(experience);
        heroRepository.save(hero);
    }
}
