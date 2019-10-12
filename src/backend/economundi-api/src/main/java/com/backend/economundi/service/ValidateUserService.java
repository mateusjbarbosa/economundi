/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.service;

import com.backend.economundi.database.dao.entity.UserEntity;
import com.backend.economundi.repository.UserRepository;
import com.backend.economundi.util.PasswordEncoder;
import com.backend.economundi.util.Utils;
import com.google.gson.Gson;
import java.sql.Timestamp;
import java.time.Instant;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alexandre
 */
@Service
public class ValidateUserService {

    @Autowired
    UserRepository userDao;

    @Autowired
    private final PasswordEncoder passEncoder = null;

    @Autowired
    private Utils utils;

    public boolean verifyEmailExists(String email) {
        
        UserEntity newUser = userDao.findByEmail(email);
        
        if(newUser == null){
            return false;
        }else{
            return true;
        }       
    }

    public UserEntity prepareNewUser(UserEntity newUser) {

        // gera uma senha criptografada
        String password = newUser.getPassword();
        String passwordEncoded = passEncoder.encodeUserPassword(password);

        //descobre a data e hora atual para adicionar no campo de data hora cadastro
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();

        Timestamp dateSign_in = new Timestamp(timeStampMillis);
        newUser.setDate_hour_register(dateSign_in);
        newUser.setPassword(passwordEncoded);

        if (newUser.getPermission() == null) {
            newUser.setPermission("USER");
        }

        //gera um hash para recuperar a senha 
        newUser.setEmailVerificationToken(utils.generatedEmailToken(60));

        newUser.setEconomic_profile("None");

        return newUser;
    }
    
    public String verifyUserLogin(){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Gson gson = new Gson();
        String json = null;
        //Cria um Objeto JSON
        JSONObject jsonObject = new JSONObject();

        String username = auth.getName();

        if ("anonymousUser".equals(username)) {

            jsonObject.put("erro", "Usuario não Identificado");
            json = gson.toJson(jsonObject);
            return json;
        }

        UserEntity user = userDao.findByEmail(username);

        //Armazena dados em um Objeto JSON
        jsonObject.put("id",user.getId());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("permission", user.getPermission());
        jsonObject.put("FirstName", user.getFirst_name());
        jsonObject.put("LastName", user.getLast_name());
        jsonObject.put("birth",user.getDate_birth());

        //converte Object para json
        json = gson.toJson(jsonObject);
        
        return json;
    }

}
