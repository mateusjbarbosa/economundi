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
    
    public void create (Word palavra) {
        if (palavra != null) {
            Long id = ++sequence;
            palavra.setId(id);
            palavra.setQuantPesquisa(0L);
            LIST.add(palavra);
            MAP.put(id, palavra);
        }
    }
    
    public Word readById(Long id) {
        Word palavra = MAP.get(id);
        
        if (palavra != null) {
            Long quantPesquisa = palavra.getQuantPesquisa();
            palavra.setQuantPesquisa(++quantPesquisa);
        }

        return palavra;
    }
    
    public Map<Long, String> readBySubString(String name) {
        Map<Long, String> palavras = new HashMap();
        
        LIST.stream().filter((palavra) -> 
            (palavra.getNome().toLowerCase().
             contains(name.toLowerCase()))).forEachOrdered((palavra) -> {
                palavras.put(palavra.getId(), palavra.getNome());
            });
        
        return palavras;
    }
    
    public void update(Word palavra) {
        Long id = palavra.getId();
        Word oldPalavra = MAP.get(id);
        
        if (oldPalavra != null) {
            LIST.remove(oldPalavra);
            LIST.add(palavra);
            MAP.put(id, palavra);
        }
    }
    
    public void delete(Long id) {
        Word palavra = MAP.get(id);
        
        if (palavra != null) {
            LIST.remove(palavra);
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
                Word palavra = MAP.get(id);
                
                if (palavra != null)
                {
                    String key = "nome";
                    
                    merged = new Word();
                    
                    merged.setId(palavra.getId());
                    merged.setNome(palavra.getNome());
                    merged.setDescricao(palavra.getDescricao());
                    
                    if (data.containsKey(key)) {
                        merged.setNome(data.get(key));
                    }
                    
                    key = "descricao";
                    
                    if (data.containsKey(key)) {
                        merged.setDescricao(data.get(key));
                    }
                    
                    merged.setQuantPesquisa(palavra.getQuantPesquisa());
                }
            }
        }
        
        return merged;
    }
    
    public Map<String, String> validate(Word palavra) {
        Map<String, String> erros = new HashMap<>();
        
        if (palavra != null) {
            String nome = palavra.getNome();
            
            if (nome == null) {
                erros.put("Nome", "A palavra precisa de um nome.");
            }
            
            String descricao = palavra.getDescricao();
            
            if (descricao == null) {
                erros.put("Descrição", "A palavra precisa de uma definição");
            }
        } else {
            erros.put("Entidade", "Não há palavra.");
        }
        
        return erros;
    }
    
    public void topSerch() {
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
                        if (word.getQuantPesquisa() >= 
                            wordAnalyze.getQuantPesquisa()) {
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
            mapTop.put(word.getId(), word.getNome());
        });
        
        return mapTop;
    }
}
