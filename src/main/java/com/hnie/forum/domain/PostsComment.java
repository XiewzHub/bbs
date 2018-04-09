package com.hnie.forum.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/18.
 */
public class PostsComment implements Serializable {
    private Integer commentId;
    private Integer postsId;
    private Integer commentUserId;
    private String commentText;
    private String commentDate;

    private User user = new User();

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public Integer getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "PostsComment{" +
                "commentId=" + commentId +
                ", postsId=" + postsId +
                ", commentUserId=" + commentUserId +
                ", commentText='" + commentText + '\'' +
                ", commentDate='" + commentDate + '\'' +
                ", user=" + user +
                '}';
    }
}
