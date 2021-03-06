/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.CharShard;
import com.example.demo.model.Hero;
import com.example.demo.model.HeroDAO;
import com.example.demo.model.Monster;
import com.example.demo.model.Item;
import com.example.demo.model.ItemBase;
import com.example.demo.model.Prefix;
import com.example.demo.model.Suffix;
import com.example.demo.model.MonsterDAO;
import com.example.demo.model.Shard;
import com.example.demo.model.Stones;
import com.example.demo.repository.CharShardRepository;
import com.example.demo.repository.HeroRepository;
import com.example.demo.repository.ItemBaseRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MonsterRepository;
import com.example.demo.repository.PrefixRepository;
import com.example.demo.repository.ShardRepository;
import com.example.demo.repository.StonesRepository;
import com.example.demo.repository.SuffixRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
public class ExploreController {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemBaseRepository itemBaseRepository;

    @Autowired
    private PrefixRepository prefixRepository;

    @Autowired
    private SuffixRepository suffixRepository;

    @Autowired
    private ItemBaseRepository uniqueItemRepository;

    @Autowired
    private StonesRepository stonesRepository;

    @Autowired
    private ShardRepository shardRepository;

    @Autowired
    private CharShardRepository charShardRepository;

    public ExploreController(HeroRepository hero,
            MonsterRepository monster, ItemRepository item,
            PrefixRepository prefix, SuffixRepository suffix,
            ItemBaseRepository unique, StonesRepository stones,
            ShardRepository shard, CharShardRepository charShard) {
        this.heroRepository = hero;
        this.monsterRepository = monster;
        this.itemRepository = item;
        this.prefixRepository = prefix;
        this.suffixRepository = suffix;
        this.uniqueItemRepository = unique;
        this.stonesRepository = stones;
        this.shardRepository = shard;
        this.charShardRepository = charShard;
    }

