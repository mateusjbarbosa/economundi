package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.News;
import java.util.List;

public interface INewsDao extends IBaseDao<News> {
    
    public List<News> readNewsWithRelevance();
    
    public List<News> readByPage(Long pageBegin, Integer size, String locality);
}
