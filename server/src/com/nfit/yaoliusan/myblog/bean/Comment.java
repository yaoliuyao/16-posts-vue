package com.nfit.yaoliusan.myblog.bean;

import java.util.Date;

public class Comment {
    private Long id;
    private String content;
    private String author;
    private Date created;
    private Post post;

    public Comment() {
    }

    public Comment(String content, String author, Post post) {
        this.content = content;
        this.author = author;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
