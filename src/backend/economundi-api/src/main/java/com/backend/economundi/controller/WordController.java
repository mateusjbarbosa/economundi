package com.backend.economundi.controller;

import com.backend.economundi.database.dao.entity.Word;
import com.backend.economundi.service.WordService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class WordController {
    
    private final String PATH_URL = "/api/v1/palavra/";
    private final WordService service = new WordService();
    
    /**
     * Pesquisa de palavra por id ou string.
     * @param search String com id ou substring.
     * @return Nenhuma, uma ou mais palavras.
     */
    @GetMapping(PATH_URL + "{id}")
    public ResponseEntity getWord(@PathVariable("id") String search) {
        
        try {
            Long id = Long.parseLong(search);
            Word word = service.readById(id);
            
            if (word.getId() != null) {
                return new ResponseEntity<>(word, null, HttpStatus.ACCEPTED);
            } else {
                Map <String, String> error = new HashMap<>();
                
                error.put("Palavra", "Id inexistente.");
                
                return new ResponseEntity<>(error, null, HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e){
            Map<Long, String> mapWord = service.readBySubString(search);
            return new ResponseEntity<>(mapWord, null, HttpStatus.ACCEPTED);
        }
    }
    
    /**
     * Informa as palavras mais pesquisadas no sistema.
     * @return Conjunto com id e sua palavra correspondente.
     */
    @GetMapping(PATH_URL + "top")
    public ResponseEntity getTop() {
        return new ResponseEntity<>(service.getTopSearch(), null, HttpStatus.ACCEPTED);
    }
    
    /**
     * Adiciona nova palavra ao sistema.
     * @param word Nova palavra.
     * @return Se sucesso, o seu ID. Caso contrário, é retornado o motivo do
     * erro.
     */
    @PostMapping(PATH_URL)
    public ResponseEntity add(@RequestBody Word word) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> errors = service.validate(word);
        
        if (errors.isEmpty()) {
            word.setName(word.getName().toUpperCase().trim());
            word.setDescription(word.getDescription().trim());
            
            if (service.create(word)) {
                httpHeaders.add("Location", "/palavra/" + word.getId());
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
            } else {
                errors.put("Banco de dados", "Não foi possível adicionar a "
                        + "palavra.");
            }
        }
        
        return new ResponseEntity<>(errors, null, HttpStatus.NOT_ACCEPTABLE);
    }
    
    /**
     * Atualiza um ou mais valores na palavra.
     * @param id O id da palavra a ser editado.
     * @param body Conjunto com chave e valor da edição.
     * @return Motivo do erro, caso exista algum campo inválido.
     */
    @PatchMapping(PATH_URL + "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
        body.put("id", Long.toString(id));
        
        Map <String, String> errors;
        Word word = service.merge(body);
        
        if (word != null) {
            errors = service.validate(word);

            if (errors.isEmpty()) {
                HttpHeaders httpHeaders = new HttpHeaders();
                service.update(word);
                httpHeaders.add ("Location", "/palavra/" + id);
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
           }
        } else {
            errors = new HashMap<>();
            
            errors.put("Palavra", "Id inexistente.");
        }
        
        return new ResponseEntity<>(errors, null, HttpStatus.NOT_ACCEPTABLE);
    }
    
    /**
     * Deleta palavra no sistema
     * @param id Identificador da palavra.
     */
    @DeleteMapping(PATH_URL + "{id}")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }
}
