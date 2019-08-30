package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.Word;

public interface IWordDao {
    
    public static String NAME = "nome";
    public static String ID = "id";
    public static String DESCRIPTION = "descricao";
    public static String ENTITY = "palavra";

    public Boolean create(Word guest);
    
    public Word readById(Long id);

    public void update(Word word);

    public void delete(Word word);

    public void closeConnection();
}
