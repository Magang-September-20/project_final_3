/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

//import com.metrodata.consumeApiFinal.entities.User;
import com.metrodata.consumeApiFinal.entities.rest.UserOutput;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author pannavr
 */
@Service
public class UserService {
     private static final RestTemplate RT =  new RestTemplate();
     
     public List<UserOutput> getAll(){
     ResponseEntity<UserOutput[]> response = RT.getForEntity("http://localhost:8088/getAll", UserOutput[].class);
     return Arrays.asList(response.getBody());
}
}
