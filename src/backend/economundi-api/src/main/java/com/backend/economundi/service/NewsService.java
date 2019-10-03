package com.backend.economundi.service;

import com.backend.economundi.database.dao.INewsDao;
import com.backend.economundi.database.dao.entity.News;
import com.backend.economundi.database.dao.entity.NewsBlackList;
import com.backend.economundi.database.dao.entity.NewsDto;
import com.backend.economundi.database.dao.impl.NewsDao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    private final String[] REMORE_IN_CONTENT = {"\n", "\t", "\r"};
    private final Integer LIMIT = 6;
    private final INewsDao newsDao = new NewsDao();

    /**
     * Cria um novo artigo no sistema.
     *
     * @param news Artigo a ser criado.
     */
    public void create(News news) {
        if (news != null && validate(news)) {
            news.setRelevance(100L);
            newsDao.create(news);
        }
    }

    /**
     * Realiza a leitura de uma notícia.
     *
     * @param id Identificador da notícia.
     * @return Notícia pesquisada.
     */
    public News readById(Long id) {
        News news;
        news = newsDao.read(id);

        return news;
    }

    /**
     * Coleta as notícias seis por páginas, começando do 0.
     *
     * @param page Página das notícias;
     * @param locality Localidade da notícia;x
     * @return Lista de notícias e quantidade de páginas total.
     */
    public Map<String, Object> readByPage(Long page, String locality) {
        List<NewsDto> newsDtoList = new ArrayList<>();
        Long pageBegin = page * LIMIT;
        List<News> newsList = newsDao.readByPage(pageBegin, LIMIT, locality);
        Long amount = (newsDao.getAmountNewsByLocality(locality) / LIMIT);
        Map<String, Object> newsMap = new HashMap<>();

        newsList.stream().forEach((news) -> {
            NewsDto newsDto = new NewsDto();
            newsDto.setTitle(news.getTitle());
            newsDto.setDescription(news.getDescription());
            newsDto.setUrl(news.getUrl());
            newsDto.setUrlToImage(news.getUrlToImage());
            newsDto.setSource(news.getSource().getName());
            newsDto.setAmountLike(10);
            newsDto.setLiked(true);
            
            newsDtoList.add(newsDto);
        });        
        
        newsMap.put("data", newsDtoList);
        newsMap.put("amountPage", amount.intValue());

        return newsMap;
    }

    /**
     * Realiza o update unitário de cada notícia.
     *
     * @param news Notícia a ser atualizada.
     */
    public void update(News news) {
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
     * Valida a nova notícia e informa se deve ou não persistir.
     *
     * @param news Notícia a ser validada.
     * @return Booleana indicando se passou pela black list.
     */
    private Boolean validate(News news) {
        NewsBlackListService serviceNewsBL = new NewsBlackListService();
        List<NewsBlackList> newsBLList = serviceNewsBL.readAll();
        Boolean create = true;

        if (news != null && news.getDescription() != null
                && news.getDescription().length() > 0
                && news.getContent() != null && news.getContent().length() > 0
                && news.getTitle() != null && news.getTitle().length() > 0
                && news.getUrlToImage() != null
                && news.getUrlToImage().length() > 0) {
            news.setTitle(news.getTitle().split(" -")[0]);
            news.getSource().setName(news.getSource().getName().split(".com")[0]);

            for (NewsBlackList newsBL : newsBLList) {
                if (news.getTitle().toUpperCase().contains(newsBL.getName())) {
                    create = false;
                    break;
                }
            }

            if (create) {
                String content = news.getContent();

                for (String remove : REMORE_IN_CONTENT) {
                    content = content.replaceAll(remove, " ");
                }

                news.setContent(content);
            }
        } else {
            create = false;
        }

        return create;
    }

    /**
     * Converte milissegundos em horas.
     *
     * @param milliseconds Milissegundos a ser convertido.
     * @return Horas correspondente.
     */
    private Integer millisecondToHours(Long milliseconds) {
        Integer seconds = milliseconds.intValue() / 1000;
        Integer hours = seconds / 3600;

        return hours;
    }
}
