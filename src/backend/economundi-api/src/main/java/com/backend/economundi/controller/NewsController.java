package com.backend.economundi.controller;

import com.backend.economundi.database.dao.entity.NewsEntity;
import com.backend.economundi.service.NewsService;

import java.util.HashMap;
import java.util.Map;   

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class NewsController {

    private final String PATH_URL = "/api/v1/news/";
    private final NewsService service = new NewsService();

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
        return new ResponseEntity<>(service.readByPage(page, locality),
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
        NewsEntity news = service.readById(id);

        if (news != null) {
            return new ResponseEntity<>(news, null, HttpStatus.ACCEPTED);
        } else {
            Map<String, String> error = new HashMap<>();

            error.put("Erro", "Notícia não encontrada!");

            return new ResponseEntity<>(error, null, HttpStatus.ACCEPTED);
        }
    }
}
