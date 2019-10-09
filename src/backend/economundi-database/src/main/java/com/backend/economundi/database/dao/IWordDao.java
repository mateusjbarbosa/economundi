package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.WordEntity;
import java.util.List;

public interface IWordDao extends IBaseDao<WordEntity> {
    public List<WordEntity> readByName(String name);
}
