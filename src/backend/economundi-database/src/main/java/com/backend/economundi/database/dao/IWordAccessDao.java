package com.backend.economundi.database.dao;

public interface IWordAccessDao {
    
    public static String WORD_ID = "palavra_id";
    public static String ENTITY = "palavra_acesso";
    
    public void create (Long id);
}
