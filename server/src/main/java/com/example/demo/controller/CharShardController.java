/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.CharShard;
import com.example.demo.model.Item;
import com.example.demo.repository.CharShardRepository;
import com.example.demo.repository.ItemRepository;
import java.util.Collection;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grzegorz
 */
@RestController
public class CharShardController {

    @Autowired
    private CharShardRepository repository;

    @Autowired
    private ItemRepository itemRepository;

    public CharShardController(CharShardRepository repo,
            ItemRepository itemRepository) {
        this.repository = repo;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/getCharShards/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<CharShard> getCharShards(@PathVariable(value = "id") Long char_id) {
        return repository.getCharShards(char_id);
    }

    @GetMapping("/getShardsToAssemble/{id}/{shard_id}/{char_shard_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<CharShard> getShardsToAssemble(@PathVariable(value = "id") Long char_id,
            @PathVariable(value = "shard_id") Long shard_id,
            @PathVariable(value = "char_shard_id") Long char_shard_id) {
        return repository.getShardToAssemble(char_id, shard_id, char_shard_id);
    }

    @PostMapping("/assembleShards/{first}/{second}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void assembleShards(@PathVariable(value = "first") Long shardIdOne,
            @PathVariable(value = "second") Long shardIdTwo) {
        CharShard main = repository.getOne(shardIdOne);
        CharShard shard = repository.getOne(shardIdTwo);

        Integer mainLevel = main.getLevel();
        System.out.println(mainLevel);
        Integer shardLevel = shard.getLevel();
        System.out.println(shardLevel);

        main.setLevel(mainLevel + shardLevel);
        if (main.getLevel() >= 5) {
            main.setLevel(5);
            this.setBonuses(main);
        }
        repository.save(main);
    }

    @DeleteMapping("/deleteShard/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteShard(@PathVariable(value = "id") Long shardId) {
        repository.deleteById(shardId);
    }

    @PostMapping("/enchanceItem/{shard_id}/{item_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void enchanceItem(@PathVariable(value = "shard_id") Long shard_id,
            @PathVariable(value = "item_id") Long item_id) {
        Item item = itemRepository.getOne(item_id);
        CharShard shard = repository.getOne(shard_id);
        if (item.getShard_id() == null) {
            item.setShard_id(shard);
            shard.setAssigned("yes");
            repository.save(shard);
        } else {
            int level = item.getShard_id().getLevel() + shard.getLevel();
            if (level > 5) {
                level = 5;
            }
            item.getShard_id().setLevel(level);
            this.setBonuses(shard);
            repository.delete(shard);
        }
        itemRepository.save(item);
    }

    public CharShard setBonuses(CharShard shard) {
        Random rand = new Random();
        int random = rand.nextInt(100);
        if (shard.getShard().getShard_id() == 1) {
            this.boarHide(shard, random);
        } else if (shard.getShard().getShard_id() == 2) {
            this.spiderWeb(shard, random);
        } else if (shard.getShard().getShard_id() == 4) {
            this.coldEssence(shard, random);
        } else if (shard.getShard().getShard_id() == 5) {
            this.demonBlood(shard, random);
        } else if (shard.getShard().getShard_id() == 6) {
            this.diseasedPlumage(shard, random);
        } else if (shard.getShard().getShard_id() == 7) {
            this.dragonBlood(shard, random);
        } else if (shard.getShard().getShard_id() == 8) {
            this.etir(shard, random);
        } else if (shard.getShard().getShard_id() == 9) {
            this.fungoidSpores(shard, random);
        } else if (shard.getShard().getShard_id() == 10) {
            this.golemHeart(shard, random);
        } else if (shard.getShard().getShard_id() == 11) {
            this.hagsSkin(shard, random);
        } else if (shard.getShard().getShard_id() == 12) {
            this.hydradonHide(shard, random);
        } else if (shard.getShard().getShard_id() == 13) {
            this.lupineClaw(shard, random);
        } else if (shard.getShard().getShard_id() == 14) {
            this.mechanicalParts(shard, random);
        } else if (shard.getShard().getShard_id() == 15) {
            this.pengClaw(shard, random);
        } else if (shard.getShard().getShard_id() == 16) {
            this.primalMagma(shard, random);
        } else if (shard.getShard().getShard_id() == 17) {
            this.pristinePlumage(shard, random);
        } else if (shard.getShard().getShard_id() == 18) {
            this.raptorTooth(shard, random);
        } else if (shard.getShard().getShard_id() == 19) {
            this.saberClaw(shard, random);
        } else if (shard.getShard().getShard_id() == 20) {
            this.spinyShell(shard, random);
        } else if (shard.getShard().getShard_id() == 21) {
            this.turtleShell(shard, random);
        } else if (shard.getShard().getShard_id() == 22) {
            this.venomSac(shard, random);
        } else if (shard.getShard().getShard_id() == 23) {
            this.vinyGrowth(shard, random);
        } else if (shard.getShard().getShard_id() == 24) {
            this.yetiFur(shard, random);
        } else if (shard.getShard().getShard_id() == 25) {
            this.amunRa(shard, random);
        } else if (shard.getShard().getShard_id() == 26) {
            this.archmiedes(shard, random);
        } else if (shard.getShard().getShard_id() == 27) {
            this.artemis(shard, random);
        } else if (shard.getShard().getShard_id() == 28) {
            this.atlas(shard, random);
        } else if (shard.getShard().getShard_id() == 29) {
            this.dionysus(shard, random);
        } else if (shard.getShard().getShard_id() == 30) {
            this.donar(shard, random);
        } else if (shard.getShard().getShard_id() == 31) {
            this.guan(shard, random);
        } else if (shard.getShard().getShard_id() == 32) {
            this.herakles(shard, random);
        } else if (shard.getShard().getShard_id() == 33) {
            this.hermes(shard, random);
        } else if (shard.getShard().getShard_id() == 34) {
            this.linezhas(shard, random);
        } else if (shard.getShard().getShard_id() == 35) {
            this.poseidon(shard, random);
        } else if (shard.getShard().getShard_id() == 36) {
            this.prometheus(shard, random);
        } else if (shard.getShard().getShard_id() == 37) {
            this.shen(shard, random);
        } else if (shard.getShard().getShard_id() == 38) {
            this.sigurds(shard, random);
        } else if (shard.getShard().getShard_id() == 39) {
            this.ankh(shard, random);
        } else if (shard.getShard().getShard_id() == 40) {
            this.thanatos(shard, random);
        } else if (shard.getShard().getShard_id() == 41) {
            this.tartarus(shard, random);
        } else if (shard.getShard().getShard_id() == 42) {
            this.hammurabi(shard, random);
        } else if (shard.getShard().getShard_id() == 43) {
            this.odyseuss(shard, random);
        } else if (shard.getShard().getShard_id() == 44) {
            this.dragon(shard, random);
        } else if (shard.getShard().getShard_id() == 45) {
            this.ajax(shard, random);
        } else if (shard.getShard().getShard_id() == 46) {
            this.jade(shard, random);
        } else if (shard.getShard().getShard_id() == 47) {
            this.monkey(shard, random);
        } else if (shard.getShard().getShard_id() == 48) {
            this.hector(shard, random);
        } else if (shard.getShard().getShard_id() == 49) {
            this.star(shard, random);
        } else if (shard.getShard().getShard_id() == 50) {
            this.stew(shard, random);
        } else if (shard.getShard().getShard_id() == 51) {
            this.horus(shard, random);
        } else if (shard.getShard().getShard_id() == 52) {
            this.achilles(shard, random);
        } else if (shard.getShard().getShard_id() == 53) {
            this.bloodletting(shard, random);
        } else if (shard.getShard().getShard_id() == 54) {
            this.thunderbolt(shard, random);
        }
        return shard;
    }

    public CharShard thunderbolt(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 4) {
            shard.setMin_damage(5);
            shard.setMax_damage(9);
        } else if (random >= 4 && random < 6) {
            shard.setDmg_elemental(6);
        } else if (random >= 6 && random < 14) {
            shard.setSlow(11);
        } else if (random >= 14 && random < 24) {
            shard.setMin_damage(4);
            shard.setMax_damage(6);
        } else if (random >= 24 && random < 34) {
            shard.setDmg_elemental(5);
        } else if (random >= 34 && random < 45) {
            shard.setAgility(11);
        } else if (random >= 45 && random < 56) {
            shard.setDmg_electric_percent(15);
        } else {
            shard.setStun_chance(10);
        }

        return shard;
    }

