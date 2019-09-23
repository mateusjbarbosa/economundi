package com.backend.economundi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backend.economundi.database.dao.entity.NewsBlackList;
import com.backend.economundi.database.dao.impl.NewsBlackListDao;

public class NewsBlackListService {
	
	/**
	 * Cria um novo item na black list
	 * @param name Novo item a ser adicionado.
	 * @return Motivo de erro, caso exista.
	 */
	public Map<String, String> create(NewsBlackList name) {
		Map<String, String> errors = validate(name);

		if (errors.isEmpty()) {
			NewsBlackListDao newsBLDao = new NewsBlackListDao();

			name.setName(name.getName().toUpperCase().trim());

			newsBLDao.create(name);
		}

		return errors;
	}

	/**
	 * Lê todos os elementos da black list.
	 * @return Black list.
	 */
	public List<NewsBlackList> readAll() {
		NewsBlackListDao newsBLDao = new NewsBlackListDao();
		List<NewsBlackList> newsBLList = newsBLDao.readAll();

		return newsBLList;
	}

	/**
	 * Atualiza algum item da black list.
	 * @param body Conjunto de chave e valor com o que se deseja mudar.
	 * @return Motivo de erro, caso exista.
	 */
	public Map<String, String> update(Map<String, String> body) {
		NewsBlackList newsBL = merge(body);
		Map<String, String> errors = validate(newsBL);
		
		if (errors.isEmpty()) {
			NewsBlackListDao newsBLDao = new NewsBlackListDao();
			
			newsBLDao.update(newsBL);
		}
		
		return errors;
	}
	
	/**
	 * Deleta um item da black list.
	 * @param id Identificador do elemento.
	 */
	public void delete(Long id) {
		NewsBlackListDao newsBLDao = new NewsBlackListDao();
		NewsBlackList newsBL = newsBLDao.read(id);
		
		if (newsBL != null) {
			newsBLDao.delete(newsBL);
		}
	}

	/**
	 * Valida a entidade verificando seus campos.
	 * @param name Item a ser analizado.
	 * @return Erro, caso exista.
	 */
	private Map<String, String> validate(NewsBlackList name) {
		Map<String, String> errors = new HashMap<>();

		if (name != null) {
			if (name.getName() == null || name.getName().isEmpty()) {
				errors.put("Nome", "Não palavra para ser adicionada na black list");
			}
		} else {
			errors.put("Entidade", "Não há novo item.");
		}

		return errors;
	}

	/**
	 * Modifica apenas os campos mapeados.
	 * @param data Mapeamento dos campos a se alterado o id do elemento.
	 * @return Entidade com os campos alterados.
	 */
	private NewsBlackList merge(Map<String, String> data) {
		NewsBlackList merged = null;
		
		if (data != null) {
			Long id = Long.parseLong(data.get("id"));
			
			if  (id != 0) {
				NewsBlackListDao newsBLDao = new NewsBlackListDao();
				NewsBlackList newsBL = newsBLDao.read(id);
				
				if  (newsBL != null) {
					String key = "name";
					
					merged = new NewsBlackList();
					
					merged.setId(id);
					merged.setName(newsBL.getName());
					
					if (data.containsKey(key)) {
						merged.setName(data.get(key).toUpperCase().trim());
					}
				}
			}
		}
		
		return merged;
	}
}
