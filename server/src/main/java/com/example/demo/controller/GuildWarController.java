/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Guild;
import com.example.demo.model.Hero;
import com.example.demo.model.HeroDAO;
import com.example.demo.repository.GuildRepository;
import com.example.demo.repository.HeroRepository;
import com.example.demo.repository.ItemBaseRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PrefixRepository;
import com.example.demo.repository.SuffixRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grzegorz
 */
@RestController
public class GuildWarController {

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

    @Autowired
    private GuildRepository guildRepository;

    public GuildWarController(HeroRepository hero, ItemRepository item, PrefixRepository prefix, SuffixRepository suffix, ItemBaseRepository unique, GuildRepository guildRepository) {
        this.heroRepository = hero;
        this.itemRepository = item;
        this.prefixRepository = prefix;
        this.suffixRepository = suffix;
        this.uniqueItemRepository = unique;
        this.guildRepository = guildRepository;
    }

    @GetMapping("/guildWar/{my_guild}/{other_guild}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<String> guildWar(@PathVariable(value = "my_guild") Long my_guild,
            @PathVariable(value = "other_guild") Long other_guild) {
        Collection<String> report = new ArrayList<String>();
        Random rand = new Random();

        Guild us = guildRepository.getOne(my_guild);
        Guild them = guildRepository.getOne(other_guild);

        Collection<Hero> ourHeroes = heroRepository.getGuildHeroes(us.getGuild_id());
        Collection<Hero> theirHeroes = heroRepository.getGuildHeroes(them.getGuild_id());

        List<HeroDAO> ourDAO = ourHeroes.stream()
                .map(hero -> new HeroDAO(hero))
                .collect(Collectors.toList());

        ourDAO.stream().forEach(hero -> this.assignPercentages(hero));

        List<HeroDAO> youDAO = theirHeroes.stream()
                .map(hero -> new HeroDAO(hero))
                .collect(Collectors.toList());

        youDAO.stream().forEach(hero -> this.assignPercentages(hero));

        int round = 1;

        report.add(String.valueOf(ourDAO.size()));
        report.add(String.valueOf(youDAO.size()));

        for (HeroDAO hero : ourDAO) {
            for (String line : hero.info()) {
                report.add(line);
            }
        }

        for (HeroDAO hero : youDAO) {
            for (String line : hero.info()) {
                report.add(line);
            }
        }

        int random;
        int chance;
        int ourAttacks = 0;
        int youAttacks = 0;

        do {
            report.add("Round " + round);
            Collections.shuffle(ourDAO);
            Collections.shuffle(youDAO);
            do {
                if (ourAttacks < ourDAO.size()) {
                    HeroDAO me = ourDAO.get(ourAttacks);
                    if (me.getStunned() == 0) {
                        random = rand.nextInt(youDAO.size()) + 0;
                        report.add(this.oneRound(me, youDAO.get(random)));
                        if (this.checkHero(youDAO.get(random))) {
                            report.add(youDAO.get(random).getChar_name() + " dies");
                            youDAO.remove(random);
                            if (youDAO.size() == 0) {
                                break;
                            }
                        }
                        random = rand.nextInt(youDAO.size()) + 0;
                        chance = rand.nextInt(100) + 0;
                        if (me.getAttack_speed() > 100) {
                            if (chance < me.getAttack_speed() - 100) {
                                report.add(this.oneRound(me, youDAO.get(random)));
                                if (this.checkHero(youDAO.get(random))) {
                                    report.add(ourDAO.get(random).getChar_name() + " dies");
                                    youDAO.remove(random);
                                    if (youDAO.size() == 0) {
                                        break;
                                    }
                                }
                                random = rand.nextInt(youDAO.size()) + 0;
                            }
                            if (chance < me.getAttack_speed() - 200) {
                                report.add(this.oneRound(me, youDAO.get(random)));
                                if (this.checkHero(youDAO.get(random))) {
                                    report.add(youDAO.get(random).getChar_name() + " dies");
                                    youDAO.remove(random);
                                    if (youDAO.size() == 0) {
                                        break;
                                    }
                                }
                                random = rand.nextInt(youDAO.size()) + 0;
                            }
                        }
                    } else {
                        report.add(me.getChar_name() + " is stunned and cannot attack.");
                        me.setStunned(me.getStunned() - 1);
                    }
                }
                if (youAttacks < youDAO.size()) {
                    HeroDAO you = youDAO.get(youAttacks);
                    if (you.getStunned() == 0) {
                        random = rand.nextInt(ourDAO.size()) + 0;
                        report.add(this.oneRound(you, ourDAO.get(random)));
                        if (this.checkHero(ourDAO.get(random))) {
                            report.add(ourDAO.get(random).getChar_name() + " dies");
                            ourDAO.remove(random);
                            if (ourDAO.size() == 0) {
                                break;
                            }
                        }
                        random = rand.nextInt(ourDAO.size()) + 0;
                        chance = rand.nextInt(100) + 0;
                        if (you.getAttack_speed() > 100) {
                            if (chance < you.getAttack_speed() - 100) {
                                report.add(this.oneRound(you, ourDAO.get(random)));
                                if (this.checkHero(ourDAO.get(random))) {
                                    report.add(youDAO.get(random).getChar_name() + " dies");
                                    ourDAO.remove(random);
                                    if (ourDAO.size() == 0) {
                                        break;
                                    }
                                }
                                random = rand.nextInt(ourDAO.size()) + 0;
                            }
                            if (chance < you.getAttack_speed() - 200) {
                                report.add(this.oneRound(you, ourDAO.get(random)));
                                if (this.checkHero(ourDAO.get(random))) {
                                    report.add(youDAO.get(random).getChar_name() + " dies");
                                    ourDAO.remove(random);
                                    if (ourDAO.size() == 0) {
                                        break;
                                    }
                                }
                                random = rand.nextInt(ourDAO.size()) + 0;
                            }
                        }
                    } else {
                        report.add(you.getChar_name() + " is stunned and cannot attack.");
                        you.setStunned(you.getStunned() - 1);
                    }
                }
                ourAttacks++;
                youAttacks++;
            } while (ourAttacks <= ourDAO.size() && youAttacks <= youDAO.size());
            ourAttacks = 0;
            youAttacks = 0;
            if (youDAO.size() == 0) {
                break;
            }
            if (ourDAO.size() == 0) {
                break;
            }
            for (HeroDAO hero : ourDAO) {
                if (hero.getHealth() > 0) {
                    hero.setHealth(hero.getHealth() + hero.getHealth_regen());
                    if (hero.getHealth() > hero.getMax_health()) {
                        hero.setHealth(hero.getMax_health());
                    }
                }
            }
            for (HeroDAO hero : youDAO) {
                if (hero.getHealth() > 0) {
                    hero.setHealth(hero.getHealth() + hero.getHealth_regen());
                    if (hero.getHealth() > hero.getMax_health()) {
                        hero.setHealth(hero.getMax_health());
                    }
                }
            }
            round++;
            report.add("");
        } while (round <= 15);

        return report;
    }

