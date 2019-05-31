/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import io.micrometer.core.lang.NonNull;
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
@Table(name = "guilds")
public class Guild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guild_id;

    private @NonNull
    String name;
    private @NonNull
    String descritpion;
    private @NonNull
    Long leader_id;
    private @NonNull
    Integer capacity;
    private @NonNull
    Integer min_level;

    
    public Guild(String name, String descritpion, Long leader_id) {
        this.name = name;
        this.descritpion = descritpion;
        this.leader_id = leader_id;
        this.capacity = 20;
        this.min_level = 1;
    }
}
