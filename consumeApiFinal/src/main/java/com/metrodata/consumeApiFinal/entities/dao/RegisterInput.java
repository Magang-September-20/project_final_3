/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.entities.dao;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author sweje
 */
@Data
public class RegisterInput {
    String name,username,email,date,gender,pass;
//    Date date;

    public RegisterInput(String name, String username, String email, String date, String gender, String pass) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.date = date;
        this.gender = gender;
        this.pass = pass;
    }

    

}