    public boolean checkHero(HeroDAO hero) {
        boolean dead = false;

        if (hero.getHealth() <= 0) {
            dead = true;
        }
        return dead;
    }

    public Collection<Integer> damageDealt(HeroDAO me, HeroDAO you) {
        int minDamage, maxDamage;

        Collection<Integer> output = new ArrayList<>();

        int damageDealt = 0;
        Random ran = new Random();
        double yourDefense = Math.round(you.getDefense() * 0.75);

        if (me.getMin_damage() <= yourDefense) {
            minDamage = 1;
        } else {
            minDamage = me.getMin_damage() - (int) yourDefense;
            if (you.getIgnore_dmg_percent() > 0) {
                minDamage = Math.round(minDamage - ((you.getIgnore_dmg_percent() / 100) * minDamage));
                if (minDamage <= 0) {
                    minDamage = 1;
                }
            }
        }
        if (me.getMax_damage() <= yourDefense) {
            maxDamage = 1;
        } else {
            maxDamage = me.getMax_damage() - (int) yourDefense;
            if (you.getIgnore_dmg_percent() > 0) {
                maxDamage = Math.round(maxDamage - ((you.getIgnore_dmg_percent() / 100) * maxDamage));
                if (maxDamage <= 0) {
                    maxDamage = 1;
                }
            }
        }

        damageDealt = ran.nextInt(maxDamage - minDamage + 1) + minDamage;
        int blockedDamage = 0;

        if (you.getBlock_chance() > 0) {
            if (ran.nextInt(100) < you.getBlock_chance()) {
                if (you.getBlock() <= damageDealt) {
                    blockedDamage = you.getBlock();
                } else if (you.getBlock() > damageDealt) {
                    blockedDamage = damageDealt;
                }
                damageDealt = damageDealt - you.getBlock();
                if (damageDealt <= 0) {
                    damageDealt = 0;
                }
            }
        }

        if (me.getMin_bleed() > 0) {
            float resMultiply = 1 - (Float.valueOf(you.getRes_bleed()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_bleed()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_bleed()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;
        }
        if (me.getMin_cold() > 0) {
            float resMultiply = 1 - (Float.valueOf(you.getRes_cold()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_cold()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_cold()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;

        }
        if (me.getMin_electric() > 0) {
            float resMultiply = 1 - (Float.valueOf(you.getRes_electric()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_electric()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_electric()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;
        }
        if (me.getMin_fire() > 0) {
            float resMultiply = 1 - (Float.valueOf(you.getRes_fire()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_fire()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_fire()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;
        }
        if (me.getMin_poison() > 0) {
            float resMultiply = 1 - (Float.valueOf(you.getRes_poison()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_poison()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_poison()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;
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

        if (you.getHealth() - damageDealt <= 0) {
            you.setHealth(0);
        } else {
            you.setHealth(you.getHealth() - damageDealt);
        }
        output.add(damageDealt);
        output.add(blockedDamage);
        return output;
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
            if (Math.round(difference / 2) > 30) {
                if (random < 90) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (random < 60 + Math.round(difference / 2)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if (myOffensive < yourDeffensive) {
            if (Math.round(difference / 2) > 30) {
                if (random < 30) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (random < 60 - Math.round(difference / 2)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
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
            if (hero.getMin_cold() > 0 || hero.getMax_cold() > 0) {
                hero.setMin_cold(hero.getMin_cold() + Math.round(hero.getIntelligence() / 2));
                hero.setMax_cold(hero.getMax_cold() + Math.round(hero.getIntelligence() / 2));
            }
            if (hero.getMin_fire() > 0 || hero.getMax_fire() > 0) {
                hero.setMin_fire(hero.getMin_fire() + Math.round(hero.getIntelligence() / 2));
                hero.setMax_fire(hero.getMax_fire() + Math.round(hero.getIntelligence() / 2));
            }
            if (hero.getMin_electric() > 0 || hero.getMax_electric() > 0) {
                hero.setMin_electric(hero.getMin_electric() + Math.round(hero.getIntelligence() / 2));
                hero.setMax_electric(hero.getMax_electric() + Math.round(hero.getIntelligence() / 2));
            }
        } else {
            if (hero.getMin_cold() > 0 || hero.getMax_cold() > 0) {
                hero.setMin_cold(hero.getMin_cold() + Math.round(hero.getIntelligence() / 2));
                hero.setMax_cold(hero.getMax_cold() + Math.round(hero.getIntelligence() / 2));
            }
            if (hero.getMin_fire() > 0 || hero.getMax_fire() > 0) {
                hero.setMin_fire(hero.getMin_fire() + Math.round(hero.getIntelligence() / 2));
                hero.setMax_fire(hero.getMax_fire() + Math.round(hero.getIntelligence() / 2));
            }
            if (hero.getMin_electric() > 0 || hero.getMax_electric() > 0) {
                hero.setMin_electric(hero.getMin_electric() + Math.round(hero.getIntelligence() / 2));
                hero.setMax_electric(hero.getMax_electric() + Math.round(hero.getIntelligence() / 2));
            }
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
}
