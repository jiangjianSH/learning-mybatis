package com.together.mybatis;

import java.util.List;

/**
 * @author jiangjian
 */
public class Blog {
    private int id;
    private String username;
    private Author author;
    private List<Comment> comments;
    private Integer status;

    public Blog() {
    }

    public Blog(String name) {
        this.username = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", author=" + author +
                ", comments=" + comments +
                ", status=" + status +
                '}';
    }
}
