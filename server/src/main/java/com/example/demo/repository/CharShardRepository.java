/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.CharShard;
import com.example.demo.model.Shard;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author Grzegorz
 */
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface CharShardRepository extends JpaRepository<CharShard, Long> {
    
    @Query("select cs from CharShard cs where char_id = ?1 and assigned is null")
    Collection<CharShard> getCharShards(Long char_id);
    
    @Query("select cs from CharShard cs where char_id = ?1 and shard_id = ?2 and char_shards_id != ?3 and level < 5 and assigned is null")
    Collection<CharShard> getShardToAssemble(Long char_id, Long shard_id, Long char_shard_id);
}
