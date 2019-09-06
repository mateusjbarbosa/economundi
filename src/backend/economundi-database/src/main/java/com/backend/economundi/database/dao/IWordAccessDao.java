package com.backend.economundi.database.dao;

import java.util.Map;



public interface IWordAccessDao {
    
    public static String WORD_ID = "palavra_id";
    public static String ENTITY = "palavra_acesso";
    
    public void create (Long id);
    
    public Map<Long, String> getMostSearched ();
}
