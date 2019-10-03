package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.NewsEntity;
import java.util.List;

public interface INewsDao extends IBaseDao<NewsEntity> {
    
    public List<NewsEntity> readNewsWithRelevance();
    
    public List<NewsEntity> readByPage(Long pageBegin, Integer size, String locality);
    
    public Long getAmountNewsByLocality(String locality);
}
