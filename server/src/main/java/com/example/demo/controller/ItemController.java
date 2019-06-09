/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Hero;
import com.example.demo.model.Item;
import com.example.demo.model.ItemBonuses;
import com.example.demo.model.Prefix;
import com.example.demo.model.Shard;
import com.example.demo.repository.HeroRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ShardRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grzegorz
 */
@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ShardRepository shardRepository;

    public ItemController(ItemRepository itemRepository,
            HeroRepository heroRepository, ShardRepository shardRepository) {
        this.itemRepository = itemRepository;
        this.heroRepository = heroRepository;
        this.shardRepository = shardRepository;

    }

    @GetMapping("/getItemToEnchance/{id}/{shard_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Item> getItemToEnchance(@PathVariable(value = "id") Long char_id,
            @PathVariable(value = "shard_id") Long shard_id) {
        List<Item> items = new ArrayList(itemRepository.getUserItems(char_id));
        Collection<Item> output = new ArrayList<Item>();
        Shard shard = shardRepository.getOne(shard_id);
        for (Item item : items) {
            if (item.getShard_id() != null) {
                if (item.getShard_id().getShard().getShard_id() == shard_id) {
                    output.add(item);
                    continue;
                } else {
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("all armor")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Head") || item.getItemBase().getType().equalsIgnoreCase("Chest") || item.getItemBase().getType().equalsIgnoreCase("Arms") || item.getItemBase().getType().equalsIgnoreCase("Legs")) {
                    output.add(item);
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("Weapon")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Weapon")) {
                    output.add(item);
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("Head")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Head")) {
                    output.add(item);
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("Arms")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Arms")) {
                    output.add(item);
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("Shield")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Shield")) {
                    output.add(item);
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("Legs")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Legs")) {
                    output.add(item);
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("Chest")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Chest")) {
                    output.add(item);
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("Amulet")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Necklage")) {
                    output.add(item);
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("rings and amulets")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Necklage") || item.getItemBase().getType().equalsIgnoreCase("Ring")) {
                    output.add(item);
                    continue;
                }
            }
            if (shard.getType_eq().equalsIgnoreCase("head and torso")) {
                if (item.getItemBase().getType().equalsIgnoreCase("Head") || item.getItemBase().getType().equalsIgnoreCase("Chest")) {
                    output.add(item);
                    continue;
                }
            }
        }

        return output;
    }

    @GetMapping("/getUsersItems/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Item> getUserItems(@PathVariable(value = "id") Long char_id) {
        Collection<Item> items = itemRepository.getUserItems(char_id);

        return items;
    }

    @GetMapping("/getEquippedItems/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Item> getEquippedItems(@PathVariable(value = "id") Long char_id) {
        Collection<Item> items = itemRepository.getEquippedItems(char_id);

        return items;
    }

    @DeleteMapping("/sellItem/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void sellItem(@PathVariable(value = "id") Long item_id) {
        Item item = itemRepository.getOne(item_id);
        if(item.getShard_id() != null) {
            item.getShard_id().setAssigned("no");
        }
        itemRepository.deleteById(item_id);
    }

    @PutMapping("/equipItem/{id}/{char_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void equipItem(@PathVariable(value = "id") Long item_id, @PathVariable(value = "char_id") Long char_id) {
        Item item = itemRepository.getOne(item_id);
        Hero hero = heroRepository.getOne(char_id);

        ItemBonuses bonuses = new ItemBonuses();
        bonuses.getBonuses(item);
        hero = bonuses.addBonuses(hero);

        item.setEquipped("yes");
        itemRepository.save(item);
        heroRepository.save(hero);
    }

    @PutMapping("/takeOffItem/{id}/{char_id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void takeOffItem(@PathVariable(value = "id") Long item_id, @PathVariable(value = "char_id") Long char_id) {
        Item item = itemRepository.getOne(item_id);
        Hero hero = heroRepository.getOne(char_id);

        ItemBonuses bonuses = new ItemBonuses();
        bonuses.getBonuses(item);
        hero = bonuses.deductBonuses(hero);

        item.setEquipped("no");
        itemRepository.save(item);
        heroRepository.save(hero);
    }
}
