/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.EmailNewsLetterDto;
import com.backend.economundi.database.dao.entity.NewsLetterDto;
import com.backend.economundi.database.dao.entity.WordEntity;
import java.util.List;

/**
 *
 * @author Alexandre
 */

public interface INewsLetterDao {
    
    public List<NewsLetterDto> getNewsletterEmail(String locality); 

    public List<WordEntity> getWordNewsEmail();
    
    public List<EmailNewsLetterDto> getEmailNewsLetter();
    
}
