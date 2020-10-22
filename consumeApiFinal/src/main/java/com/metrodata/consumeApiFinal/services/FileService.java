/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.File;
import com.metrodata.consumeApiFinal.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pannavr
 */
@Service
public class FileService {
    
    @Autowired
    FileRepository fileRepository;
    
    public int countCV(){
    return fileRepository.countCV();
    }
    
    public File save(File file){
    return fileRepository.save(file);
    }
}
