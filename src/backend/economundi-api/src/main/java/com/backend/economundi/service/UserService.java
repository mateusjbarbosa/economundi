/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.service;

import com.backend.economundi.database.dao.IUserDao;
import com.backend.economundi.database.dao.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Alexandre
 */
@Service
public class UserService {

    @Autowired
    IUserDao userDao;

    /**
     * Cria um novo usuario no sistema.
     *
     * @param user Usuario a ser criado.
     */
    public void create(User user) {
        if (user != null) {

            userDao.create(user);

        }
    }

    /**
     * Realiza o update do usuario.
     *
     * @param user Usuario a ser atualizada.
     */
    public void update(User user) {
        if (user != null) {
            if (user.getId() != null) {

                userDao.update(user);

            }
        }
    }

    /**
     * Realiza o delete do usuario.
     *
     * @param user Usuario a ser deletado.
     */
    public void delete(Long id) {

        userDao.delete(id);
    }

    /**
     * Realiza o leitura do usuario pelo id.
     *
     * @param user Usuario a ser buscado.
     */
    public User readById(Long id) {

        User returnUser = new User();

        returnUser = userDao.readById(id);

        return returnUser;
    }
    
    /**
     * Realiza o leitura do usuario pelo id.
     *
     * @param user Usuario a ser buscado.
     */
    public List<User> readAll() {

        List<User> returnUser = new ArrayList<>();
        returnUser = userDao.readAll();

        return returnUser;
    }

}
