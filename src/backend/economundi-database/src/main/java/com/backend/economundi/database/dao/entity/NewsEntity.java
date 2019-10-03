package com.backend.economundi.database.dao.entity;

public class NewsEntity {

    private Long id;
    private SourceEntity source;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String content;
    private String locality;
    private String date;
    private Long relevance;

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", source=" + source + ", title=" + 
                title + ", description=" + description +
                ", url=" + url + ", urlToImage=" + urlToImage + ", content=" +
                content + ", locality=" + locality + ", date=" + date +
                ", relevance=" + relevance + '}';
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public SourceEntity getSource() {
        return source;
    }

    public void setSource(SourceEntity source) {
        this.source = source;
    }

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

    public void setUrlToImage(String urtToImage) {
        this.urlToImage = urtToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Long getRelevance() {
        return relevance;
    }

    public void setRelevance(Long relevance) {
        this.relevance = relevance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
