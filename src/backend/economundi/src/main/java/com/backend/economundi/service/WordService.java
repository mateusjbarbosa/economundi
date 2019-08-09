package com.backend.economundi.service;

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
      * Adiciona aos controles do dicionário.
      * 
      * @param word Nova palavra a ser criada.
      */
    public void create (Word word) {
        if (word != null) {
            Long id = ++sequence;
            word.setId(id);
            word.setAmountSearch(0L);
            LIST.add(word);
            MAP.put(id, word);
        }
    }
    
    public Word readById(Long id) {
        Word word = MAP.get(id);
        
        if (word != null) {
            Long amountSearch = word.getAmountSearch();
            word.setAmountSearch(++amountSearch);
        }

        return word;
    }
    
    public Map<Long, String> readBySubString(String name) {
        Map<Long, String> words = new HashMap();
        
        LIST.stream().filter((word) -> 
            (word.getName().toLowerCase().
             contains(name.toLowerCase()))).forEachOrdered((word) -> {
                words.put(word.getId(), word.getName());
            });
        
        return words;
    }
    
    public void update(Word word) {
        Long id = word.getId();
        Word oldWord = MAP.get(id);
        
        if (oldWord != null) {
            LIST.remove(oldWord);
            LIST.add(word);
            MAP.put(id, word);
        }
    }
    
    public void delete(Long id) {
        Word word = MAP.get(id);
        
        if (word != null) {
            LIST.remove(word);
            MAP.remove(id);
        }
    }
    
    public Word merge(Map<String, String> data) {
        Word merged = null;
        
        if (data != null)
        {
            Long id = Long.parseLong(data.get("id"));
            
            if (id != 0)
            {
                Word word = MAP.get(id);
                
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
    
    public Map<Long, String> getTopSearch() {
        Map<Long, String> mapTop = new HashMap();
        
        WORD_TOP.forEach((word) -> {
            mapTop.put(word.getId(), word.getName());
        });
        
        return mapTop;
    }
}