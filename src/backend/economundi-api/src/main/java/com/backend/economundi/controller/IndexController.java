package com.backend.economundi.controller;

import com.backend.economundi.service.IndexesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class IndexController {
    private final String PATH_URL = "/api/v1/indexes/";
    private final IndexesService service = new IndexesService();
    
    @GetMapping(PATH_URL)
    public ResponseEntity<?> getIndexes() {
        return new ResponseEntity<>(service.getIndexes(), null,
                HttpStatus.ACCEPTED);
    }
}
