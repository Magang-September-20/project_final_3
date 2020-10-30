/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

import com.metrodata.consumeApiFinal.entities.Program;
import com.metrodata.consumeApiFinal.entities.ProgramApply;
import com.metrodata.consumeApiFinal.entities.User;
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
 * @author Olivia Michele
 */
@Service
public class EmailNotificationProgram {

    JavaMailSender javaMailSender;

    @Autowired
    public EmailNotificationProgram(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmailCandidate(User candidate, User hr, ProgramApply pa) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariable("name", candidate.getFullName());
        context.setVariable("program", pa.getProgram().getName());
        context.setVariable("email", candidate.getEmail());
        String html = templateEngine.process("template-program", context);

        System.out.println("ini program " + pa.getProgram().getName());
        System.out.println("ini note " + pa.getNote());
        System.out.println(pa.getCandidate().getEmail());
        System.out.println(pa.getCandidate().getFullName());
        System.out.println(pa.getHr().getFullName());
        helper.setTo(candidate.getEmail()); //ini di isi pa.getCandidate().getEmail()
        helper.setText(html, true);
        helper.setSubject("Register Program Success - Metrodata Recruitment " + pa.getProgram().getName());
        System.out.println(pa.getProgram().getName());
        helper.setFrom("metrodata.recruitment3@gmail.com");
        javaMailSender.send(message);
    }

    public void sendEmailCongrat(User candidate, ProgramApply pa) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariable("name", candidate.getFullName());
        context.setVariable("program", pa.getProgram().getName());
        context.setVariable("email", candidate.getEmail());
        String html = templateEngine.process("template-congrats", context);
        helper.setTo(candidate.getEmail());
        helper.setText(html, true);
        helper.setSubject("Congratulation : You are accepted on " + pa.getProgram().getName() + " !!");
        System.out.println(pa.getProgram().getName());
        helper.setFrom("metrodata.recruitment3@gmail.com");
        javaMailSender.send(message);
    }

}
