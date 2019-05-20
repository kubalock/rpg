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
@Table(name = "mastery")
public class Mastery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mastery_id;
    
    private String mastery_name;
    
    private Integer health_level;
    private Integer strength_level;
    private Integer agility_level;
    private Integer intelligence_level;
    private Integer endurance_level;
}
