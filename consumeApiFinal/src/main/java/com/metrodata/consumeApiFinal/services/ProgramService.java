/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.Program;
import com.metrodata.consumeApiFinal.repositories.ProgramRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pannavr
 */
@Service
public class ProgramService {

    @Autowired
    ProgramRepository programRepository;

    public List<Program> getAll() {
        return programRepository.findAll();
    }

    public Integer getHR(int id) {
        return programRepository.getHR(id);
    }
    
    public Program getById(int id){
        return programRepository.findById(id).get();
    }
}
