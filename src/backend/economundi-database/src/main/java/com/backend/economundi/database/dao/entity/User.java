package com.backend.economundi.database.dao.entity;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandre
 */

public class User {

//    @Id
 //   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   // @Column(unique = true, nullable = false)
    private String email;
    
    //@Column(nullable = false)
    private String firstName;

    //@Column(nullable = false)
    private String lastName;

    //@Column(nullable = false)
    private String password;

    //@Column(nullable = false)
    private String birth;

   // @Column(nullable = false)
    private String role;

   // @Column(nullable = true)
    private String economicUser;

   // @Column(nullable = false)
    private Timestamp dateTimeSignin;

    public User() {

    }

    public User(String email, String firstName, String lastName, String password, String birth, String role, String economicUser, Timestamp dateTimeSignin) {
        
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birth = birth;
        this.role = role;
        this.economicUser = economicUser;
        this.dateTimeSignin = dateTimeSignin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String name) {
        this.firstName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEconomicUser() {
        return economicUser;
    }

    public void setEconomicUser(String economicUser) {
        this.economicUser = economicUser;
    }

    public Timestamp getDateTimeSignin() {
        return dateTimeSignin;
    }

    public void setDateTimeSignin(Timestamp dateTimeSignin) {
        this.dateTimeSignin = dateTimeSignin;
    }

}
