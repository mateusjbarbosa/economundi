package com.backend.economundi.controller;

import com.backend.economundi.database.dao.entity.User;
import com.backend.economundi.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {         
  
    @Autowired
    UserService userService;    

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> list() {
        
        List<User> userList = new ArrayList<>();
        
        userList = userService.readAll();
        return userList;
    }

    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void save(@RequestBody User user) {
        userService.create(user);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void edit(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<User> detail(@PathVariable Long id) {

        userService.readById(id);

        return null;
    }

}
