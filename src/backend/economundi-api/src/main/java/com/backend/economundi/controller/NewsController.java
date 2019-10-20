package com.backend.economundi.controller;

import com.backend.economundi.database.dao.entity.NewsEntity;
import com.backend.economundi.service.NewsService;
import com.backend.economundi.service.ValidateUserService;

import java.util.HashMap;
import java.util.Map;   

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class NewsController {

    private final String PATH_URL = "/api/v1/news/";
    private final NewsService serviceNews = new NewsService();
    private final ValidateUserService serviceUser = new ValidateUserService();

    /**
     * Retorna as notícias de acordo com a página indicada.
     *
     * @param page Valor inteiro informando a página.
     * @param locality Localização da notícia.
     * @return Notícias de uma determinada página.
     */
    @GetMapping(PATH_URL + "{locality}/" + "{page}")
    public ResponseEntity<?> getNewsByPage(@PathVariable("locality") String locality,
            @PathVariable("page") Long page) {
        return new ResponseEntity<>(serviceNews.readByPage(page, locality),
                null, HttpStatus.ACCEPTED);
    }

    /**
     * Retorna uma notícia com base no Id.
     *
     * @param id Indentificação da notícia.
     * @return Notícia, caso encontre.
     */
    @GetMapping(PATH_URL + "{id}")
    public ResponseEntity<?> getNews(@PathVariable("id") Long id) {
        NewsEntity news = serviceNews.readById(id);

        if (news != null) {
            return new ResponseEntity<>(news, null, HttpStatus.ACCEPTED);
        } else {
            Map<String, String> error = new HashMap<>();

            error.put("Erro", "Notícia não encontrada!");

            return new ResponseEntity<>(error, null, HttpStatus.ACCEPTED);
        }
    }
    
    @PostMapping(PATH_URL + "/protected/{id}/" + "{typeLike}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> likeNews(@PathVariable("id") Long id,
            @PathVariable("typeLike") String typeLike) {
        String user = serviceUser.verifyUserLogin();
        
        return new ResponseEntity<>(user, null, HttpStatus.ACCEPTED);
    }
}
