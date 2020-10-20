/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.Result;
import com.metrodata.consumeApiFinal.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sweje
 */
@Service
public class ResultService {

    @Autowired
    ResultRepository resultRepository;

    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    public int examDone() {
        return resultRepository.examDone();
    }
    public int passedUser(){
        return resultRepository.passedUser();
    }
    public int failedUser(){
        return resultRepository.failedUser();
    }
    
     public int passedPsikotes(){
        return resultRepository.passedPsikotes();
    }
    
     public int passedTechnical(){
        return resultRepository.passedTechnical();
    }
    
}
