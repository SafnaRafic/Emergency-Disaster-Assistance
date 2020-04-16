package org.SafnaRafic.emergencydisasterhelpline.controllers;

//import org.SafnaRafic.emergencydisasterhelpline.mail.ContactSender;
//import org.SafnaRafic.emergencydisasterhelpline.models.ContactUs;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.ValidationException;


import org.SafnaRafic.emergencydisasterhelpline.models.ContactUs;
import org.SafnaRafic.emergencydisasterhelpline.models.EmailCfg;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
@RestController
@RequestMapping("/contact-us")
public class ContactController {


    private EmailCfg emailCfg;

    public ContactController(EmailCfg emailCfg) {
        this.emailCfg = emailCfg;
    }

    @PostMapping
    public void sendFeedback(@RequestBody ContactUs contactUs,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Information is not valid");
        }

        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(contactUs.getEmail());
        mailMessage.setTo("rc@feedback.com");
        mailMessage.setSubject("New contact from " + contactUs.getName());
        mailMessage.setText(contactUs.getComments());

        // Send mail
        mailSender.send(mailMessage);
    }
}