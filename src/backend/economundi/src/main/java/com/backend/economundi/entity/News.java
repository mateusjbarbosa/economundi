package com.backend.economundi.entity;

public class News {

    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String content;
    private String locality;
    private Long relevance;

    @Override
    public String toString() {
        return "News{" + "source=" + source + ", author=" + author + ", title="
                + title + ", description=" + description + ", url=" + url + 
                ", urlToImage=" + urlToImage + ", content=" + content + 
                ", locality=" + locality + ", relevance=" + relevance + '}';
    }

    
    
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
//    
    
}
