/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.ProgramApply;
import com.metrodata.consumeApiFinal.entities.Result;
import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.entities.Test;
import com.metrodata.consumeApiFinal.entities.User;
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
    @Autowired
    UserService us;
    @Autowired
    TestService ts;
    @Autowired
    ResultService rs;

    public void save(ScheduleTest scheduleTest) throws ParseException {
//        DateFormat df = new SimpleDateFormat("hh:mm");
//        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

//        ProgramApply programApply = new ProgramApply(scheduleTestInput.getApply());
//        User pic = us.getById(scheduleTestInput.getPic());
//        Test test = ts.getById(scheduleTestInput.getTest());
//        java.util.Date dutyDay = (java.util.Date) simpleDateFormat.parse(scheduleTestInput.getDate());
//        java.util.Date timeStart = (java.util.Date) df.parse(scheduleTestInput.getStartTime());
//        java.util.Date timeEnd = (java.util.Date) df.parse(scheduleTestInput.getEndTime());
//        ScheduleTest test1 = new ScheduleTest(Integer.SIZE, dutyDay, timeStart, timeEnd, scheduleTestInput.getLocation(), programApply, pic, test);
        
//        Result result = new Result();
//        result.setId(scheduleTestRepository.getMaxId());
//        result.setGrade(0);
//        result.setIsPassed(false);
//        result.setNote("belum di isi");
//        
//        result.setScheduleTest(test1);
//        test1.setResult(result);
//        
////        rs.saveResult(result);
//        scheduleTestRepository.save(test1);
    }
    
    public ScheduleTest getById(int id){
        return scheduleTestRepository.findById(id).get();
    }
}
