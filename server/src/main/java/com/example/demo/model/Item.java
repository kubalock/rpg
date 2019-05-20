/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.micrometer.core.lang.NonNull;
import java.util.Collection;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long item_id;

    @OneToOne
    @JoinColumn(name = "prefix_id")
    @JsonManagedReference
    private Prefix prefix;

    @OneToOne
    @JoinColumn(name = "item_base_id")
    @JsonManagedReference
    private ItemBase itemBase;

    @OneToOne
    @JoinColumn(name = "suffix_id")
    @JsonManagedReference
    private Suffix suffix;

    @OneToOne
    @JoinColumn(name = "char_id")
    @JsonManagedReference
    private Hero hero;
    
    private @NonNull
    String equipped;
    
    private Integer armor;
    private Integer health_regen;
    private Integer agility;
}
