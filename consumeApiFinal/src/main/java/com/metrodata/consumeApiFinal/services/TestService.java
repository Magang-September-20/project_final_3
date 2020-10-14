/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.Test;
import com.metrodata.consumeApiFinal.repositories.TestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pannavr
 */
@Service
public class TestService {
    @Autowired
    TestRepository testRepository;
       public List<Test> getAll(){
       
           return testRepository.findAll();
       }
}
