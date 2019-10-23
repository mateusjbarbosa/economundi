/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.service;

import com.backend.economundi.database.dao.INewsLetterDao;
import com.backend.economundi.database.dao.entity.EmailNewsLetterDto;
import com.backend.economundi.database.dao.entity.NewsLetterDto;
import com.backend.economundi.database.dao.entity.WordEntity;
import com.backend.economundi.database.dao.impl.NewsLetterDao;
import com.backend.economundi.payload.EmailTemplates;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alexandre
 */
@Service
public class NewsLetterService {

    private final INewsLetterDao newsLetterDao = new NewsLetterDao();

    EmailTemplates emailTemplate = new EmailTemplates();

    EmailService sendEmail = new EmailService();

    public List<NewsLetterDto> getNewsForNewsLetter(String locality) {

        List<NewsLetterDto> newsLetterList = new ArrayList<>();

        newsLetterList = newsLetterDao.getNewsletterEmail(locality);

        return newsLetterList;
    }

    public List<WordEntity> getWordForNewsLetter() {

        List<WordEntity> wordList = new ArrayList<>();

        wordList = newsLetterDao.getWordNewsEmail();

        return wordList;
    }

    public String getTemplateNewsLetter() {

        List<NewsLetterDto> newsLetterBrazilList = getNewsForNewsLetter("Brazil");

        List<NewsLetterDto> newsLetterWorldList = getNewsForNewsLetter("World");

        List<WordEntity> wordList = getWordForNewsLetter();

        String template = emailTemplate.getNewsLetterTemplate(newsLetterBrazilList, newsLetterWorldList, wordList);

        return template;
    }

    public List<EmailNewsLetterDto> getListEmailNewsLetter() {

        List<EmailNewsLetterDto> emailList = new ArrayList<>();

        emailList = newsLetterDao.getEmailNewsLetter();

        return emailList;
    }

    public boolean sendEmailNewsLetter() throws MessagingException {

        List<EmailNewsLetterDto> emailList = new ArrayList<>();
        String template = "";

        template = getTemplateNewsLetter();

        emailList = getListEmailNewsLetter();

        for (EmailNewsLetterDto item : emailList) {
            sendEmail.sendMail(item.getEmail(), "News Letter EconoMundi", template);
        }
    
        return true;
    }
}
