/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.controller;

import com.backend.economundi.database.dao.entity.EmailNewsLetterDto;
import com.backend.economundi.database.dao.entity.NewsLetterDto;
import com.backend.economundi.database.dao.entity.WordEntity;
import com.backend.economundi.payload.EmailTemplates;
import com.backend.economundi.service.EmailService;
import com.backend.economundi.service.NewsLetterService;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alexandre
 */
@RestController
@RequestMapping("api/v1")
public class SendEmailTest {

    @Autowired
    EmailService sendEmail;

    @Autowired
    NewsLetterService newsLetterService;

    @GetMapping(path = "public/send")
    public ResponseEntity sendEmail() throws MessagingException {

        if (newsLetterService.sendEmailNewsLetter()) {
            return new ResponseEntity("Email Enviado Com Sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity("Erro Ao Enviar o Email", HttpStatus.NOT_FOUND);
        }

    }

}
