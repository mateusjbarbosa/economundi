/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "_user")
public class UserEntity extends AbstractEntity {

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String first_name;

    @NotEmpty
    private String last_name;

    @NotEmpty   
    private String password;
    
    private Date date_birth;   

    @NotEmpty
    private String permission;

    private String economic_profile;    
    
    private Timestamp date_hour_register; 

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

    public Timestamp getDate_hour_register() {
        return date_hour_register;
    }

    public void setDate_hour_register(Timestamp date_hour_register) {
        this.date_hour_register = date_hour_register;
    }

   

}
