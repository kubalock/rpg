/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Item;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Grzegorz
 */
@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {
    
    @Query("select i from Item i  where char_id = ?1 and equipped = 'no'")
    Collection<Item> getUserItems(Long char_id);
    
    @Query("select i from Item i where char_id = ?1 and equipped = 'yes'")
    Collection<Item> getEquippedItems(Long  char_id);
}
