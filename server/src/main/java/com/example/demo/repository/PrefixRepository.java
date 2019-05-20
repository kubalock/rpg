/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Item;
import com.example.demo.model.Prefix;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Grzegorz
 */
@RepositoryRestResource
public interface PrefixRepository extends JpaRepository<Prefix, Long> {
    
    @Query("select p from Prefix p  where type = ?1")
    List<Prefix> getPrefixes(String type);
}
