package com.backend.economundi.service;

import com.backend.economundi.entity.Palavra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalavraService {
    private static Long sequence = 0L;
    private static List<Palavra> list = new ArrayList<>();
    private static Map<Long, Palavra> map = new HashMap<>();
    
    public void create (Palavra palavra) {
        if (palavra != null) {
            Long id = ++sequence;
            palavra.setId(id);
            palavra.setQuantPesquisa(0L);
            list.add(palavra);
            map.put(id, palavra);
        }
    }
    
    public Palavra readById(Long id) {
        Palavra palavra = map.get(id);
        
        if (palavra != null) {
            Long quantPesquisa = palavra.getQuantPesquisa();
            palavra.setQuantPesquisa(++quantPesquisa);
        }

        return palavra;
    }
    
    public void update(Palavra palavra) {
        Long id = palavra.getId();
        Palavra oldPalavra = map.get(id);
        
        if (oldPalavra != null) {
            list.remove(oldPalavra);
            list.add(palavra);
            map.put(id, palavra);
        }
    }
    
    public void delete(Long id) {
        Palavra palavra = map.get(id);
        
        if (palavra != null) {
            list.remove(palavra);
            map.remove(id);
        }
    }
    
    public Palavra merge(Map<String, String> data) {
        Palavra merged = null;
        
        if (data != null)
        {
            Long id = Long.parseLong(data.get("id"));
            
            if (id != 0)
            {
                Palavra palavra = map.get(id);
                
                if (palavra != null)
                {
                    String key = "nome";
                    
                    merged = new Palavra();
                    
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
    
    public Map<String, String> validate(Palavra palavra) {
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
