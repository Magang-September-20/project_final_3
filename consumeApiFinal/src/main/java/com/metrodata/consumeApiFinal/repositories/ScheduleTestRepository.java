/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.entities.dao.ScheduleTestInput;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pannavr
 */
public interface ScheduleTestRepository extends JpaRepository<ScheduleTest, Integer>{

    public void save(ScheduleTestInput scheduleTestInput);

//    public void save(Map<String, Object> registerApi);

//    public String save(ScheduleRepository scheduleRepository);
    
}
