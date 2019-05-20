/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.ItemBase;
import com.example.demo.model.Prefix;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Grzegorz
 */
@RepositoryRestResource
public interface ItemBaseRepository extends JpaRepository<ItemBase, Long> {
    
    @Query("select i from ItemBase i  where legendary = 'normal' or legendary = 'shop'")
    List<ItemBase> getItemsNormal();
    
    @Query("select i from ItemBase i  where legendary = 'shop'")
    List<ItemBase> getShopItems();
    
    @Query("select i from ItemBase i  where legendary = 'shop' or legendary = 'normal' or legendary = 'epic'")
    List<ItemBase> getEpicItems();
    
    @Query("select i from ItemBase i  where legendary = 'shop' or legendary = 'normal' or legendary = 'epic' or legendary = 'legendary'")
    List<ItemBase> getLegendaryItems();
}
