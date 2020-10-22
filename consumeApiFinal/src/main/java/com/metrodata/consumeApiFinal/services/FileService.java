/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.File;
import com.metrodata.consumeApiFinal.repositories.FileRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pannavr
 */
@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;
    @Value("${data.dir.upload}")
    private String uploadDir;
    
    public int countCV() {
        return fileRepository.countCV();
    }

    public File save(File file) {
        return fileRepository.save(file);
    }
    
    public File getById(int id){
        return fileRepository.findById(id).get();
    }

    public String UploadFileName(MultipartFile file, File a, String prefix) {

        String filename = "";
        if (!file.isEmpty()) {
            filename = StringUtils.cleanPath(file.getOriginalFilename());
            if (filename.contains("..")) {
                System.out.println("not a a valid file");
            }

            filename = prefix  + "-" +  a.getId() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
//            filename = DigestUtils.md5DigestAsHex(filename.getBytes()) + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                try {
                    Files.createDirectory(uploadPath);
                } catch (IOException ex) {
                    Logger.getLogger(RegisterService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                Path filePath = uploadPath.resolve(filename);
                Files.copy(file.getInputStream(), filePath);
            } catch (IOException ex) {
                Logger.getLogger(RegisterService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            filename = "";
        }

        return filename;
    }
}
