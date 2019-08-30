package com.backend.economundi.database.dao.entity;

import java.util.List;

public class ResultNews {
    private String status;
    private Long totalResults;
    private List<News> articles;

    @Override
    public String toString() {
        return "ResultNews{" + "status=" + status + ", totalResults=" + totalResults + ", articles=" + articles + '}';
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }
}
