/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pannavr
 */
@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer>{
    
     @Query(value="SELECT hr FROM tb_m_program WHERE id= ?1", nativeQuery=true)
    public Integer getHR(Integer id);
}
