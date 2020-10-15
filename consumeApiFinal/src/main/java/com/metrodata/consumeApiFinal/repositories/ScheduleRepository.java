/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olivia Michele
 */
@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleTest, String>{
    @Query(value="CALL `scheduleTest`(?1)",nativeQuery = true)
    public ScheduleTest getByEmail(String email);
}
