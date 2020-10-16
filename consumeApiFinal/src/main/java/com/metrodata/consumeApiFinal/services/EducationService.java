/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.Education;
import com.metrodata.consumeApiFinal.entities.User;
import com.metrodata.consumeApiFinal.repositories.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olivia Michele
 */
@Service
public class EducationService {
    @Autowired
    EducationRepository er;
    
    
    public Education getEducation(String email){
        return er.getEducation(email);
    }
    
}
