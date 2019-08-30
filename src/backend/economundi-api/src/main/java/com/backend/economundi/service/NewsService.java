package com.backend.economundi.service;

import com.backend.economundi.database.dao.impl.NewsDao;
import com.backend.economundi.database.dao.entity.News;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class NewsService {

    /**
     * Cria um novo artigo no sistema.
     *
     * @param news Artigo a ser criado.
     */
    public void create(News news) {
        if (news != null) {
            NewsDao newsDao = new NewsDao();

            newsDao.create(news);
            newsDao.closeConnection();
        }
    }

    /**
     * Realiza o update unitário de cada notícia.
     *
     * @param news Notícia a ser atualizada.
     */
    public void update(News news) {
        if (news != null) {
            if (news.getId() != null) {
                NewsDao newsDao = new NewsDao();

                newsDao.update(news);
                newsDao.closeConnection();
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

        newsDao.closeConnection();
        
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
