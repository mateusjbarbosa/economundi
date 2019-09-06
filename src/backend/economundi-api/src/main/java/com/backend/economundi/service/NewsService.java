package com.backend.economundi.service;

import com.backend.economundi.database.dao.entity.News;
import com.backend.economundi.database.dao.impl.NewsDao;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    
    /**
     * Cria um novo artigo no sistema.
     *
     * @param news Artigo a ser criado.
     */
    public void create(News news) {
        NewsDao newsDao = new NewsDao();
        
        if (news != null) {
            newsDao.create(news);
        }
    }

    /**
     * Realiza o update unitário de cada notícia.
     *
     * @param news Notícia a ser atualizada.
     */
    public void update(News news) {
        NewsDao newsDao = new NewsDao();
        
        if (news != null) {
            if (news.getId() != null) {
                newsDao.update(news);
            }
        }
    }

    /**
     * Realiza a atualização apenas das notícias com relevância.
     */
    public void updateAllNewsWithRelevance() {
        NewsDao newsDao = new NewsDao();
        Date now = new Date();
        Timestamp tsNow = new Timestamp(now.getTime());
        List<News> newsList = newsDao.readNewsWithRelevance();
        
        newsList.forEach((_item) -> {
            Timestamp tsNews = Timestamp.valueOf(_item.getDate());
            Long diff = tsNow.getTime() - tsNews.getTime();
            Integer hours = millisecondToHours(diff);
            Long newRelevance = _item.getRelevance() - (2 ^ hours);
            
            if (newRelevance < 0) {
                newRelevance = 0L;
            }
            
            _item.setRelevance(newRelevance);
            update(_item);
        });
    }

    /**
     * Converte milissegundos em horas.
     * @param milliseconds Milissegundos a ser convertido.
     * @return Horas correspondente.
     */
    private Integer millisecondToHours(Long milliseconds) {
        Integer seconds = milliseconds.intValue() / 1000;
        Integer hours = seconds / 3600;

        return hours;
    }
}
