package com.backend.economundi.service;

import com.backend.economundi.database.dao.impl.WordDao;
import com.backend.economundi.database.dao.entity.Word;
import com.backend.economundi.database.dao.impl.WordAccessDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordService {
    
    /**
     * 
     * Adiciona aos controles do dicionário.
     * 
     * @param word Nova palavra a ser criada.
     * @return Houve ou não sucesso na criação.
     */
    public Map<String, String> create (Word word) {
    	Map<String, String> errors = validate(word);
    	
    	if (errors.isEmpty())
    	{
    		WordDao wordDao = new WordDao();
        
    		word.setName(word.getName().toUpperCase().trim());
    		word.setDescription(word.getDescription().trim());
        
    		wordDao.create(word);
    	}
    	
    	return errors;
    }
    
    /**
     * Pesquisa de palavras por id.
     * 
     * @param id Identificador da palavra a ser encontrada.
     * @return Palavra caso exista.
     */
    public Word readById(Long id) {
        WordDao wordDao = new WordDao();
        Word word = wordDao.read(id);
        
        if (word != null) {
            WordAccessDao wordAccessDao = new WordAccessDao();
            wordAccessDao.create(id);
        }
        
        return word;
    }
    
    /**
     * Realiza pesquisa por substring.
     * 
     * @param substring A string que se deseja encontrar.
     * @return Id e nome da palabra que contém a substring.
     */
    public Map<Long, String> readBySubString(String substring) {
        WordDao wordDao = new WordDao();
        List<Word> wordList = wordDao.readByName(substring);
        Map<Long, String> wordMap = new HashMap<>();
        
        wordList.forEach((word) -> {
            wordMap.put(word.getId(), word.getName());
        });
        
        return wordMap;
    }
    
    /**
     * Atualiza a palavra.
     * @param word Palavra com suas modificações.
     * @return Motivo de erro, caso exista.
     */
    public Map <String, String> update(Map<String, String> body) {
    	Word word = merge(body);
    	Map <String, String> errors = validate(word);
    	
    	if (errors.isEmpty())
    	{
    		WordDao wordDao = new WordDao();
        
    		wordDao.update(word);
    	}
        
        return errors;
    }
    
    /**
     * Deleta a palavra.
     * @param id Identificador da palavra que se deseja deletar.
     */
    public void delete(Long id) {
        WordDao wordDao = new WordDao();
        Word word = wordDao.read(id);
        
        if (word != null) {
            wordDao.delete(word);
        }
    }
    
    /**
     * Realiza o merge apenas dos valores que estiver mapeado.
     * @param data Mapeamento com um conjunto de chave e valor.
     * @return Palavra editada, se houver.
     */
    private Word merge(Map<String, String> data) {
        Word merged = null;
        
        if (data != null)
        {
            Long id = Long.parseLong(data.get("id"));
            
            if (id != 0)
            {
                WordDao wordDao = new WordDao();
                Word word = wordDao.read(id);
                
                if (word != null)
                {
                    String key = "name";
                    
                    merged = new Word();
                    
                    merged.setId(word.getId());
                    merged.setName(word.getName());
                    merged.setDescription(word.getDescription());
                    
                    if (data.containsKey(key)) {
                        merged.setName(data.get(key).toUpperCase().trim());
                    }
                    
                    key = "description";
                    
                    if (data.containsKey(key)) {
                        merged.setDescription(data.get(key).trim());
                    }
                }
            }
        }
        
        return merged;
    }
    
    /**
     * Valida se os campos obrigatórios realmente estão preenchidos.
     * @param word Palavra a ser validada.
     * @return  Mapeamento com os erros, se houver.
     */
    private Map<String, String> validate(Word word) {
        Map<String, String> errors = new HashMap<>();
        
        if (word != null) {
            String name = word.getName();
            
            if (name == null || name.isEmpty()) {
                errors.put("Nome", "A palavra precisa de um nome.");
            }
            
            String description = word.getDescription();
            
            if (description == null || description.isEmpty()) {
                errors.put("Descrição", "A palavra precisa de uma definição");
            }
        } else {
            errors.put("Entidade", "Não há palavra.");
        }
        
        return errors;
    }
    
    /**
     * Informa as palavras mais pesquisadas no sistema.
     * @return Mapa com id e palavra (correspondente) mais pesquisadas.
     */
    public Map<Long, String> getTopSearch() {
        WordAccessDao wordAccessDao = new WordAccessDao();
        Map<Long, String> mapTop = wordAccessDao.getMostSearched();
        
        return mapTop;
    }
}