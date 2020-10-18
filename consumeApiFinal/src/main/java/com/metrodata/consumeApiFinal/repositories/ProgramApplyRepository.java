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
public interface ProgramApplyRepository extends JpaRepository<ProgramApply, Integer>{
    
    @Query(value="SELECT * FROM tb_tr_program_apply join tb_m_user on tb_tr_program_apply.candidate=tb_m_user.id WHERE tb_m_user.id = ?1", nativeQuery=true)
    public List<ProgramApply> getApply(int id);
    
    @Query(value="SELECT * from tb_m_user where email =?1 LIMIT 1", nativeQuery=true)
    public Integer getid(String email);

       @Query(value="SELECT COUNT(id) from tb_tr_program_apply", nativeQuery=true)
       public Integer countApply();
       
        @Query(value = "select * FROM tb_tr_program_apply LEFT join tb_tr_schedule_test on tb_tr_program_apply.id = tb_tr_schedule_test.apply where tb_tr_schedule_test.id is NULL", nativeQuery = true)
    public List<ProgramApply> Showschedule();
}
