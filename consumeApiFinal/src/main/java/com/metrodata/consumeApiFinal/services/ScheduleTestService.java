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
import com.metrodata.consumeApiFinal.repositories.ScheduleTestRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
    @Autowired
    ProgramApplyService programApplyService;
    @Autowired
    EmailNotificationService emailNotificationService;

    public void save(ScheduleTestInput scheduleTest) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm");

        dateFormatter.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        timeFormatter.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        System.out.println(scheduleTest.getDate());
        System.out.println(scheduleTest.getStartTime());
        System.out.println(scheduleTest.getEndTime());

        java.util.Date dutyDay = (java.util.Date) dateFormatter.parse(scheduleTest.getDate());
        java.util.Date timeStart = (java.util.Date) timeFormatter.parse(scheduleTest.getStartTime());
        java.util.Date timeEnd = (java.util.Date) timeFormatter.parse(scheduleTest.getEndTime());

        ProgramApply programApply = new ProgramApply(scheduleTest.getApply());

//        System.out.println("isi getapply(): "+scheduleTest.getApply());
//        System.out.println("ini buat email : "+programApply.getId());
        User pic = us.getById(scheduleTest.getPic());
//        ProgramApply apply = programApplyService.getById(scheduleTest.getApply());

        Test test = ts.getById(scheduleTest.getTest());

        ScheduleTest test1 = new ScheduleTest(scheduleTestRepository.getMaxId() + 1, dutyDay, timeStart, timeEnd, scheduleTest.getLocation(), programApply, pic, test);

        ProgramApply programApplyEmail = programApplyService.getById(programApply.getId());
        User candidate = us.getById(programApplyEmail.getCandidate().getId());

//        System.out.println("pic fullname: " + pic.getFullName());
//        System.out.println("candidate fullname: " + candidate.getFullName());
        Result result = new Result();
        result.setId(test1.getId());
        result.setGrade(0);
        result.setIsPassed(false);
        result.setNote("belum di isi");

        result.setScheduleTest(test1);
        test1.setResult(result);

        scheduleTestRepository.save(test1);
//        ======= kirim email ========
        try {
            emailNotificationService.sendSchedule(candidate, pic, test1, scheduleTest);
            emailNotificationService.sendSchedulePic(candidate, pic, test1, scheduleTest);
        } catch (MessagingException ex) {
            Logger.getLogger(ProgramApplyService.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public Integer saveHastest(int idUser) {
        return scheduleTestRepository.InsertHastest(idUser);
    }

    public ScheduleTest isHastest(int programId,int test) {
        return scheduleTestRepository.isHastest(programId,test);
    }

    public ScheduleTest getDetailProgress(int program) {
        return scheduleTestRepository.getDetailProgress(program);
    }

    public ScheduleTest isPassedTest(int program, int test) {
        return scheduleTestRepository.isPassedTest(program, test);
    }
}
