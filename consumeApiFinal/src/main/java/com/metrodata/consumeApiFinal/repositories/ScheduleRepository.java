/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olivia Michele
 */
@Repository
//public interface ScheduleRepository extends JpaRepository<ScheduleTest, String>{
//    @Query(value="CALL `scheduleTest`(?1)",nativeQuery = true)
//    public ScheduleTest getByEmail(String email);
public interface ScheduleRepository extends JpaRepository<ScheduleTest, Integer> {

    @Query(value = "SELECT * from tb_tr_schedule_test  as a join tb_tr_program_apply as b ON a.id = b.id join tb_m_user as c on b.candidate = c.id WHERE c.email = ?1", nativeQuery = true)
    public List<ScheduleTest> getAccountByEmail(String email);
   
    @Query(value = "select * from tb_tr_schedule_test as a join tb_m_user as b on a.pic = b.id WHERE b.email = ?1", nativeQuery = true)
    public List<ScheduleTest> getScheduleHr(String email);
    
    
    @Query(value = "select a.*, b.*, c.id, c.grade, c.note, c.is_passed from tb_tr_schedule_test as a join tb_tr_result as c on a.id = c.id JOIN tb_m_user as b on a.pic = b.id WHERE b.email = ?1", nativeQuery = true)
    public List<ScheduleTest> getResult(String email);
}
