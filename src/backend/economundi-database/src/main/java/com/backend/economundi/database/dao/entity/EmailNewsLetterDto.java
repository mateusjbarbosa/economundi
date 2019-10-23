/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.database.dao.entity;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexandre
 */
public class EmailNewsLetterDto {
    
   private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
