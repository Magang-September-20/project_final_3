/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.University;
import com.metrodata.consumeApiFinal.repositories.UniversityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sweje
 */
@Service
public class UniversityService {
    @Autowired UniversityRepository ur;
    
    public List<University> getAll(){
        return ur.findAll();
    }
    
    public University findbyid(int id){
        return ur.findById(id).get();
    }
}
