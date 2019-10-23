/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Alexandre
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override    
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("https://economundi-frontend.herokuapp.com")
                .allowedOrigins("https://economundi-frontend.herokuapp.com")
                .allowedMethods("PUT")
                .allowedMethods("GET")
                .allowedMethods("POST")
                .allowedMethods("DELETE");
    }

}
