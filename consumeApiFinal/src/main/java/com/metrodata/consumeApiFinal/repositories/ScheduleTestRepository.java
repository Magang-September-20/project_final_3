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
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pannavr
 */
@Repository
public interface ScheduleTestRepository extends JpaRepository<ScheduleTest, Integer>{

//    public void save(ScheduleTestInput scheduleTestInput);

    @Query(value="SELECT MAX(id) FROM tb_tr_schedule_test",nativeQuery = true)
    public int getMaxId();
//    public void save(Map<String, Object> registerApi);

//    public String save(ScheduleRepository scheduleRepository);
    
}
