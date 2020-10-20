/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.entities.User;
import com.metrodata.consumeApiFinal.entities.dao.RegisterInput;
import com.metrodata.consumeApiFinal.entities.dao.ScheduleTestInput;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author sweje
 */
@Service
public class EmailNotificationService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(RegisterInput input) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("name", input.getName());
        context.setVariable("email", input.getEmail());
        context.setVariable("username", input.getUsername());

        String html = templateEngine.process("template-engine", context);

        System.out.println(input.getEmail());
        helper.setTo(input.getEmail());
        helper.setText(html, true);
        helper.setSubject("Register Success - Metrodata Recruitment");
        helper.setFrom("metrodata.recruitment3@gmail.com");

        javaMailSender.send(message);
    }

    public void sendSchedule(User candidate, User pic,  ScheduleTest input,ScheduleTestInput scheduleTestInput) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariable("candidatename", candidate.getFullName());
        context.setVariable("picname", pic.getFullName());
        context.setVariable("Location", input.getLocation());
        context.setVariable("Date", scheduleTestInput.getDate());
        context.setVariable("startTime", scheduleTestInput.getStartTime());
        context.setVariable("endTime", scheduleTestInput.getEndTime());
        context.setVariable("test", input.getTest().getName());

        String html = templateEngine.process("template-interview", context);

        System.out.println("ini location: " + input.getLocation());
        System.out.println("ini date,start,end " + input.getDate() + input.getStartTime() + input.getEndTime());
        helper.setTo("jonathanpurnama13@gmail.com"); //ini di isi candidate.getEmail()
        helper.setText(html, true);
        helper.setSubject("Next Exam - Metrodata Recruitment");
        helper.setFrom("metrodata.recruitment3@gmail.com");
        javaMailSender.send(message); //jgn lupa di uncomment
    }

    public void sendSchedulePic(User candidate, User pic, ScheduleTest input,ScheduleTestInput scheduleTestInput) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        
        context.setVariable("candidatename", candidate.getFullName());
        context.setVariable("picname", pic.getFullName());
        context.setVariable("Date", scheduleTestInput.getDate());
        context.setVariable("startTime", scheduleTestInput.getStartTime());
        context.setVariable("endTime", scheduleTestInput.getEndTime());
        context.setVariable("test", input.getTest().getName());
        context.setVariable("Location", input.getLocation());

        String html = templateEngine.process("template-psikotest", context);

//        System.out.println("ini program " + pa.getProgram().getName());
//        System.out.println("ini note " + pa.getNote());
//        System.out.println(pa.getCandidate().getEmail());
//        System.out.println(pa.getCandidate().getFullName());
//        System.out.println(pa.getHr().getFullName());
        helper.setTo("jonathanpurnama13@gmail.com"); //ini di isi pic.getEmail();
        helper.setText(html, true);
        helper.setSubject("Next Jobs- Metrodata Recruitment");
        helper.setFrom("metrodata.recruitment3@gmail.com");
        javaMailSender.send(message); //jgn lupa di uncomment
    }

}
