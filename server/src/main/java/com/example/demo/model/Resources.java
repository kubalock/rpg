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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resources")
public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resources_id;

    private @NonNull
    Integer gold;
    private @NonNull
    Integer wood;
    private @NonNull
    Integer iron;

    @OneToOne
    @JoinColumn(name = "char_id")
    @JsonBackReference
    private Hero hero;
}
