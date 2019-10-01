package com.backend.economundi.controller;

import com.backend.economundi.config.JWTAuthenticationFilter;
import com.backend.economundi.error.ResourceNotFoundException;
import com.backend.economundi.model.UserEntity;
import com.backend.economundi.payload.LoginRequest;
import com.backend.economundi.repository.UserRepository;
import com.backend.economundi.util.JwtUtil;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

//    private JWTAuthenticationFilter tokenAuth;
//    private AuthenticationManager authenticationManager;
    
    @Autowired
    private final JwtUtil jwt = null;

    
    private final UserRepository userDao;   
   
    @Autowired
    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    } 
    
    @GetMapping(path = "public/getlogin")
    public ResponseEntity getLogin() {      
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();       
        
        String username = auth.getName().toString();          
        
        UserEntity user = new UserEntity();          
               
        String token = jwt.generateToken(username);
        
        return new ResponseEntity<>( token, HttpStatus.OK);
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

    @GetMapping(path = "protected/users/findbyname/{name}")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity findUserByEmail(@PathVariable String email) {

        return new ResponseEntity<>(userDao.findByEmail(email), HttpStatus.OK);
    }

    @PostMapping(path = "public/create")
    public ResponseEntity save(@Valid @RequestBody UserEntity user) {

        return new ResponseEntity(userDao.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "admin/users/{id}")
    @Secured("hasRole('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        verifyIfUserExists(id);

        userDao.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "protected/update")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity update(@RequestBody UserEntity user) {
        verifyIfUserExists(user.getId());
        userDao.save(user);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    private void verifyIfUserExists(Long id) {

        if (!userDao.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Student Not Found for id = " + id);
        }
    }
}
