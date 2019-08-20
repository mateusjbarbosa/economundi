package com.backend.economundi.controller;

import com.backend.economundi.entity.Word;
import com.backend.economundi.service.WordService;
import java.util.HashMap;
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
    
    private final WordService service = new WordService();
    
    /**
     * Pesquisa de palavra por id ou string.
     * @param search String com id ou substring.
     * @return Nenhuma, uma ou mais palavras.
     */
    @GetMapping("/api/palavra/{id}")
    public ResponseEntity getWord(@PathVariable("id") String search) {
        
        try {
            Long id = Long.parseLong(search);
            Word palavra = service.readById(id);
            return new ResponseEntity<>(palavra, null, HttpStatus.ACCEPTED);
        } catch (NumberFormatException e){
            Map <Long, String> palavras = service.readBySubString(search);
            return new ResponseEntity<>(palavras, null, HttpStatus.ACCEPTED);
        }
    }
    
    /**
     * Informa as palavras mais pesquisadas no sistema.
     * @return Conjunto com id e sua palavra correspondente.
     */
    @GetMapping("/api/palavra/top")
    public ResponseEntity getTop() {
        return new ResponseEntity<>(service.getTopSearch(), null, HttpStatus.ACCEPTED);
    }
    
    /**
     * Adiciona nova palavra ao sistema.
     * @param palavra Nova palavra.
     * @return Se sucesso, o seu ID. Caso contrário, é retornado o motivo do
     * erro.
     */
    @PostMapping("/api/palavra")
    public ResponseEntity add(@RequestBody Word palavra) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> erros = service.validate(palavra);
        
        if (erros.isEmpty()) {
            service.create(palavra);
            httpHeaders.add("Location", "/palavra/" + palavra.getId());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(erros, null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    /**
     * Atualiza um ou mais valores na palavra.
     * @param id O id da palavra a ser editado.
     * @param body Conjunto com chave e valor da edição.
     * @return Motivo do erro, caso exista algum campo inválido.
     */
    @PatchMapping("/api/palavra/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
        body.put("id", Long.toString(id));
        
        Map <String, String> erros;
        Word palavra = service.merge(body);
        
        if (palavra != null) {
            erros = service.validate(palavra);

            if (erros.isEmpty()) {
                HttpHeaders httpHeaders = new HttpHeaders();
                service.update(palavra);
                httpHeaders.add ("Location", "/palavra/" + id);
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
           }
        } else {
            erros = new HashMap<>();
            
            erros.put("Palavra", "Id inexistente.");
        }
        
        return new ResponseEntity<>(erros, null, HttpStatus.NOT_ACCEPTABLE);
    }
    
    /**
     * Deleta palavra no sistema
     * @param id Identificador da palavra.
     */
    @DeleteMapping("/api/palavra/{id}")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }
}
