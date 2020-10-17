/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.Major;
import com.metrodata.consumeApiFinal.repositories.MajorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sweje
 */
@Service
public class MajorService {
    @Autowired
    MajorRepository mr;
    
    public List<Major> getAllMajor(){
        return mr.findAll();
    }
    
    public Major findbyid(int id){
        return mr.findById(id).get();
    }
}
