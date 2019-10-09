/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandre
 */
@Component
public class PasswordEncoder {
    
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public String encodeUserPassword(String password){      
        
       return passwordEncoder.encode(password);
    }  
    
}
