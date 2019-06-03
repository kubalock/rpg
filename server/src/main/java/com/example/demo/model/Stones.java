/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.micrometer.core.lang.NonNull;
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
@AllArgsConstructor
@Table(name = "stones")
public class Stones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stones_id;

    private @NonNull
    Integer blood_stone;

    private @NonNull
    Integer heart_stone;

    private @NonNull
    Integer life_stone;

    private @NonNull
    Integer soul_stone;

    @OneToOne
    @JoinColumn(name = "char_id")
    @JsonBackReference
    private Hero hero;
    
    public Stones() {
        this.blood_stone = 5;
        this.heart_stone = 0;
        this.life_stone = 0;
        this.soul_stone = 0;
    }
}