    public CharShard bloodletting(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 9) {
            shard.setSlow(11);
        } else if (random >= 9 && random < 19) {
            shard.setMin_damage(6);
            shard.setMax_damage(12);
        } else if (random >= 19 && random < 28) {
            shard.setMin_fire(6);
            shard.setMax_fire(12);
        } else if (random >= 28 && random < 37) {
            shard.setDmg_bleed(10);
        } else if (random >= 37 && random < 46) {
            shard.setDmg_bleed_percent(5);
        } else {
            shard.setStun_chance(10);
        }

        return shard;
    }

    public CharShard achilles(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 6) {
            shard.setMin_damage(5);
            shard.setMax_damage(9);
        } else if (random >= 6 && random < 15) {
            shard.setSlow(11);
        } else if (random >= 15 && random < 26) {
            shard.setAttack_speed(8);
        } else if (random >= 26 && random < 37) {
            shard.setStrength(11);
        } else if (random >= 37 && random < 48) {
            shard.setStun_chance(10);
        } else if (random >= 48 && random < 59) {
            shard.setAgility(11);
        } else if (random >= 59 && random < 70) {
            shard.setDmg_percent(10);
        } else {
            shard.setMin_damage(4);
            shard.setMax_damage(6);
        }

        return shard;
    }

    public CharShard horus(CharShard shard, int random) {
        if (random < 10) {
            shard.setRes_bleed(15);
        } else if (random >= 10 && random < 20) {
            shard.setDef_ability(35);
        } else if (random >= 20 && random < 30) {
            shard.setRes_poison(16);
        } else if (random >= 30 && random < 40) {
            shard.setRes_electric(20);
        } else if (random >= 40 && random < 50) {
            shard.setRes_fire(20);
        } else if (random >= 50 && random < 60) {
            shard.setRes_cold(20);
        } else {
            shard.setArmor(15);
        }

        return shard;
    }

    public CharShard stew(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 6) {
            shard.setRes_bleed(15);
        } else if (random >= 6 && random < 11) {
            shard.setSlow(11);
        } else if (random >= 11 && random < 18) {
            shard.setOff_ability(25);
        } else if (random >= 18 && random < 25) {
            shard.setStrength(11);
        } else if (random >= 25 && random < 32) {
            shard.setStun_chance(10);
        } else if (random >= 32 && random < 39) {
            shard.setDef_ability(25);
        } else if (random >= 39 && random < 46) {
            shard.setArmor(15);
        } else if (random >= 46 && random < 53) {
            shard.setDmg_percent(10);
        } else if (random >= 53 && random < 73) {
            shard.setStrength_percent(5);
        } else {
            shard.setHealth(95);
        }

        return shard;
    }

    public CharShard star(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 12) {
            shard.setSlow(11);
        } else if (random >= 12 && random < 25) {
            shard.setDmg_elemental(14);
        } else if (random >= 25 && random < 38) {
            shard.setAttack_speed(8);
        } else {
            shard.setStun_chance(10);
        }

        return shard;
    }

    public CharShard hector(CharShard shard, int random) {
        if (random < 15) {
            shard.setStrength(11);
        } else if (random >= 15 && random < 30) {
            shard.setDef_ability(45);
        } else if (random >= 30 && random < 45) {
            shard.setHealth(145);
        } else if (random >= 45 && random < 50) {
            shard.setHealth_regen(10);
        } else if (random >= 50 && random < 60) {
            shard.setRes_stun(10);
        } else {
            shard.setStrength(6);
        }

        return shard;
    }

    public CharShard monkey(CharShard shard, int random) {
        if (random < 13) {
            shard.setAttack_speed(10);
        } else if (random >= 13 && random < 26) {
            shard.setAgility(18);
        } else if (random >= 26 && random < 39) {
            shard.setArmor(15);
        } else if (random >= 39 && random < 52) {
            shard.setDef_ability(35);
        } else {
            shard.setIntelligence(18);
        }

        return shard;
    }

    public CharShard jade(CharShard shard, int random) {
        if (random < 3) {
            shard.setRes_cold(8);
            shard.setRes_electric(8);
            shard.setRes_fire(8);
        } else if (random >= 3 && random < 10) {
            shard.setRes_cold(10);
        } else if (random >= 10 && random < 17) {
            shard.setRes_electric(10);
        } else if (random >= 17 && random < 24) {
            shard.setRes_fire(10);
        } else if (random >= 24 && random < 37) {
            shard.setIntelligence(11);
        } else if (random >= 37 && random < 50) {
            shard.setArmor(15);
        } else {
            shard.setHealth(95);
        }

        return shard;
    }

    public CharShard ajax(CharShard shard, int random) {
        if (random < 13) {
            shard.setRes_bleed(15);
        } else if (random >= 13 && random < 26) {
            shard.setDef_ability(25);
        } else if (random >= 26 && random < 39) {
            shard.setHealth(145);
        } else if (random >= 39 && random < 52) {
            shard.setArmor(15);
        } else if (random >= 52 && random < 65) {
            shard.setRes_cold(8);
            shard.setRes_electric(8);
            shard.setRes_fire(8);
        } else if (random >= 65 && random < 78) {
            shard.setHealth_regen(10);
        } else {
            shard.setRes_stun(10);
        }

        return shard;
    }

    public CharShard dragon(CharShard shard, int random) {
        if (random < 3) {
            shard.setRes_cold(8);
            shard.setRes_electric(8);
            shard.setRes_fire(8);
        } else if (random >= 3 && random < 19) {
            shard.setHealth_regen(13);
        } else if (random >= 19 && random < 35) {
            shard.setAgility(18);
        } else if (random >= 35 && random < 51) {
            shard.setArmor(15);
        } else {
            shard.setHealth(95);
        }

        return shard;
    }

    public CharShard odyseuss(CharShard shard, int random) {
        if (random < 4) {
            shard.setRes_cold(8);
            shard.setRes_electric(8);
            shard.setRes_fire(8);
        } else if (random >= 4 && random < 15) {
            shard.setRes_cold(10);
        } else if (random >= 15 && random < 26) {
            shard.setRes_electric(10);
        } else if (random >= 26 && random < 37) {
            shard.setRes_fire(10);
        } else if (random >= 37 && random < 59) {
            shard.setIntelligence(11);
        } else {
            shard.setIntelligence(6);
        }

        return shard;
    }

    public CharShard hammurabi(CharShard shard, int random) {
        if (random < 4) {
            shard.setRes_cold(8);
            shard.setRes_electric(8);
            shard.setRes_fire(8);
        } else if (random >= 4 && random < 15) {
            shard.setRes_cold(10);
        } else if (random >= 15 && random < 26) {
            shard.setRes_electric(10);
        } else if (random >= 26 && random < 37) {
            shard.setRes_fire(10);
        } else if (random >= 37 && random < 59) {
            shard.setIntelligence_percent(5);
        } else {
            shard.setIntelligence(21);
        }

        return shard;
    }

    public CharShard tartarus(CharShard shard, int random) {
        if (random < 3) {
            shard.setSlow(14);
        } else if (random >= 6 && random < 13) {
            shard.setDmg_cold(10);
        } else if (random >= 13 && random < 20) {
            shard.setDmg_bleed(14);
        } else if (random >= 20 && random < 32) {
            shard.setDmg_cold_percent(20);
        } else if (random >= 32 && random < 48) {
            shard.setStun_chance(10);
        } else if (random >= 48 && random < 64) {
            shard.setMin_cold(5);
            shard.setMax_cold(9);
        } else if (random >= 64 && random < 80) {
            shard.setDmg_elemental(14);
        } else {
            shard.setSlow(11);
        }

        return shard;
    }

    public CharShard thanatos(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 12) {
            shard.setSlow(11);
        } else if (random >= 12 && random < 25) {
            shard.setDmg_elemental(14);
        } else if (random >= 25 && random < 38) {
            shard.setAttack_speed(8);
        } else {
            shard.setStun_chance(10);
        }

        return shard;
    }

    public CharShard ankh(CharShard shard, int random) {
        if (random < 3) {
            shard.setRes_cold(8);
            shard.setRes_electric(8);
            shard.setRes_fire(8);
        } else if (random >= 3 && random < 10) {
            shard.setRes_cold(10);
        } else if (random >= 10 && random < 17) {
            shard.setRes_electric(10);
        } else if (random >= 17 && random < 24) {
            shard.setRes_fire(10);
        } else if (random >= 24 && random < 38) {
            shard.setRes_bleed(15);
        } else {
            shard.setHealth(95);
        }

        return shard;
    }

    public CharShard sigurds(CharShard shard, int random) {
        if (random < 6) {
            shard.setAttack_speed(10);
        } else if (random >= 6 && random < 18) {
            shard.setRes_bleed(15);
        } else if (random >= 18 && random < 30) {
            shard.setAttack_speed(8);
        } else if (random >= 30 && random < 42) {
            shard.setAgility(18);
        } else if (random >= 42 && random < 54) {
            shard.setArmor(15);
        } else {
            shard.setDef_ability(35);
        }

        return shard;
    }

    public CharShard shen(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 15) {
            shard.setSlow(11);
        } else if (random >= 15 && random < 32) {
            shard.setMin_damage(6);
            shard.setMax_damage(12);
        } else if (random >= 32 && random < 50) {
            shard.setDmg_poison_percent(10);
        } else {
            shard.setStun_chance(10);
        }

        return shard;
    }

    public CharShard prometheus(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 3) {
            shard.setMin_damage(5);
            shard.setMax_damage(9);
        } else if (random >= 3 && random < 4) {
            shard.setMin_fire(5);
            shard.setMax_fire(9);
        } else if (random >= 4 && random < 13) {
            shard.setSlow(11);
        } else if (random >= 13 && random < 24) {
            shard.setMin_damage(4);
            shard.setMax_damage(6);
        } else if (random >= 24 && random < 35) {
            shard.setMin_fire(4);
            shard.setMax_fire(6);
        } else if (random >= 35 && random < 47) {
            shard.setRes_fire(23);
        } else if (random >= 47 && random < 59) {
            shard.setDmg_fire(12);
        } else if (random >= 59 && random < 71) {
            shard.setDmg_fire_percent(13);
        } else {
            shard.setAttack_speed(8);
        }

        return shard;
    }

    public CharShard poseidon(CharShard shard, int random) {
        if (random < 25) {
            shard.setArmor(21);
        } else if (random >= 25 && random < 50) {
            shard.setRes_electric(10);
        } else if (random >= 50 && random < 75) {
            shard.setDef_ability(35);
        } else {
            shard.setIntelligence(21);
        }

        return shard;
    }

    public CharShard linezhas(CharShard shard, int random) {
        if (random < 6) {
            shard.setAttack_speed(10);
        } else if (random >= 6 && random < 18) {
            shard.setRes_bleed(15);
        } else if (random >= 18 && random < 30) {
            shard.setAttack_speed(8);
        } else if (random >= 30 && random < 42) {
            shard.setAgility(18);
        } else if (random >= 42 && random < 54) {
            shard.setArmor(15);
        } else {
            shard.setDef_ability(35);
        }

        return shard;
    }

    public CharShard hermes(CharShard shard, int random) {
        if (random < 7) {
            shard.setRes_poison(20);
        } else if (random >= 7 && random < 14) {
            shard.setRes_electric(20);
        } else if (random >= 14 && random < 21) {
            shard.setRes_fire(20);
        } else if (random >= 21 && random < 28) {
            shard.setRes_cold(20);
        } else if (random >= 28 && random < 35) {
            shard.setHealth_regen(10);
        } else if (random >= 35 && random < 42) {
            shard.setArmor(5);
        } else if (random >= 42 && random < 49) {
            shard.setStrength(7);
        } else if (random >= 49 && random < 56) {
            shard.setOff_ability(50);
        } else if (random >= 56 && random < 63) {
            shard.setHealth(50);
        } else if (random >= 63 && random < 70) {
            shard.setIntelligence(7);
        } else if (random >= 70 && random < 77) {
            shard.setAgility(7);
        } else if (random >= 77 && random < 84) {
            shard.setDef_ability(50);
        } else {
            shard.setAttack_speed(3);
        }

        return shard;
    }

    public CharShard herakles(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 6) {
            shard.setRes_bleed(15);
        } else if (random >= 6 && random < 11) {
            shard.setSlow(11);
        } else if (random >= 11 && random < 18) {
            shard.setOff_ability(25);
        } else if (random >= 18 && random < 25) {
            shard.setStrength(11);
        } else if (random >= 25 && random < 32) {
            shard.setStun_chance(10);
        } else if (random >= 32 && random < 39) {
            shard.setDef_ability(25);
        } else if (random >= 39 && random < 46) {
            shard.setArmor(15);
        } else if (random >= 46 && random < 53) {
            shard.setDmg_percent(10);
        } else if (random >= 53 && random < 73) {
            shard.setStrength_percent(5);
        } else {
            shard.setHealth(95);
        }

        return shard;
    }

    public CharShard guan(CharShard shard, int random) {
        if (random < 6) {
            shard.setAttack_speed(10);
        } else if (random >= 8 && random < 20) {
            shard.setRes_bleed(15);
        } else if (random >= 20 && random < 32) {
            shard.setAttack_speed(8);
        } else if (random >= 32 && random < 44) {
            shard.setAgility(18);
        } else if (random >= 44 && random < 56) {
            shard.setArmor(15);
        } else {
            shard.setDef_ability(35);
        }

        return shard;
    }

    public CharShard donar(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 12) {
            shard.setSlow(11);
        } else if (random >= 12 && random < 25) {
            shard.setDmg_elemental(14);
        } else if (random >= 25 && random < 37) {
            shard.setAttack_speed(8);
        } else {
            shard.setStun_chance(10);
        }

        return shard;
    }

    public CharShard dionysus(CharShard shard, int random) {
        if (random < 10) {
            shard.setRes_bleed(15);
        } else if (random >= 10 && random < 28) {
            shard.setRes_cold(10);
        } else if (random >= 28 && random < 46) {
            shard.setStun_chance(10);
        } else if (random >= 46 && random < 64) {
            shard.setStrength(11);
        } else if (random >= 64 && random < 82) {
            shard.setHealth(95);
        } else {
            shard.setDef_ability(25);
        }

        return shard;
    }

    public CharShard artemis(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 4) {
            shard.setMin_damage(5);
            shard.setMax_damage(9);
        } else if (random >= 4 && random < 6) {
            shard.setMin_poison(4);
            shard.setMax_poison(8);
        } else if (random >= 6 && random < 13) {
            shard.setSlow(11);
        } else if (random >= 13 && random < 22) {
            shard.setMin_poison(3);
            shard.setMax_poison(6);
        } else if (random >= 22 && random < 32) {
            shard.setAgility(11);
        } else if (random >= 32 && random < 42) {
            shard.setDmg_percent(10);
        } else if (random >= 42 && random < 70) {
            shard.setMin_damage(4);
            shard.setMax_damage(6);
        } else {
            shard.setAttack_speed(8);
        }

        return shard;
    }

    public CharShard atlas(CharShard shard, int random) {
        if (random < 3) {
            shard.setRes_cold(8);
            shard.setRes_electric(8);
            shard.setRes_fire(8);
        } else if (random >= 3 && random < 9) {
            shard.setRes_cold(10);
        } else if (random >= 9 && random < 15) {
            shard.setRes_electric(10);
        } else if (random >= 15 && random < 21) {
            shard.setRes_fire(10);
        } else if (random >= 21 && random < 32) {
            shard.setRes_bleed(15);
        } else if (random >= 32 && random < 43) {
            shard.setArmor(15);
        } else if (random >= 43 && random < 54) {
            shard.setIntelligence(11);
        } else {
            shard.setHealth(95);
        }

        return shard;
    }

    public CharShard archmiedes(CharShard shard, int random) {
        if (random < 2) {
            shard.setRes_cold(23);
        } else if (random >= 2 && random < 4) {
            shard.setRes_electric(23);
        } else if (random >= 4 && random < 6) {
            shard.setRes_fire(23);
        } else if (random >= 6 && random < 10) {
            shard.setRes_poison(8);
        } else if (random >= 10 && random < 14) {
            shard.setRes_poison(16);
        } else if (random >= 14 && random < 20) {
            shard.setRes_cold(10);
        } else if (random >= 20 && random < 26) {
            shard.setRes_fire(10);
        } else if (random >= 26 && random < 32) {
            shard.setRes_electric(10);
        } else if (random >= 32 && random < 39) {
            shard.setStrength(5);
        } else if (random >= 39 && random < 46) {
            shard.setDef_ability(25);
        } else if (random >= 46 && random < 53) {
            shard.setStrength(11);
        } else if (random >= 53 && random < 60) {
            shard.setHealth(95);
        } else if (random >= 60 && random < 80) {
            shard.setHealth(50);
        } else {
            shard.setArmor(5);
        }

        return shard;
    }

    public CharShard amunRa(CharShard shard, int random) {
        if (random < 3) {
            shard.setRes_cold(8);
            shard.setRes_electric(8);
            shard.setRes_fire(8);
        } else if (random >= 3 && random < 9) {
            shard.setRes_cold(10);
        } else if (random >= 9 && random < 15) {
            shard.setRes_electric(10);
        } else if (random >= 15 && random < 21) {
            shard.setRes_fire(10);
        } else if (random >= 21 && random < 32) {
            shard.setRes_bleed(15);
        } else if (random >= 32 && random < 43) {
            shard.setArmor(15);
        } else if (random >= 21 && random < 32) {
            shard.setIntelligence(11);
        } else {
            shard.setHealth(95);
        }

        return shard;
    }

    public CharShard yetiFur(CharShard shard, int random) {
        if (random < 17) {
            shard.setArmor(4);
        } else if (random >= 17 && random < 34) {
            shard.setRes_cold(10);
        } else if (random >= 34 && random < 51) {
            shard.setStrength(5);
        } else if (random >= 51 && random < 68) {
            shard.setDef_ability(25);
        } else if (random >= 68 && random < 85) {
            shard.setHealth(60);
        } else {
            shard.setRes_bleed(15);
        }

        return shard;
    }

    public CharShard vinyGrowth(CharShard shard, int random) {
        if (random < 14) {
            shard.setRes_bleed(15);
        } else if (random >= 14 && random < 28) {
            shard.setDef_ability(25);
        } else if (random >= 28 && random < 42) {
            shard.setHealth_regen(5);
        } else if (random >= 42 && random < 58) {
            shard.setArmor(4);
        } else {
            shard.setHealth(60);
        }

        return shard;
    }

    public CharShard venomSac(CharShard shard, int random) {
        if (random < 12) {
            shard.setSlow(11);
        } else if (random >= 12 && random < 24) {
            shard.setStun_chance(8);
        } else if (random >= 24 && random < 36) {
            shard.setDmg_poison(14);
        } else {
            shard.setDmg_poison(7);
        }

        return shard;
    }

    public CharShard turtleShell(CharShard shard, int random) {
        if (random < 25) {
            shard.setHealth(60);
        } else if (random >= 25 && random < 50) {
            shard.setAgility(5);
        } else if (random >= 50 && random < 75) {
            shard.setStrength(5);
        } else {
            shard.setDef_ability(25);
        }

        return shard;
    }

    public CharShard saberClaw(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(14);
        } else if (random >= 2 && random < 6) {
            shard.setAttack_speed(8);
        } else if (random >= 6 && random < 13) {
            shard.setOff_ability(25);
        } else if (random >= 13 && random < 20) {
            shard.setDmg_bleed_percent(10);
        } else if (random >= 20 && random < 27) {
            shard.setAttack_speed(8);
        } else if (random >= 27 && random < 34) {
            shard.setMin_damage(8);
            shard.setMax_damage(12);
        } else if (random >= 34 && random < 45) {
            shard.setSlow(11);
        } else if (random >= 45 && random < 60) {
            shard.setMin_damage(4);
            shard.setMax_damage(6);
        } else {
            shard.setStrength(10);
        }

        return shard;
    }

    public CharShard spinyShell(CharShard shard, int random) {
        if (random < 15) {
            shard.setRes_stun(30);
        } else if (random >= 15 && random < 30) {
            shard.setHealth_regen(10);
        } else if (random >= 30 && random < 45) {
            shard.setHealth(60);
        } else if (random >= 45 && random < 60) {
            shard.setStrength(5);
        } else if (random >= 60 && random < 75) {
            shard.setRes_bleed(15);
        } else {
            shard.setDef_ability(25);
        }

        return shard;
    }

    public CharShard raptorTooth(CharShard shard, int random) {
        if (random < 3) {
            shard.setSlow(14);
        } else if (random >= 3 && random < 10) {
            shard.setAttack_speed(8);
        } else if (random >= 10 && random < 23) {
            shard.setStrength(11);
        } else if (random >= 23 && random < 36) {
            shard.setOff_ability(25);
        } else if (random >= 39 && random < 49) {
            shard.setDmg_percent(10);
        } else if (random >= 49 && random < 67) {
            shard.setSlow(10);
        } else {
            shard.setOff_ability(15);
        }

        return shard;
    }

    public CharShard pristinePlumage(CharShard shard, int random) {
        if (random < 17) {
            shard.setDef_ability(15);
        } else if (random >= 17 && random < 34) {
            shard.setArmor(4);
        } else if (random >= 34 && random < 51) {
            shard.setAgility(5);
        } else if (random >= 51 && random < 68) {
            shard.setOff_ability(15);
        } else if (random >= 68 && random < 85) {
            shard.setRes_poison(8);
        } else {
            shard.setRes_bleed(15);
        }

        return shard;
    }

    public CharShard primalMagma(CharShard shard, int random) {
        if (random < 2) {
            shard.setRes_fire(30);
        } else if (random >= 2 && random < 5) {
            shard.setSlow(17);
        } else if (random >= 5 && random < 12) {
            shard.setSlow(14);
        } else if (random >= 12 && random < 23) {
            shard.setRes_fire(20);
        } else if (random >= 23 && random < 35) {
            shard.setMin_damage(8);
            shard.setMax_damage(18);
        } else if (random >= 35 && random < 47) {
            shard.setDmg_fire_percent(20);
        } else if (random >= 47 && random < 59) {
            shard.setMin_fire(8);
            shard.setMax_fire(18);
        } else {
            shard.setAttack_speed(25);
        }

        return shard;
    }

    public CharShard pengClaw(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(17);
        } else if (random >= 2 && random < 5) {
            shard.setAttack_speed(8);
        } else if (random >= 5 && random < 11) {
            shard.setAgility(11);
        } else if (random >= 11 && random < 17) {
            shard.setAttack_speed(8);
        } else if (random >= 17 && random < 23) {
            shard.setMin_damage(8);
            shard.setMax_damage(12);
        } else if (random >= 23 && random < 32) {
            shard.setSlow(11);
        } else if (random >= 32 && random < 44) {
            shard.setOff_ability(15);
        } else if (random >= 44 && random < 56) {
            shard.setMin_damage(4);
            shard.setMax_damage(6);
        } else if (random >= 56 && random < 68) {
            shard.setDmg_percent(10);
        } else {
            shard.setHealth(50);
        }

        return shard;
    }

    public CharShard mechanicalParts(CharShard shard, int random) {
        if (random < 20) {
            shard.setArmor(4);
        } else if (random >= 20 && random < 40) {
            shard.setStrength(5);
        } else if (random >= 40 && random < 60) {
            shard.setAgility(5);
        } else if (random >= 60 && random < 80) {
            shard.setDef_ability(25);
        } else {
            shard.setOff_ability(25);
        }

        return shard;
    }

    public CharShard lupineClaw(CharShard shard, int random) {
        if (random < 2) {
            shard.setSlow(17);
        } else if (random >= 2 && random < 6) {
            shard.setAttack_speed(12);
        } else if (random >= 6 && random < 12) {
            shard.setAttack_speed(7);
        } else if (random >= 12 && random < 20) {
            shard.setMin_damage(6);
            shard.setMax_damage(10);
        } else if (random >= 20 && random < 34) {
            shard.setMin_damage(4);
            shard.setMax_damage(6);
        } else if (random >= 34 && random < 45) {
            shard.setSlow(11);
        } else if (random >= 45 && random < 75) {
            shard.setAgility(15);
        } else {
            shard.setAgility(10);
        }

        return shard;
    }

    public CharShard hydradonHide(CharShard shard, int random) {
        if (random < 6) {
            shard.setDef_ability(25);
        } else if (random >= 6 && random < 22) {
            shard.setRes_bleed(15);
        } else if (random >= 22 && random < 38) {
            shard.setDef_ability(35);
        } else if (random >= 38 && random < 54) {
            shard.setRes_cold(10);
        } else {
            shard.setArmor(21);
        }
        return shard;
    }

    public CharShard hagsSkin(CharShard shard, int random) {
        if (random < 20) {
            shard.setArmor(4);
        } else if (random >= 20 && random < 40) {
            shard.setRes_fire(10);
        } else if (random >= 40 && random < 60) {
            shard.setAgility(5);
        } else if (random >= 60 && random < 80) {
            shard.setDef_ability(25);
        } else {
            shard.setRes_bleed(15);
        }
        return shard;
    }

    public CharShard golemHeart(CharShard shard, int random) {
        if (random < 3) {
            shard.setSlow(17);
        } else if (random >= 3 && random < 13) {
            shard.setSlow(11);
        } else if (random >= 13 && random < 26) {
            shard.setDmg_elemental(14);
        } else if (random >= 26 && random < 39) {
            shard.setAttack_speed(8);
        } else {
            shard.setStun_chance(10);
        }
        return shard;
    }

    public CharShard fungoidSpores(CharShard shard, int random) {
        if (random < 3) {
            shard.setSlow(17);
        } else if (random >= 3 && random < 16) {
            shard.setSlow(11);
        } else if (random >= 16 && random < 34) {
            shard.setMin_damage(6);
            shard.setMax_damage(12);
        } else if (random >= 34 && random < 52) {
            shard.setDmg_poison_percent(10);
        } else {
            shard.setStun_chance(10);
        }
        return shard;
    }

    public CharShard etir(CharShard shard, int random) {
        if (random < 3) {
            shard.setSlow(17);
        } else if (random >= 3 && random < 13) {
            shard.setSlow(11);
        } else if (random >= 13 && random < 26) {
            shard.setDmg_elemental(14);
        } else if (random >= 26 && random < 39) {
            shard.setAttack_speed(8);
        } else if (random >= 39 && random < 60) {
            shard.setAttack_speed(4);
        } else {
            shard.setStun_chance(10);
        }
        return shard;
    }

    public CharShard dragonBlood(CharShard shard, int random) {
        if (random < 13) {
            shard.setRes_stun(10);
        } else if (random >= 13 && random < 26) {
            shard.setHealth_regen(10);
        } else if (random >= 26 && random < 39) {
            shard.setRes_fire(8);
            shard.setRes_electric(8);
            shard.setRes_cold(8);
        } else if (random >= 39 && random < 52) {
            shard.setArmor(15);
        } else if (random >= 52 && random < 62) {
            shard.setHealth(145);
        } else if (random >= 62 && random < 75) {
            shard.setDef_ability(25);
        } else if (random >= 75 && random < 88) {
            shard.setRes_bleed(15);
        } else {
            shard.setHealth_regen(5);
        }
        return shard;
    }

    public CharShard diseasedPlumage(CharShard shard, int random) {
        if (random < 17) {
            shard.setArmor(4);
        } else if (random >= 17 && random < 34) {
            shard.setAgility(5);
        } else if (random >= 34 && random < 51) {
            shard.setDef_ability(25);
        } else if (random >= 51 && random < 68) {
            shard.setOff_ability(25);
        } else {
            shard.setDmg_poison_percent(10);
        }
        return shard;
    }

    public CharShard demonBlood(CharShard shard, int random) {
        if (random < 17) {
            shard.setDef_ability(25);
        } else if (random >= 17 && random < 34) {
            shard.setRes_fire(10);
        } else if (random >= 34 && random < 51) {
            shard.setHealth_regen(10);
        } else if (random >= 51 && random < 68) {
            shard.setRes_stun(10);
        } else {
            shard.setHealth_regen(5);
        }
        return shard;
    }

    public CharShard coldEssence(CharShard shard, int random) {
        if (random < 32) {
            shard.setSlow(11);
        } else if (random >= 32 && random < 45) {
            shard.setDmg_elemental(14);
        } else if (random >= 45 && random < 60) {
            shard.setMin_cold(5);
            shard.setMax_cold(9);
        } else if (random >= 60 && random < 75) {
            shard.setStun_chance(10);
        } else if (random >= 75 && random < 86) {
            shard.setDmg_cold_percent(20);
        } else if (random >= 86 && random < 92) {
            shard.setDmg_cold(10);
        } else if (random >= 92 && random < 98) {
            shard.setDmg_bleed(14);
        } else {
            shard.setSlow(17);
        }
        return shard;
    }

    public CharShard spiderWeb(CharShard shard, int random) {
        if (random < 15) {
            shard.setArmor(21);
        } else if (random >= 15 && random < 30) {
            shard.setRes_electric(10);
        } else if (random >= 30 && random < 45) {
            shard.setDef_ability(35);
        } else if (random >= 45) {
            shard.setAgility(10);
        }
        return shard;
    }

    public CharShard boarHide(CharShard shard, int random) {
        if (random < 55) {
            shard.setArmor(4);
        } else if (random >= 55 && random < 70) {
            shard.setRes_cold(10);
        } else if (random >= 70 && random < 85) {
            shard.setDef_ability(25);
        } else if (random >= 85) {
            shard.setRes_bleed(15);
        }
        return shard;
    }
}
