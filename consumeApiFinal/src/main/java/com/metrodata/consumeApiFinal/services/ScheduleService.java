/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.repositories.ScheduleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olivia Michele
 */
@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    
    public List<ScheduleTest>getAll(){
        return scheduleRepository.findAll();
    }
    
    public ScheduleTest getByEmail(String email){
        return scheduleRepository.getByEmail(email);
    }
}