    @GetMapping("/explore/{level}/{my_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<String> explore(@PathVariable(value = "my_id") Long my_id, @PathVariable(value = "level") Long level) {
        Collection<String> report = new ArrayList<String>();
        Random rand = new Random();

        Hero originalMe = heroRepository.getOne(my_id);
        HeroDAO me = new HeroDAO(originalMe);

        me = this.assignPercentages(me);

        int round = 1;

        List<MonsterDAO> monsters = this.getMonsters(level, me.getLevel());

        List<Long> monsterIds = new ArrayList<Long>();

        int monsterSize = monsters.size();
        int experienceToGain = 0;
        report.add(String.valueOf(monsterSize));

        for (String line : me.info()) {
            report.add(line);
        }
        for (MonsterDAO monster : monsters) {
            monsterIds.add(monster.getId());
            for (String line : monster.info()) {
                report.add(line);
            }
            experienceToGain = experienceToGain + monster.getExperience();
        }
        int random;
        int chance;

        do {
            report.add("Round " + round);
            if (me.getStunned() == 0) {
                random = rand.nextInt(monsters.size()) + 0;
                report.add(this.heroRound(me, monsters.get(random)));
                if (this.checkMonster(monsters.get(random))) {
                    monsters.remove(random);
                    if (monsters.size() == 0) {
                        break;
                    }
                    random = rand.nextInt(monsters.size()) + 0;
                }
                chance = rand.nextInt(100) + 0;
                if (me.getAttack_speed() > 100) {
                    if (chance < me.getAttack_speed() - 100) {
                        report.add(this.heroRound(me, monsters.get(random)));
                        if (this.checkMonster(monsters.get(random))) {
                            monsters.remove(random);
                            if (monsters.size() == 0) {
                                break;
                            }
                            random = rand.nextInt(monsters.size()) + 0;
                        }
                    }
                    if (chance < me.getAttack_speed() - 200) {
                        report.add(this.heroRound(me, monsters.get(random)));
                        if (this.checkMonster(monsters.get(random))) {
                            monsters.remove(random);
                            if (monsters.size() == 0) {
                                break;
                            }
                            random = rand.nextInt(monsters.size()) + 0;
                        }
                    }

                }
            } else {
                report.add(me.getChar_name() + " is stunned and cannot attack.");
                me.setStunned(me.getStunned() - 1);
            }

            for (MonsterDAO monster : monsters) {
                chance = rand.nextInt(100);
                if (monster.getStunned() == 0) {
                    report.add(this.monsterRound(monster, me));
                    if (me.getHealth() == 0) {
                        break;
                    }
                    if (monster.getAttack_speed() > 100) {
                        if (chance < monster.getAttack_speed() - 100) {
                            report.add(this.monsterRound(monster, me));
                            if (me.getHealth() == 0) {
                                break;
                            }
                        }
                        if (chance < monster.getAttack_speed() - 200) {
                            report.add(this.monsterRound(monster, me));
                            if (me.getHealth() == 0) {
                                break;
                            }
                        }
                    }
                } else {
                    report.add(monster.getMonster_name() + " is stunned and cannot attack.");
                    monster.setStunned(monster.getStunned() - 1);
                }
            }

            if (me.getHealth() > 0) {
                me.setHealth(me.getHealth() + me.getHealth_regen());
                if (me.getHealth() > me.getMax_health()) {
                    me.setHealth(me.getMax_health());
                }
            }

            for (MonsterDAO monster : monsters) {
                if (monster.getHealth() > 0) {
                    monster.setHealth(monster.getHealth() + monster.getHealth_regen());
                    if(monster.getHealth() > monster.getMax_health()) {
                        monster.setHealth(monster.getMax_health());
                    }
                }
            }

            report.add("");
            round++;
            report.add("");
        } while (me.getHealth() > 0);

        if (me.getHealth() > 0) {
            originalMe.setExperience(originalMe.getExperience() + experienceToGain);
            report.add(me.getChar_name() + " gained " + experienceToGain + " experience.");
            for (int i = 0; i < monsterSize; i++) {
                if (this.itemChance(level)) {
                    report.add(this.createItem(level, originalMe));
                }
                i++;
                if (this.bloodStoneChance(level)) {
                    Stones stones = stonesRepository.getCharStones(originalMe.getChar_id());
                    stones.setBlood_stone(stones.getBlood_stone() + 1);
                    stonesRepository.save(stones);
                    report.add("A blood stone has been found");
                }
                if (this.heartStoneChance(level)) {
                    Stones stones = stonesRepository.getCharStones(originalMe.getChar_id());
                    stones.setHeart_stone(stones.getHeart_stone() + 1);
                    stonesRepository.save(stones);
                    report.add("A heart stone has been found");
                }
                if (this.lifeStoneChance(level)) {
                    Stones stones = stonesRepository.getCharStones(originalMe.getChar_id());
                    stones.setLife_stone(stones.getLife_stone() + 1);
                    stonesRepository.save(stones);
                    report.add("A life stone has been found");
                }
                if (this.soulStoneChance(level)) {
                    Stones stones = stonesRepository.getCharStones(originalMe.getChar_id());
                    stones.setSoul_stone(stones.getSoul_stone() + 1);
                    stonesRepository.save(stones);
                    report.add("A soul stone has been found");
                }
                if (this.relicShard(level)) {
                    List<Shard> relics = shardRepository.getRelics();
                    Collections.shuffle(relics);
                    CharShard charShard = new CharShard();
                    charShard.setHero(originalMe);
                    charShard.setLevel(1);
                    charShard.setShard(relics.get(0));
                    charShardRepository.save(charShard);
                    report.add("An " + relics.get(0).getName() + " has been found");
                }
            }
            for (Long number : monsterIds) {
                if (this.monsterShard(level)) {
                    Shard shard = shardRepository.getOne(number);
                    CharShard charShard = new CharShard();
                    charShard.setShard(shard);
                    charShard.setHero(originalMe);
                    charShard.setLevel(1);
                    charShardRepository.save(charShard);
                    report.add("A " + shard.getName() + " has been found");
                }
            }
        } else {
            report.add(me.getChar_name() + " was defeated.");
        }
        heroRepository.save(originalMe);
        return report;
    }

    public boolean monsterShard(Long level) {
        Random rand = new Random();

        int random = rand.nextInt(100);
        if (random < 1 + level) {
            return true;
        } else {
            return false;
        }
    }

