/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pannavr
 */
public interface ProgramRepository extends JpaRepository<Program, Integer>{
    
}
