package com.backend.economundi.entity;

public class News {
   private Source source;
   private String author;
   private String title;
   private String description;
   private String url;
   private String urtToImage;
   private String content;

    @Override
    public String toString() {
        return "News{" + "source=" + source + ", author=" + author + ", title=" + title + ", description=" + description + ", url=" + url + ", urtToImage=" + urtToImage + ", content=" + content + '}';
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

    public String getUrtToImage() {
        return urtToImage;
    }

    public void setUrtToImage(String urtToImage) {
        this.urtToImage = urtToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