    public boolean relicShard(Long level) {
        Random rand = new Random();

        int random = rand.nextInt(100);
        if (random < 1 + level) {
            return true;
        } else {
            return false;
        }
    }

    public boolean bloodStoneChance(Long level) {
        Random rand = new Random();

        int random = rand.nextInt(100);
        if (random < 3 + level) {
            return true;
        } else {
            return false;
        }
    }

    public boolean heartStoneChance(Long level) {
        Random rand = new Random();

        int random = rand.nextInt(100);
        if (random < 2 + level) {
            return true;
        } else {
            return false;
        }
    }

    public boolean lifeStoneChance(Long level) {
        Random rand = new Random();

        int random = rand.nextInt(100);
        if (random < 2 + level) {
            return true;
        } else {
            return false;
        }
    }

    public boolean soulStoneChance(Long level) {
        Random rand = new Random();

        int random = rand.nextInt(100);
        if (random < 1 + level) {
            return true;
        } else {
            return false;
        }
    }

    public String createItem(Long level, Hero hero) {
        Item item = new Item();
        Random rand = new Random();
        List<ItemBase> baseCollection = new ArrayList<>();
        if (level <= 3) {
            baseCollection = itemBaseRepository.getShopItems();
        } else if (level > 3 && level < 10) {
            baseCollection = itemBaseRepository.getItemsNormal();
        } else if (level <= 10 && level < 15) {
            baseCollection = itemBaseRepository.getEpicItems();
        } else {
            baseCollection = itemBaseRepository.getLegendaryItems();
        }
        int random = rand.nextInt(baseCollection.size());
        item.setItemBase(baseCollection.get(random));
        if (this.prefSuffChance(level)) {
            List<Prefix> prefixes = prefixRepository.getPrefixes(item.getItemBase().getType());
            random = rand.nextInt(prefixes.size());
            item.setPrefix(prefixes.get(random));
        }
        if (this.prefSuffChance(level)) {
            List<Suffix> suffixes = suffixRepository.getSuffixes(item.getItemBase().getType());
            random = rand.nextInt(suffixes.size());
            item.setSuffix(suffixes.get(random));
        }
        item.setHero(hero);
        item.setEquipped("no");
        item.setLevel(0);
        itemRepository.save(item);
        String output = "";
        if (item.getPrefix() != null) {
            output = item.getPrefix().getName();
        }
        output = output + " " + item.getItemBase().getName();
        if (item.getSuffix() != null) {
            output = output + " " + item.getSuffix().getName();
        }
        output = output + " has been found.";

        return output;
    }

    public boolean prefSuffChance(Long level) {
        Random rand = new Random();
        int random = rand.nextInt(100);
        if (random < level * 2) {
            return true;
        }
        return false;
    }

    public boolean itemChance(Long level) {
        Random rand = new Random();
        int random = rand.nextInt(100);
        if (random < level * 2) {
            return true;
        }
        return false;
    }

    public boolean checkMonster(MonsterDAO monster) {
        boolean dead = false;

        if (monster.getHealth() <= 0) {
            dead = true;
        }
        return dead;
    }

    public boolean checkHero(Hero hero) {
        boolean dead = false;

        if (hero.getHealth() <= 0) {
            dead = true;
        }

        return dead;
    }

    public String heroRound(HeroDAO me, MonsterDAO monster) {
        String output;
        String slowed = monster.getSlowed();
        if (this.heroHit(me, monster) == true) {
            Collection<Integer> damageDealt = this.heroDamage(me, monster);
            if (!damageDealt.toArray()[1].toString().equals("0")) {
                output = me.getChar_name() + " uses (weapon) and deals " + damageDealt.toArray()[0] + " damage to " + monster.getMonster_name() + "(" + damageDealt.toArray()[1] + " blocked by (shield)). (" + monster.getHealth() + " health remaining)";
            } else {
                output = me.getChar_name() + " uses (weapon) and deals " + damageDealt.toArray()[0] + " damage to " + monster.getMonster_name() + "(" + monster.getHealth() + " health remaining)";
            }
            if (slowed == "no" && me.getSlow() > 0) {
                output = output + me.getChar_name() + " slows " + monster.getMonster_name() + " by " + me.getSlow() + "%.";
            }
        } else {
            output = me.getChar_name() + " uses (weapon) to hit " + monster.getMonster_name() + " but it was a miss";
        }
        return output;
    }

