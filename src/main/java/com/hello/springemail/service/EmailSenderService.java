package com.hello.springemail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper= new MimeMessageHelper(message, true);



        helper.setFrom("no-reply@kbjcapital.co.th");
        helper.setTo(toEmail);
        helper.setText(body, false);
        helper.setSubject(subject);


        mailSender.send(message);


        System.out.println("Mail Sent successfully...");
        System.out.println("toEmail = " + toEmail);
        System.out.println("body = " + body);
    }
}
