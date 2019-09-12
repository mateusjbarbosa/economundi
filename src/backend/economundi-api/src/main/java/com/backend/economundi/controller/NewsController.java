package com.backend.economundi.controller;

import com.backend.economundi.database.dao.entity.News;
import com.backend.economundi.service.NewsService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class NewsController {
    private final String PATH_URL = "/api/v1/noticias/";
    private final NewsService service = new NewsService();
    
    /**
     * Retorna as notícias de acordo com a página indicada.
     * @param page Valor inteiro informando a página.
     * @return As notícias de uma determinada página.
     */
    @GetMapping(PATH_URL + "{page}")
    public ResponseEntity getNewsBrazil(@PathVariable("page") Long page) {
        return new ResponseEntity<>(service.readByPage(page), null, HttpStatus.ACCEPTED);
    }
}
