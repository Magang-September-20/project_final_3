/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.ScheduleTest;
import com.metrodata.consumeApiFinal.entities.dao.RegisterInput;
import java.nio.charset.StandardCharsets;
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
    public void sendPsikotest(ScheduleTest input) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("Date", input.getDate());
        context.setVariable("startTime", input.getStartTime());
        context.setVariable("endTime", input.getEndTime());
        context.setVariable("test", input.getTest().getName());

        String html = templateEngine.process("template-psikotest", context);

        System.out.println(input.getApply());
        helper.setTo(input.getApply().getCandidate().getEmail());
        helper.setText(html, true);
        helper.setSubject("Schedule Psikotest Test - Metrodata Recruitment");
        helper.setFrom("metrodata.recruitment3@gmail.com");

        javaMailSender.send(message);
    }
    public void sendTechnical(ScheduleTest input) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("Date", input.getDate());
        context.setVariable("startTime", input.getStartTime());
        context.setVariable("endTime", input.getEndTime());
        context.setVariable("test", input.getTest().getName());

        String html = templateEngine.process("template-technical", context);

        System.out.println(input.getApply());
        helper.setTo(input.getApply().getCandidate().getEmail());
        helper.setText(html, true);
        helper.setSubject("Schedule Technical Test - Metrodata Recruitment");
        helper.setFrom("metrodata.recruitment3@gmail.com");

        javaMailSender.send(message);
    }
    public void sendInterview(ScheduleTest input) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("Name", input.getApply().getCandidate().getFullName());
        context.setVariable("Date", input.getDate());
        context.setVariable("startTime", input.getStartTime());
        context.setVariable("endTime", input.getEndTime());
        context.setVariable("test", input.getTest().getName());

        String html = templateEngine.process("template-interview", context);

        System.out.println(input.getApply());
        helper.setTo(input.getApply().getCandidate().getEmail());
        helper.setText(html, true);
        helper.setSubject("Schedule Interview Test - Metrodata Recruitment");
        helper.setFrom("metrodata.recruitment3@gmail.com");

        javaMailSender.send(message);
    }
}
