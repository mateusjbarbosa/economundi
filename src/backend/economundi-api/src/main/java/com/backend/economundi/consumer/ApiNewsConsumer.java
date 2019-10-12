package com.backend.economundi.consumer;

import com.backend.economundi.database.dao.entity.ResultNewsEntity;
import com.backend.economundi.service.NewsService;
import java.io.IOException;

public class ApiNewsConsumer {

    private final String URL_BR = "https://newsapi.org/v2/top-headlines?country=br&category="
            + "business&apiKey=e8f44036f3254db8bcd6e31c3c9ca1b0";
    private final String URL_WORLD = "https://newsapi.org/v2/top-headlines?category="
            + "business&country=us&apiKey=e8f44036f3254db8bcd6e31c3c9ca1b0";

    
    public void refreshNews() throws IOException {
        ApiConsumerGeneric generic = new ApiConsumerGeneric();
        NewsService service = new NewsService();
        ResultNewsEntity resultBr = generic.getData(URL_BR, ResultNewsEntity.class);
        ResultNewsEntity resultWorld = generic.getData(URL_WORLD, ResultNewsEntity.class);

        createNews("Brazil", resultBr);
        createNews("World", resultWorld);

        service.updateAllNewsWithRelevance();
    }

    private void createNews(String locality, ResultNewsEntity resultNews) {
        NewsService service = new NewsService();
        
        resultNews.getArticles().forEach((news) -> {
            news.setLocality(locality);

            service.create(news);
        });
    }
}