    public String monsterRound(MonsterDAO me, HeroDAO you) {
        if (this.monsterHit(me, you) == true) {
            Collection<Integer> damageDealt = this.monsterDamage(me, you);
            if (!damageDealt.toArray()[1].toString().equals("0")) {
                return me.getMonster_name() + " attacks and deals " + damageDealt.toArray()[0] + " damage to " + you.getChar_name() + "(" + damageDealt.toArray()[1] + " blocked by (shield)). (" + you.getHealth() + " health remaining)";
            } else {
                return me.getMonster_name() + " attacks and deals " + damageDealt.toArray()[0] + " damage to " + you.getChar_name() + "(" + you.getHealth() + " health remaining)";
            }
        } else {
            return me.getMonster_name() + " attacks " + you.getChar_name() + " but it was a miss";
        }
    }

    public boolean monsterHit(MonsterDAO me, HeroDAO you) {
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

    public Collection<Integer> monsterDamage(MonsterDAO me, HeroDAO you) {
        int minDamage, maxDamage;

        Collection<Integer> output = new ArrayList<>();

        int damageDealt = 0;
        Random ran = new Random();
        double yourDefense = Math.round(you.getDefense() * 0.75);

        if (me.getMin_damage() <= yourDefense) {
            minDamage = 1;
        } else {
            minDamage = me.getMin_damage() - (int) yourDefense;
        }
        if (me.getMax_damage() <= yourDefense) {
            maxDamage = 1;
        } else {
            maxDamage = me.getMax_damage() - (int) yourDefense;
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

        if (you.getIgnore_dmg_percent() > 0) {
            damageDealt = damageDealt - (damageDealt * (you.getIgnore_dmg_percent() / 100));
        }

        you.setHealth(you.getHealth() - damageDealt);
        if (you.getHealth() < 0) {
            you.setHealth(0);
        }
        output.add(damageDealt);
        output.add(blockedDamage);
        return output;
    }

    public Collection<Integer> heroDamage(HeroDAO me, MonsterDAO monster) {
        int minDamage, maxDamage;

        Collection<Integer> output = new ArrayList<>();

        int damageDealt = 0;
        Random ran = new Random();
        double yourDefense = Math.round(monster.getDefense() * 0.75);

        if (me.getMin_damage() <= monster.getDefense()) {
            minDamage = 1;
        } else {
            minDamage = me.getMin_damage() - (int) yourDefense;
        }
        if (me.getMax_damage() <= monster.getDefense()) {
            maxDamage = 1;
        } else {
            maxDamage = me.getMax_damage() - (int) yourDefense;
        }

        damageDealt = ran.nextInt(maxDamage - minDamage + 1) + minDamage;
        int blockedDamage = 0;

        if (monster.getBlock_chance() > 0) {
            if (ran.nextInt(100) < monster.getBlock_chance()) {
                if (monster.getBlock() <= damageDealt) {
                    blockedDamage = monster.getBlock();
                } else if (monster.getBlock() > damageDealt) {
                    blockedDamage = damageDealt;
                }
                damageDealt = damageDealt - monster.getBlock();
                if (damageDealt <= 0) {
                    damageDealt = 0;
                }
            }
        }

        if (me.getMin_bleed() > 0) {
            float resMultiply = 1 - (Float.valueOf(monster.getRes_bleed()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_bleed()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_bleed()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;
        }
        if (me.getMin_cold() > 0) {
            float resMultiply = 1 - (Float.valueOf(monster.getRes_cold()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_cold()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_cold()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;

        }
        if (me.getMin_electric() > 0) {
            float resMultiply = 1 - (Float.valueOf(monster.getRes_electric()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_electric()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_electric()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;
        }
        if (me.getMin_fire() > 0) {
            float resMultiply = 1 - (Float.valueOf(monster.getRes_fire()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_fire()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_fire()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;
        }
        if (me.getMin_poison() > 0) {
            float resMultiply = 1 - (Float.valueOf(monster.getRes_poison()) / 100);
            minDamage = Math.round(resMultiply * Float.valueOf(me.getMin_poison()));
            maxDamage = Math.round(resMultiply * Float.valueOf(me.getMax_poison()));
            damageDealt = damageDealt + ran.nextInt(maxDamage - minDamage + 1) + minDamage;
        }

        if (me.getStun_chance() > 0) {
            if (ran.nextInt(100) < me.getStun_chance()) {
                monster.setStunned(1);
            }
        }

        if (me.getSlow() > 0 && monster.getSlowed() == "no") {
            monster.setSlowed("yes");
            monster.setAttack_speed(monster.getAttack_speed() - me.getSlow());
        }

        monster.setHealth(monster.getHealth() - damageDealt);
        if (monster.getHealth() < 0) {
            monster.setHealth(0);
        }

        output.add(damageDealt);
        output.add(blockedDamage);
        return output;
    }

    public boolean heroHit(HeroDAO me, MonsterDAO monster) {
        Random rand = new Random();
        int random = rand.nextInt(100);

        int myOffensive = me.getOff_ability();
        int yourDeffensive = monster.getDef_ability();

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

    public List<MonsterDAO> getMonsters(Long level, int myLevel) {
        Random rand = new Random();
        int random;

        List<Monster> monsters = monsterRepository.getMonsters(Integer.valueOf(level.intValue()));

        List<MonsterDAO> monstersDAO = new ArrayList<MonsterDAO>();
        Collections.shuffle(monsters);
        List<MonsterDAO> duplicates = new ArrayList<MonsterDAO>();

        monstersDAO.add(this.transformMonster(monsters.get(0)));
        monsters.remove(0);

        int nextChance = 50;
        
        for (Monster monster : monsters) {
            random = rand.nextInt(100) + 1;
            if (random < nextChance) {
                monstersDAO.add(this.transformMonster(monster));
                nextChance--;
            }
        }
        for (MonsterDAO monsterDAO : monstersDAO) {
            random = rand.nextInt(100) + 1;
            while (random < nextChance) {
                MonsterDAO duplicate = new MonsterDAO(monsterDAO);
                random = rand.nextInt(100);
                duplicates.add(duplicate);
                nextChance--;
            }
            //this.assingLevels(monster);
        }
        monstersDAO.addAll(duplicates);

        for (MonsterDAO monsterDAO : monstersDAO) {
            this.assingLevels(monsterDAO, level, myLevel);
        }
        /**
         * int number = 1; for (Monster duplicate : duplicates) {
         * duplicate.setMonster_name(duplicate.getMonster_name() + "(" + number
         * + ")"); monsters.add(duplicate); number++; } return monsters;
         *
         */
        return monstersDAO;
    }

    public MonsterDAO assingLevels(MonsterDAO monsters, Long level, int myLevel) {
        Random rand = new Random();
        int random;
        int intLevel = Math.toIntExact(level);
        int myLevelMulti = myLevel / 5;
        MonsterDAO monster = monsters;

        if(monster.getLevel() == intLevel) {
            random = rand.nextInt(50+myLevelMulti) + 1+myLevelMulti;
        } else {
            random = rand.nextInt(50+intLevel+myLevelMulti) + 1+(intLevel*2)+myLevelMulti;
        }
        
        monster.setLevel(random);
        monster.setId(monsters.getId());
        monster.setHealth((monster.getHealth() * random / 100) + monster.getHealth());
        monster.setRes_fire((monster.getRes_fire() * random / 100) + monster.getRes_fire());
        monster.setRes_cold((monster.getRes_cold() * random / 100) + monster.getRes_cold());
        monster.setRes_electric((monster.getRes_electric() * random / 100) + monster.getRes_electric());
        monster.setRes_poison((monster.getRes_poison() * random / 100) + monster.getRes_poison());
        monster.setDefense((monster.getDefense() * random / 100) + monster.getDefense());
        monster.setBlock_chance((monster.getBlock_chance() * random / 100) + monster.getBlock_chance());
        monster.setBlock((monster.getBlock() * random / 100) + monster.getBlock());
        monster.setRes_bleed((monster.getRes_bleed() * random / 100) + monster.getRes_bleed());
        monster.setRes_stun((monster.getRes_stun() * random / 100) + monster.getRes_stun());
        monster.setHealth_regen((monster.getHealth_regen() * random / 100) + monster.getHealth_regen());
        monster.setOff_ability((monster.getOff_ability() * random / 100) + monster.getOff_ability());
        monster.setDef_ability((monster.getDef_ability() * random / 100) + monster.getDef_ability());
        monster.setMin_damage((monster.getMin_damage() * random / 100) + monster.getMin_damage());
        monster.setMax_damage((monster.getMax_damage() * random / 100) + monster.getMax_damage());
        monster.setMin_cold((monster.getMin_cold() * random / 100) + monster.getMin_cold());
        monster.setMin_electric((monster.getMin_electric() * random / 100) + monster.getMin_electric());
        monster.setMax_cold((monster.getMax_cold() * random / 100) + monster.getMax_cold());
        monster.setMax_electric((monster.getMax_electric() * random / 100) + monster.getMax_electric());
        monster.setMin_fire((monster.getMin_fire() * random / 100) + monster.getMin_fire());
        monster.setMax_fire((monster.getMax_fire() * random / 100) + monster.getMax_fire());
        monster.setMin_poison((monster.getMin_poison() * random / 100) + monster.getMin_poison());
        monster.setMax_poison((monster.getMax_poison() * random / 100) + monster.getMax_poison());
        monster.setMin_bleed((monster.getMin_bleed() * random / 100) + monster.getMin_bleed());
        monster.setMax_bleed((monster.getMax_bleed() * random / 100) + monster.getMax_bleed());
        monster.setStun_chance((monster.getStun_chance() * random / 100) + monster.getStun_chance());
        monster.setAttack_speed((monster.getAttack_speed() * random / 100) + monster.getAttack_speed());
        monster.setExperience((monster.getExperience() * random / 100) + monster.getExperience());
        monster.setSlowed("no");
        monster.setStunned(0);
        return monster;
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

    public MonsterDAO transformMonster(Monster monster) {
        MonsterDAO monsterDAO = new MonsterDAO();
        monsterDAO.setId(monster.getMonster_id());
        monsterDAO.setLevel(monster.getLevel());
        monsterDAO.setAttack_speed(monster.getAttack_speed());
        monsterDAO.setBlock(monster.getBlock());
        monsterDAO.setBlock_chance(monster.getBlock_chance());
        monsterDAO.setDef_ability(monster.getDef_ability());
        monsterDAO.setDefense(monster.getDefense());
        monsterDAO.setExperience(monster.getExperience());
        monsterDAO.setMax_health(monster.getHealth());
        monsterDAO.setHealth(monster.getHealth());
        monsterDAO.setHealth_regen(monster.getHealth_regen());
        monsterDAO.setLevel(monster.getLevel());
        monsterDAO.setMax_bleed(monster.getMax_bleed());
        monsterDAO.setMax_cold(monster.getMax_cold());
        monsterDAO.setMax_damage(monster.getMax_damage());
        monsterDAO.setMax_electric(monster.getMax_electric());
        monsterDAO.setMax_fire(monster.getMax_fire());
        monsterDAO.setMax_poison(monster.getMax_poison());
        monsterDAO.setMin_bleed(monster.getMin_bleed());
        monsterDAO.setMin_cold(monster.getMin_cold());
        monsterDAO.setMin_damage(monster.getMin_damage());
        monsterDAO.setMin_electric(monster.getMin_electric());
        monsterDAO.setMin_fire(monster.getMin_fire());
        monsterDAO.setMin_poison(monster.getMin_poison());
        monsterDAO.setMonster_name(monster.getMonster_name());
        monsterDAO.setOff_ability(monster.getOff_ability());
        monsterDAO.setRes_bleed(monster.getRes_bleed());
        monsterDAO.setRes_cold(monster.getRes_cold());
        monsterDAO.setRes_electric(monster.getRes_electric());
        monsterDAO.setRes_fire(monster.getRes_fire());
        monsterDAO.setRes_poison(monster.getRes_poison());
        monsterDAO.setRes_stun(monster.getRes_stun());
        monsterDAO.setStun_chance(monster.getStun_chance());
        monsterDAO.setStunned(0);
        monsterDAO.setSlowed("no");
        return monsterDAO;
    }

}
