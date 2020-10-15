/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sweje
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Query(value = "select * from tb_m_user WHERE email = ?1", nativeQuery = true)
    public User getProfil(String email);
}
