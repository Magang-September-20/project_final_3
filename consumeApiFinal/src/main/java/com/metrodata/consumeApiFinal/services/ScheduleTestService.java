/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.entities.dao.ScheduleTestInput;
//import com.metrodata.consumeApiFinal.entities.dao.ScheduleInput;?
import com.metrodata.consumeApiFinal.repositories.ScheduleRepository;
import com.metrodata.consumeApiFinal.repositories.ScheduleTestRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pannavr
 */
@Service
public class ScheduleTestService {

    @Autowired
    ScheduleTestRepository scheduleTestRepository;

    public ScheduleTest save(ScheduleTest scheduleTest) {
//         DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//           java.util.Date dutyDay = (java.util.Date) simpleDateFormat.parse(input.getDate());
//        input.getDate();
//        input.getStartTime();
//        input.getEndTime();
//        input.getLocation();
//        input.getApply();
//        input.getPic();
//        ScheduleTest scheduleTest = new ScheduleTest( input.getDate();, input.getStartTime(), input.getEndTime(),  input.getApply(),   input.getApply(), input.getPic());
//        return scheduleTestRepository.save(scheduleTest);

//        scheduleTestInput.getDate();
//        scheduleTestInput.getStartTime();
//        scheduleTestInput.getEndTime();
//        scheduleTestInput.getLocation();
//        scheduleTestInput.getTest();
//        scheduleTestInput.getApply();
//        scheduleTestInput.getPic();

//        DateFormat df = new SimpleDateFormat("hh:mm");
//        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date dutyDay = (java.util.Date) simpleDateFormat.parse(scheduleTestInput.getDate());
//        java.util.Date timeStart = (java.util.Date) df.parse(scheduleTestInput.getStartTime());
//        java.util.Date timeEnd = (java.util.Date) df.parse(scheduleTestInput.getEndTime());
//        ScheduleTest test = new ScheduleTest(Integer.SIZE, dutyDay, timeStart, timeEnd, scheduleTestInput.getLocation());
//        scheduleTestRepository.save(test);

//        scheduleTestInput.setDate(scheduleTestInput.getDate());
//        scheduleTestInput.setStartTime(scheduleTestInput.getStartTime());
//        scheduleTestInput.setEndTime(scheduleTestInput.getEndTime());
//        scheduleTestInput.setLocation(scheduleTestInput.getLocation());
//        scheduleTestInput.setTest(scheduleTestInput.getTest());
//        scheduleTestInput.setPic(scheduleTestInput.getPic());
//        scheduleTestInput.setApply(scheduleTestInput.getApply());
//        scheduleTestRepository.save(scheduleTestInput);
////        Map<String, Object> registerApi = new HashMap<>();
////        registerApi.put("id", 0);
////        registerApi.put("date", dutyDay);
////        registerApi.put("startTime", timeStart);
////        registerApi.put("endTime", timeEnd);
////        registerApi.put("location", scheduleTestInput.getLocation());
////        registerApi.put("test", scheduleTestInput.getTest());
////        registerApi.put("apply", scheduleTestInput.getApply());
////        registerApi.put("pic", scheduleTestInput.getPic());
//        scheduleTestRepository.save(scheduleTestInput);

      return scheduleTestRepository.save(scheduleTest);
    }
}
