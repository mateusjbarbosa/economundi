/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.User;
import java.util.List;


/**
 *
 * @author Alexandre
 */
public interface IUserDao {
    public static String ID = "id";
    public static String FIRSTNAME = "nome";
    public static String LASTNAME = "sobrenome";
    public static String PASSWORD = "senha";
    public static String BIRTH = "data_nasc";
    public static String ECONOMIC_USER = "perfil_economico";
    public static String DATETIME_SIGN = "data_hora_cadastro";
    public static String EMAIL = "email";
    public static String ROLE = "permissao";
    public static String ENTITY = "usuario";

    public void create(User user);
    
    public List<User> readAll();
    
    public User readById(Long id);
    
    public void update(User user);
    
    public void delete(Long id);
    
   // public void closeConnection();
    
    
    
}