package com.backend.economundi.service;

import com.backend.economundi.database.dao.impl.WordDao;
import com.backend.economundi.entity.Word;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordService {
    private static Long sequence = 0L;
    private static final List<Word> LIST = new ArrayList<>();
    private static final Map<Long, Word> MAP = new HashMap<>();
    private static final List<Word> WORD_TOP = new ArrayList();
    private final Integer TOP = 5;
    
    /**
     * 
     * Adiciona aos controles do dicionário.
     * 
     * @param word Nova palavra a ser criada.
     */
    public void create (Word word) {
        if (word != null) {
            WordDao wordDao = new WordDao();
            
            wordDao.create(word);
            wordDao.closeConnection();
        }
    }
    
    /**
     * Pesquisa de palavras por id.
     * 
     * @param id Identificador da palavra a ser encontrada.
     * @return Palavra caso exista.
     */
    public Word readById(Long id) {
        WordDao wordDao = new WordDao();
        Word word = wordDao.readById(id);
        
        /*
        @TODO Implementar o usuário responsável pela pesquisa (tabela
        usuario_pesquisa_palavra).
         */
        wordDao.closeConnection();
        
        return word;
    }
    
    /**
     * Realiza pesquisa por substring.
     * 
     * @param substring A string que se deseja encontrar.
     * @return Id e nome da palabra que contém a substring.
     */
    public Map<Long, String> readBySubString(String substring) {
        Map<Long, String> words = new HashMap();
        
        LIST.stream().filter((word) -> 
            (word.getName().toLowerCase().
             contains(substring.toLowerCase()))).forEachOrdered((word) -> {
                words.put(word.getId(), word.getName());
            });
        
        return words;
    }
    
    /**
     * Atualiza a palavra.
     * @param word Palavra com suas modificações.
     */
    public void update(Word word) {
        WordDao wordDao = new WordDao();
        
        wordDao.update(word);
        wordDao.closeConnection();
    }
    
    /**
     * Deleta a palavra.
     * @param id Identificador da palavra que se deseja deletar.
     */
    public void delete(Long id) {
        WordDao wordDao = new WordDao();
        Word word = wordDao.readById(id);
        
        if (word != null) {
            wordDao.delete(word);
        }
        
        wordDao.closeConnection();
    }
    
    /**
     * Realiza o merge apenas dos valores que estiver mapeado.
     * @param data Mapeamento com um conjunto de chave e valor.
     * @return Palavra editada, se houver.
     */
    public Word merge(Map<String, String> data) {
        Word merged = null;
        
        if (data != null)
        {
            Long id = Long.parseLong(data.get("id"));
            
            if (id != 0)
            {
                WordDao wordDao = new WordDao();
                Word word = wordDao.readById(id);
                
                wordDao.closeConnection();
                
                if (word != null)
                {
                    String key = "nome";
                    
                    merged = new Word();
                    
                    merged.setId(word.getId());
                    merged.setName(word.getName());
                    merged.setDescription(word.getDescription());
                    
                    if (data.containsKey(key)) {
                        merged.setName(data.get(key));
                    }
                    
                    key = "descricao";
                    
                    if (data.containsKey(key)) {
                        merged.setDescription(data.get(key));
                    }
                    
                    merged.setAmountSearch(word.getAmountSearch());
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
    public Map<String, String> validate(Word word) {
        Map<String, String> errors = new HashMap<>();
        
        if (word != null) {
            String name = word.getName();
            
            if (name == null) {
                errors.put("Nome", "A palavra precisa de um nome.");
            }
            
            String description = word.getDescription();
            
            if (description == null) {
                errors.put("Descrição", "A palavra precisa de uma definição");
            }
        } else {
            errors.put("Entidade", "Não há palavra.");
        }
        
        return errors;
    }
    
     /**
     * Monta uma lista com as top 5 palavras mais pesquisadas.
     */
    public void topSearch() {
        LIST.forEach((word) -> {
            if (WORD_TOP.size() < TOP) {
                if (!WORD_TOP.contains(word)) {
                    WORD_TOP.add(word);
                }
            } else {
                Word wordBackup;
                
                for (int pos = 0; pos < TOP; pos++) {
                    Word wordAnalyze = WORD_TOP.get(pos);
                    
                    if (!WORD_TOP.contains(word)) {
                        if (word.getAmountSearch() >= 
                            wordAnalyze.getAmountSearch()) {
                            wordBackup = wordAnalyze;
                            WORD_TOP.set(pos, word);
                            
                            for (int posSubs = (pos + 1); (posSubs < TOP);
                                posSubs++) {
                                wordAnalyze = WORD_TOP.get(posSubs);
                                WORD_TOP.set(posSubs, wordBackup);
                                wordBackup = wordAnalyze;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        });
    }
    
    /**
     * Informa as palavras mais pesquisadas no sistema.
     * @return Mapa com id e palavra (correspondente) mais pesquisadas.
     */
    public Map<Long, String> getTopSearch() {
        Map<Long, String> mapTop = new HashMap();
        
        WORD_TOP.forEach((word) -> {
            mapTop.put(word.getId(), word.getName());
        });
        
        return mapTop;
    }
}