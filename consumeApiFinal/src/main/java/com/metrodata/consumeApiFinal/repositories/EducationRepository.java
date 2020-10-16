/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.Education;
import com.metrodata.consumeApiFinal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olivia Michele
 */
@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {
    @Query(value = "select * from tb_m_education as e join tb_m_user as u on e.id = u.id WHERE u.email = ?1", nativeQuery = true)
    public Education getEducation(String email);
}
