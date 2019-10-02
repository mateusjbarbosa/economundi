package com.backend.economundi.database.dao.entity;

public class NewsDto {

    String title;
    String description;
    String url;
    String urlToImage;
    String source;
    Integer amountLike;
    Boolean liked;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String titleToImage) {
        this.urlToImage = titleToImage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getAmountLike() {
        return amountLike;
    }

    public void setAmountLike(Integer amountLike) {
        this.amountLike = amountLike;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
