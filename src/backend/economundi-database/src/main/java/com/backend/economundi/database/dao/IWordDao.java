package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.Word;
import java.util.List;

public interface IWordDao extends IBaseDao<Word> {
    public List<Word> readByName(String name);
}
