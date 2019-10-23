/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.database.dao.entity;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "O EMAIL NÃO PODE SER VAZIO")
    @Email(message = "O EMAIL NÃO É VALIDO")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "O CAMPO FIRSTNAME NÃO PODE SER VAZIO")
    private String first_name;

    @NotEmpty(message = "O CAMPO LASTNAME NÃO PODE VAZIO")
    private String last_name;

    @NotEmpty(message = "O CAMPO SENHA NÃO PODE SER VAZIO")    
    private String password;

    private Date date_birth;

    @NotEmpty(message = "O CAMPO PERMISSION NÃO PODE SER VAZIO")
    private String permission;

    private String economic_profile;

    private String emailVerificationToken;

    private Timestamp date_hour_register;
    
    private String profile_picture;
    
    private boolean news_letter_active;

    public boolean isNews_letter_active() {
        return news_letter_active;
    }

    public void setNews_letter_active(boolean news_letter_active) {
        this.news_letter_active = news_letter_active;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getEconomic_profile() {
        return economic_profile;
    }

    public void setEconomic_profile(String economic_profile) {
        this.economic_profile = economic_profile;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public Timestamp getDate_hour_register() {
        return date_hour_register;
    }

    public void setDate_hour_register(Timestamp date_hour_register) {
        this.date_hour_register = date_hour_register;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }
    
    

}
