package com.together.mybatis;

/**
 * @author jiangjian
 */
public class Comment {
    private int id;
    private int blogId;
    private String content;

    public Comment(Integer id, Integer blogId, String content) {
        this.id = id;
        this.blogId = blogId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", content='" + content + '\'' +
                '}';
    }
}
