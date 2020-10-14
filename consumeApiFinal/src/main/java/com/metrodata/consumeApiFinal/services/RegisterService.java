/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.User;
import com.metrodata.consumeApiFinal.entities.dao.RegisterInput;
import com.metrodata.consumeApiFinal.repositories.UserRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sweje
 */
@Service
public class RegisterService {
    @Autowired
    EmailNotificationService email;
    @Autowired
    UserRepository ur;
    private static final RestTemplate RT = new RestTemplate();

    public void register(RegisterInput input) throws ParseException {
        Map<String, Object> registerApi = new HashMap<>();
        registerApi.put("name", input.getName());
        registerApi.put("email", input.getEmail());
        registerApi.put("username", input.getUsername());
        registerApi.put("password", input.getPass());

        int idTemp = (int) RT.postForObject("http://localhost:8088/register", registerApi, Map.class).get("id");
        System.out.println(idTemp + "ini id");
        
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dutyDay = (java.util.Date) simpleDateFormat.parse(input.getDate());
        
        User user = new User(idTemp, input.getName(), input.getEmail(), input.getGender(), dutyDay);
        ur.save(user);
        try {
            email.sendEmail(input);
        } catch (MessagingException ex) {
            Logger.getLogger(RegisterService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
