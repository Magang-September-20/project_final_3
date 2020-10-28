/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.ProgramApply;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pannavr
 */
@Repository
public interface ProgramApplyRepository extends JpaRepository<ProgramApply, Integer> {

    @Query(value = "SELECT * FROM tb_tr_program_apply join tb_m_user on tb_tr_program_apply.candidate=tb_m_user.id WHERE tb_m_user.id = ?1", nativeQuery = true)
    public List<ProgramApply> getApply(int id);

    @Query(value = "SELECT * from tb_m_user where email =?1 LIMIT 1", nativeQuery = true)
    public Integer getid(String email);

    @Query(value = "SELECT COUNT(id) from tb_tr_program_apply", nativeQuery = true)
    public Integer countApply();

    @Query(value = "select * FROM tb_tr_program_apply LEFT join tb_tr_schedule_test on tb_tr_program_apply.id = tb_tr_schedule_test.apply where tb_tr_schedule_test.id is NULL", nativeQuery = true)
    public List<ProgramApply> Showschedule();
   
    @Query(value = "select count(id) from tb_tr_program_apply WHERE tb_tr_program_apply.program=1", nativeQuery = true)
    public Integer applyIntern();
    @Query(value = "select count(id) from tb_tr_program_apply WHERE tb_tr_program_apply.program=2", nativeQuery = true)
    public Integer applyJunior();
   
    @Query(value = "select count(id) from tb_tr_program_apply WHERE tb_tr_program_apply.program=3", nativeQuery = true)
    public Integer applySenior();
   
    @Query(value = "select count(id) from tb_tr_program_apply WHERE tb_tr_program_apply.program=4", nativeQuery = true)
    public Integer applyFront();
   
    @Query(value = "select count(id) from tb_tr_program_apply WHERE tb_tr_program_apply.program=5", nativeQuery = true)
    public Integer applyBack();
  
    @Query(value = "SELECT count(id) from tb_tr_program_apply  WHERE tb_tr_program_apply.candidate = ?1 and tb_tr_program_apply.program = ?2", nativeQuery = true)
    public int compare(int candidateId, int program);
   
    //query untuk mengetahui siapa saja yang lulus dan sudah mengerjakan test
    @Query(value = "SELECT * from tb_tr_program_apply join tb_tr_schedule_test on tb_tr_schedule_test.apply = tb_tr_program_apply.id join tb_tr_result on tb_tr_schedule_test.id = tb_tr_result.id WHERE tb_tr_program_apply.candidate = ?1 and tb_tr_schedule_test.test = ?2 and tb_tr_program_apply.program = ?3 and tb_tr_result.is_passed = true and tb_tr_schedule_test.hastest = true", nativeQuery = true)
    public List<ProgramApply> passedExam(int candidate,int test, int program);
   
 
}
