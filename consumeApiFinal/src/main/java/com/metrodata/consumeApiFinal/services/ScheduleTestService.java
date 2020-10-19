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
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm");
        String strDate = dateFormatter.format(scheduleTest.getDate());
        String startTime = timeFormatter.format(scheduleTest.getStartTime());
        String endTime = timeFormatter.format(scheduleTest.getEndTime());

        java.util.Date dutyDay = (java.util.Date) dateFormatter.parse(strDate);
        java.util.Date timeStart = (java.util.Date) timeFormatter.parse(startTime);
        java.util.Date timeEnd = (java.util.Date) timeFormatter.parse(endTime);
//        ProgramApply programApply = new ProgramApply(scheduleTest.getApply());
//        User pic = us.getById(scheduleTest.getPic());
//        Test test = ts.getById(scheduleTestInput.getTest());
        ScheduleTest test1 = new ScheduleTest(Integer.SIZE, dutyDay, timeStart, timeEnd, scheduleTest.getLocation(), scheduleTest.getApply(), scheduleTest.getPic(), scheduleTest.getTest());
        Result result = new Result();
        result.setId(scheduleTest.getId());
        result.setGrade(0);
        result.setIsPassed(false);
        result.setNote("belum di isi");
//        
        result.setScheduleTest(test1);
        test1.setResult(result);
//        
////        rs.saveResult(result);
        scheduleTestRepository.save(test1);
    }

    public ScheduleTest getById(int id) {
        return scheduleTestRepository.findById(id).get();
    }

    public List<ScheduleTest> ShowPsikotes() {
        return scheduleTestRepository.ShowPsikotes();
    }

    public List<ScheduleTest> ShowTechnical() {
        return scheduleTestRepository.ShowTechnical();
    }

    public List<ScheduleTest> ShowInterview() {
        return scheduleTestRepository.ShowInterview();
    }
    public List<ScheduleTest> getAll() {
        return scheduleTestRepository.findAll();
    }
}
