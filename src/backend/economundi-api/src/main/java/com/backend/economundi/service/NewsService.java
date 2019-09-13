package com.backend.economundi.service;

import com.backend.economundi.database.dao.entity.News;
import com.backend.economundi.database.dao.impl.NewsDao;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    
    private final Integer LIMIT = 6;
    
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
    
    public News readById(Long id) {
    	News news = null;
    	NewsDao newsDao = new NewsDao();
    	
    	news = newsDao.read(id);
    	
    	return news;
    }
    
    /**
     * Coleta as notícias seis por páginas, começando do 0.
     * @param page Página das notícias;
     * @param locality Localidade da notícia;x
     * @return Lista de notícias.
     */
    public Map<Long, Map<String, String>> readByPage(Long page, String locality) {
        NewsDao newsDao = new NewsDao();
        Long pageBegin = page * LIMIT;
        List<News> newsList = newsDao.readByPage(pageBegin, LIMIT, locality);
        Map<Long, Map<String, String>> newsMap = new HashMap<>();
        
        newsList.stream().forEach((news) -> {
            Map<String, String> newsInfoMap = new HashMap<>();
            
            newsInfoMap.put("title", news.getTitle());
            newsInfoMap.put("description", news.getDescription());
            newsInfoMap.put("url", news.getUrl());
            newsInfoMap.put("titleToImage", news.getUrlToImage());
            newsInfoMap.put("title", news.getSource().getName());
            
            newsMap.put(news.getId(), newsInfoMap);
        });
        
        return newsMap;
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
