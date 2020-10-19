/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.entities.dao.ScheduleTestInput;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pannavr
 */
@Repository
public interface ScheduleTestRepository extends JpaRepository<ScheduleTest, Integer> {

//    public void save(ScheduleTestInput scheduleTestInput);
    @Query(value = "SELECT MAX(id) FROM tb_tr_schedule_test", nativeQuery = true)
    public int getMaxId();
//    public void save(Map<String, Object> registerApi);

//    public String save(ScheduleRepository scheduleRepository);
    @Query(value = "SELECT * FROM  tb_tr_schedule_test join tb_tr_program_apply  on tb_tr_program_apply.id =tb_tr_schedule_test.apply join tb_tr_result on tb_tr_result.id = tb_tr_schedule_test.id join tb_m_test on tb_tr_schedule_test.test = tb_m_test.id WHERE tb_m_test.id = 1 and tb_tr_result.is_passed = 1", nativeQuery = true)
    public List<ScheduleTest> ShowPsikotes();

    @Query(value = "SELECT * FROM  tb_tr_schedule_test join tb_tr_program_apply  on tb_tr_program_apply.id =tb_tr_schedule_test.apply join tb_tr_result on tb_tr_result.id = tb_tr_schedule_test.id join tb_m_test on tb_tr_schedule_test.test = tb_m_test.id WHERE tb_m_test.id = 2 and tb_tr_result.is_passed = 1", nativeQuery = true)
    public List<ScheduleTest> ShowTechnical();

    @Query(value = "SELECT * FROM  tb_tr_schedule_test join tb_tr_program_apply  on tb_tr_program_apply.id =tb_tr_schedule_test.apply join tb_tr_result on tb_tr_result.id = tb_tr_schedule_test.id join tb_m_test on tb_tr_schedule_test.test = tb_m_test.id WHERE tb_m_test.id = 3 and tb_tr_result.is_passed = 1", nativeQuery = true)
    public List<ScheduleTest> ShowInterview();

}
