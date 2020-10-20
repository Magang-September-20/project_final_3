/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.repositories;

import com.metrodata.consumeApiFinal.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sweje
 */
@Repository
public interface ResultRepository extends JpaRepository<Result, Integer>{
    
    @Query (value ="SELECT COUNT(id) FROM tb_tr_result where tb_tr_result.grade >= 0", nativeQuery=true)
    public int examDone();
    
    @Query(value = "SELECT COUNT(a.id) from tb_tr_result as a JOIN tb_tr_schedule_test as b on a.id = b.id WHERE b.test=3 AND a.is_passed=TRUE",nativeQuery = true)
    public int passedUser();
    @Query(value = "SELECT COUNT(a.id) from tb_tr_result as a JOIN tb_tr_schedule_test as b on a.id = b.id WHERE a.is_passed=false;",nativeQuery = true)
    public int failedUser();
}
