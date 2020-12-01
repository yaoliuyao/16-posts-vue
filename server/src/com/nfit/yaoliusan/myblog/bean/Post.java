package com.nfit.yaoliusan.myblog.bean;

import java.util.Date;

public class Post {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String cover;
    private Date created;
    private int likes;

    public Post() {
    }

    public Post(String title, String content, String author, String cover) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.cover = cover;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
