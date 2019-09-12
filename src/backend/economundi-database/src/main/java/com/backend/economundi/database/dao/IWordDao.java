package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.Word;
import java.util.List;

public interface IWordDao {

    public Boolean create(Word guest);
    
    public Word readById(Long id);
    
    public List<Word> readByName(String name);

    public void update(Word word);

    public void delete(Word word);
}
