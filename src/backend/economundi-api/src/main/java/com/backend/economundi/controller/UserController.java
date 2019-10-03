package com.backend.economundi.controller;

import com.backend.economundi.database.dao.entity.UserEntity;
import com.backend.economundi.error.ResourceNotFoundException;
import com.backend.economundi.payload.EmailTemplates;
import com.backend.economundi.payload.RecoveryRequest;
import com.backend.economundi.repository.UserRepository;
import com.backend.economundi.service.EmailService;
import com.backend.economundi.util.JwtUtil;
import com.backend.economundi.util.PasswordEncoder;
import com.backend.economundi.util.Utils;
import com.google.gson.Gson;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.validation.Valid;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alexandre
 */
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class UserController {/*EndPoint e ponto final onde os usuarios vao acessar nossa api  */

    @Autowired
    private final PasswordEncoder passEncoder = null;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Utils utils;

    @Autowired
    private EmailTemplates emailTemplates;

    private final UserRepository userDao;

    @Autowired
    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping(path = "public/getlogin")
    public ResponseEntity getLogin() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Gson gson = new Gson();
        String json = null;
        //Cria um Objeto JSON
        JSONObject jsonObject = new JSONObject();

        String username = auth.getName();

        if ("anonymousUser".equals(username)) {

            jsonObject.put("erro", "Usuario não Identificado");
            json = gson.toJson(jsonObject);
            return new ResponseEntity(json, HttpStatus.OK);
        }

        UserEntity user = userDao.findByEmail(username);

        //Armazena dados em um Objeto JSON
        jsonObject.put("email", user.getEmail());
        jsonObject.put("permission", user.getPermission());
        jsonObject.put("Firstname", user.getFirst_name());
        jsonObject.put("LastName", user.getLast_name());

        //converte Object para json
        json = gson.toJson(jsonObject);
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @GetMapping(path = "protected/users")
    @Secured("hasRole('ROLE_ADMIN')")
    public ResponseEntity listAll(Pageable pageble) {

        return new ResponseEntity<>(userDao.findAll(pageble), HttpStatus.OK);
    }

    @GetMapping(path = "protected/users/{id}")
    @Secured("hasRole('ROLE_ADMIN')")
    public ResponseEntity getUserById(@PathVariable Long id,
            Authentication authentication) {

        System.out.println("Usuario" + authentication);

        verifyIfUserExists(id);
        Optional<UserEntity> user = userDao.findById(id);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping(path = "protected/users/findByEmail/{email}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity findUserByEmail(@PathVariable String email) {

        return new ResponseEntity<>(userDao.findByEmail(email), HttpStatus.OK);
    }

    @PostMapping(path = "public/create")
    public ResponseEntity save(@Valid @RequestBody UserEntity user) throws MessagingException {

        user = prepareNewUser(user);
        userDao.save(user);
        emailService.sendMail(user.getEmail(), "Cadastro Com Sucesso", emailTemplates.getTemplateSign(user.getFirst_name()));

        return new ResponseEntity("Cadastro Realizado Com Sucesso", HttpStatus.CREATED);
    }

    @GetMapping(path = "public/recovery/findByEmail/{email}")
    public ResponseEntity recovery(@PathVariable String email) throws MessagingException {

        UserEntity user = new UserEntity();

        user = userDao.findByEmail(email);
        emailService.sendMail(user.getEmail(), "Redefinir Senha", emailTemplates.getTemplateRecovery(user));

        return new ResponseEntity("Link de Redefinição de senha enviado por email", HttpStatus.OK);
    }

    @GetMapping(path = "public/recovery/findByToken/{token}")
    public ResponseEntity findUserByToken(@PathVariable String token) {

        UserEntity user = new UserEntity();

        user = userDao.findByEmailVerificationToken(token);

        if (user == null) {
            return new ResponseEntity("A página não existe", HttpStatus.OK);
        }

        return new ResponseEntity("Usuario Encontrado", HttpStatus.OK);
    }

    @PostMapping(path = "public/recovery")
    public ResponseEntity recoveryPassword(@Valid RecoveryRequest recoveryRequest) {

        UserEntity user = new UserEntity();

        user = userDao.findByEmailVerificationToken(recoveryRequest.getEmailVerificationToken());
        if (user != null) {
            user.setEmailVerificationToken(utils.generatedEmailToken(60));
            userDao.save(user);
        } else {
            return new ResponseEntity("Erro ao Atualizar Senha.", HttpStatus.OK);
        }

        return new ResponseEntity("Senha Atualizada com Sucesso", HttpStatus.OK);
    }

    @DeleteMapping(path = "admin/users/{id}")
    @Secured("hasRole('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        verifyIfUserExists(id);

        userDao.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "protected/update")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity update(@RequestBody UserEntity user) {
        verifyIfUserExists(user.getId());
        userDao.save(user);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    private void verifyIfUserExists(Long id) {

        if (!userDao.findById(id).isPresent()) {
            throw new ResourceNotFoundException("User Not Found for id = " + id);
        }
    }

    private UserEntity prepareNewUser(UserEntity newUser) {

        String password = newUser.getPassword();
        String passwordEncoded = passEncoder.encodeUserPassword(password);

        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();

        Timestamp dateSign_in = new Timestamp(timeStampMillis);
        newUser.setDate_hour_register(dateSign_in);
        newUser.setPassword(passwordEncoded);
        
        if(newUser.getPermission() == null){
            newUser.setPermission("USER");
        }
        
        newUser.setEmailVerificationToken(utils.generatedEmailToken(60));

        newUser.setEconomic_profile("None");

        return newUser;
    }
}
