package com.backend.economundi.controller;

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

import com.backend.economundi.database.dao.entity.NewsBlackList;
import com.backend.economundi.service.NewsBlackListService;

@CrossOrigin
@RestController
public class NewsBlackListController {
    private final String PATH_URL = "/api/v1/news-black-list/";
    private final NewsBlackListService service = new NewsBlackListService();
	
    /**
     * Cria um novo item na black list.
     * @param name Nova palavra que será adicionada ao black list.
     * @return	Erros, se houver.
     */
	@PostMapping(PATH_URL)
	public ResponseEntity<?> add(@RequestBody NewsBlackList name) {
		HttpHeaders httpHeaders = new HttpHeaders();
		Map<String, String> errors = service.create(name);
		
		if (errors.isEmpty()) {
			if (name.getId() != null && name.getId() != 0) {
				httpHeaders.add("Location", "/balcklist/" + name.getId());
				return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
			} else {
				errors.put("Nome", "Item já está na black list!");
			}
		}
		
		return new ResponseEntity<>(errors, null, HttpStatus.NOT_ACCEPTABLE);
	}
	
	/**
	 * Realiza a leitura de todos os itens da black list.
	 * @return Black list.
	 */
	@GetMapping(PATH_URL + "read")
	public ResponseEntity<?> readAll() {
		return new ResponseEntity<>(service.readAll(), null, HttpStatus.ACCEPTED);
	}
	
	/**
	 * Atualiza um item da black list.
	 * @param id Identificador do item a ser editado.
	 * @param body Conjunto com chave e valor da edição.
	 * @return Motivo do erro, caso exista algum campo inválido.
	 */
	@PatchMapping(PATH_URL + "{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
		body.put("id", Long.toString(id));
		
		Map <String, String> errors = service.update(body);
		
		if (errors.isEmpty()) {
			return new ResponseEntity<>(null, null, HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>(errors, null, HttpStatus.NOT_ACCEPTABLE);
	}
	
	/**
	 * Deleta um item da black list.
	 * @param id Identificador do item.
	 */
	@DeleteMapping(PATH_URL + "{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
}
