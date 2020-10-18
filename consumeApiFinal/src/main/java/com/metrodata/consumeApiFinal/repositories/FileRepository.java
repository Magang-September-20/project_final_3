/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pannavr
 */
@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    
    @Query(value = "select count(cv) from tb_m_file", nativeQuery=true)
    public int countCV();
}
