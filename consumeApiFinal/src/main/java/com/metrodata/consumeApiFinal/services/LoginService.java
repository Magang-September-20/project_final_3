/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.LoginInput;
import com.metrodata.consumeApiFinal.entities.rest.LoginOutput;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Service
public class LoginService {

    private static final RestTemplate RT = new RestTemplate();

    public LoginOutput login(LoginInput login) {
        HttpEntity<LoginInput> request = new HttpEntity<LoginInput>(login, null);
        ResponseEntity<LoginOutput> responseEntity = RT.exchange("http://116.254.101.228:8080/metrodataacademy/login",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<LoginOutput>() {
        });

        return responseEntity.getBody();
    }

    public LoginOutput loginNew(LoginInput login) {
        return RT.postForObject("http://localhost:8088/login",login,LoginOutput.class);
    }
}
