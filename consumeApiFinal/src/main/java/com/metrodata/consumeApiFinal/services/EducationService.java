/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.Education;
import com.metrodata.consumeApiFinal.entities.Major;
import com.metrodata.consumeApiFinal.entities.University;
import com.metrodata.consumeApiFinal.entities.User;
import com.metrodata.consumeApiFinal.entities.dao.EducationInput;
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
    @Autowired
    MajorService ms;
    @Autowired
    UniversityService us;
    @Autowired
    UserService userService;
    
    
    public Education getEducation(String email){
        return er.getEducation(email);
    }
    
    public Education getById(int id){
        return er.findById(id).get();
    }
    
    public void save(EducationInput input,int idTemp){
        Major major = ms.findbyid(input.getMajor());
        University univ = us.findbyid(input.getUniversity());
        
        Education edu = new Education(Integer.SIZE, input.getDegree(), input.getStatus(), input.getIpk(),major,univ);
        User user = userService.getById(idTemp);
        edu.setUser(user);
        
        System.out.println(input.getDegree());
        System.out.println(input.getIpk());
        System.out.println(input.getStatus());
        System.out.println(input.getMajor());
        
        er.save(edu);
    }
}
