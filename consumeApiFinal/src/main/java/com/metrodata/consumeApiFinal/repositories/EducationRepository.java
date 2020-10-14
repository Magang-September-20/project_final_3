/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olivia Michele
 */
@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {
    
}
