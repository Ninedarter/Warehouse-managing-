package com.warehouse.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendingService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage() {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("l.sarpauskas@gmail.com");
        message.setSubject("2");
        message.setText("laba, siunciu pirma laiska is javos");
        emailSender.send(message);
    }
}

