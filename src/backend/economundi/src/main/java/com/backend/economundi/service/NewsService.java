package com.backend.economundi.service;

import com.backend.economundi.database.dao.impl.NewsDao;
import com.backend.economundi.entity.News;

public class NewsService {
    
    /**
     * Cria um novo artigo no sistema.
     * @param news Artigo a ser criado.
     */
    public void create (News news) {
        if (news != null) {
            NewsDao newsDao = new NewsDao();
            
            newsDao.create(news);
            newsDao.closeConnection();
        }
    }
}
