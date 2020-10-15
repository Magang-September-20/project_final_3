/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.entities.dao;

import lombok.Data;

/**
 *
 * @author pannavr
 */
@Data
public class ScheduleTestInput {
    String date, startTime, endTime, location;
    int test, apply, pic;

    public ScheduleTestInput(String date, String startTime, String endTime, String location, int test, int apply, int pic) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.test = test;
        this.apply = apply;
        this.pic = pic;
    }

  

}