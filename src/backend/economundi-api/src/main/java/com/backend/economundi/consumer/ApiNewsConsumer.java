package com.backend.economundi.consumer;

import com.backend.economundi.database.dao.entity.ResultNews;
import com.backend.economundi.service.NewsService;
import java.io.IOException;

public class ApiNewsConsumer {
	
	private final String URL_BR = "https://newsapi.org/v2/top-headlines?country=br&category="
			+ "business&apiKey=e8f44036f3254db8bcd6e31c3c9ca1b0";

	public void refreshNews() throws IOException {
		ApiConsumerGeneric generic = new ApiConsumerGeneric();
		ResultNews result = generic.getData(URL_BR, ResultNews.class);
		NewsService service = new NewsService();

		result.getArticles().forEach((news) -> {
			news.setLocality("Brazil");

			service.create(news);
		});

		service.updateAllNewsWithRelevance();
	}
}
