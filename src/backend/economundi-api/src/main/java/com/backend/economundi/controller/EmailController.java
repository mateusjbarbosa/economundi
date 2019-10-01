///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.backend.economundi.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// * @author Alexandre
// */
//@RestController
//public class EmailController {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @GetMapping(path = "/email-send")
//    public String sendMail() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText("Hello from Spring Boot Application");
//        message.setTo("asribeiro@linear.com.br");
//        message.setFrom("alexandresilva58@gmail.com");
//
//        try {
//            mailSender.send(message);
//            return "Email enviado com sucesso!";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Erro ao enviar email.";
//        }
//    }
//}
