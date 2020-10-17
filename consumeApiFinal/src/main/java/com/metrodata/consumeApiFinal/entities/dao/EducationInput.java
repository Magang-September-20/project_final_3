/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.entities.dao;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sweje
 */
@Data
public class EducationInput {
    String degree, status;
    int ipk, major, university;
    MultipartFile cv, photo;

    public EducationInput() {
    }
    
    
    public EducationInput(String degree, String status, int ipk, int major, int university) {
        this.degree = degree;
        this.status = status;
        this.ipk = ipk;
        this.major = major;
        this.university = university;
    }
    

    
    
}
