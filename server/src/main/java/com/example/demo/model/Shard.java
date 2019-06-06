/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

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
@Table(name = "shards")
public class Shard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shard_id;
    
    private String name;
    private String type;
    private String type_eq;
    
    private Integer armor;
    
    private Integer res_bleed;
}
