/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.consumeApiFinal.services;

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
}
