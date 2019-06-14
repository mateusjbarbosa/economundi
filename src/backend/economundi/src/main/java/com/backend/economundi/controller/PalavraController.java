package com.backend.economundi.controller;

import com.backend.economundi.entity.Palavra;
import com.backend.economundi.service.PalavraService;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PalavraController {
    
    private PalavraService service = new PalavraService();
    
    @GetMapping("/api/palavra/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Palavra palavra = service.readById(id);
        
        if (palavra != null) {
            return new ResponseEntity<>(palavra, null, HttpStatus.ACCEPTED);
        } else
        {
            return new ResponseEntity<>(palavra, null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/api/palavra")
    public ResponseEntity add(@RequestBody Palavra palavra) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> erros = service.validate(palavra);
        if (erros.isEmpty()) {
            service.creat(palavra);
            httpHeaders.add("Location", "/palavra/" + palavra.getId());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
        } else
        {
            return new ResponseEntity<>(erros, null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
