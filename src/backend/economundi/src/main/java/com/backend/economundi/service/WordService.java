package com.backend.economundi.service;

import com.backend.economundi.entity.Word;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordService {
    private static Long sequence = 0L;
    private static List<Word> list = new ArrayList<>();
    private static Map<Long, Word> map = new HashMap<>();
    
    public void create (Word palavra) {
        if (palavra != null) {
            Long id = ++sequence;
            palavra.setId(id);
            palavra.setQuantPesquisa(0L);
            list.add(palavra);
            map.put(id, palavra);
        }
    }
    
    public Word readById(Long id) {
        Word palavra = map.get(id);
        
        if (palavra != null) {
            Long quantPesquisa = palavra.getQuantPesquisa();
            palavra.setQuantPesquisa(++quantPesquisa);
        }

        return palavra;
    }
    
    public Map<Long, String> readBySubString(String name) {
        Map<Long, String> palavras = new HashMap();
        
        list.stream().filter((palavra) -> 
            (palavra.getNome().toLowerCase().
             contains(name.toLowerCase()))).forEachOrdered((palavra) -> {
                palavras.put(palavra.getId(), palavra.getNome());
            });
        
        return palavras;
    }
    
    public void update(Word palavra) {
        Long id = palavra.getId();
        Word oldPalavra = map.get(id);
        
        if (oldPalavra != null) {
            list.remove(oldPalavra);
            list.add(palavra);
            map.put(id, palavra);
        }
    }
    
    public void delete(Long id) {
        Word palavra = map.get(id);
        
        if (palavra != null) {
            list.remove(palavra);
            map.remove(id);
        }
    }
    
    public Word merge(Map<String, String> data) {
        Word merged = null;
        
        if (data != null)
        {
            Long id = Long.parseLong(data.get("id"));
            
            if (id != 0)
            {
                Word palavra = map.get(id);
                
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
}
