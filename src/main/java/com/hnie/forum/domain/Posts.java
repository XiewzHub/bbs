package com.hnie.forum.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/24.
 */
public class Posts implements Serializable {
    private Integer id;
    private String title;
    /**
     * MarkDown文本
     */
    private String srcText;
    /**
     * 转变成html的文本
     */
    private String text;
    private String author;
    private String date;

    private Integer commentNum;
    private Integer viewNum;
    /**
     * 1表示置顶
     * 0表示不置顶
     */
    private Integer setTop;

    private Integer mobileId;

//    实现一对一查询对应的手机型号
    private MobileType mobileType;

    private PostsCarousel postsCarousel;

    private User user;

    public MobileType getMobileType() {
        return mobileType;
    }

    public void setMobileType(MobileType mobileType) {
        this.mobileType = mobileType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getSetTop() {
        return setTop;
    }

    public void setSetTop(Integer setTop) {
        this.setTop = setTop;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSrcText() {
        return srcText;
    }

    public void setSrcText(String srcText) {
        this.srcText = srcText;
    }

    public Integer getMobileId() {
        return mobileId;
    }

    public void setMobileId(Integer mobileId) {
        this.mobileId = mobileId;
    }

    public PostsCarousel getPostsCarousel() {
        return postsCarousel;
    }

    public void setPostsCarousel(PostsCarousel postsCarousel) {
        this.postsCarousel = postsCarousel;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", srcText='" + srcText + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", commentNum=" + commentNum +
                ", viewNum=" + viewNum +
                ", setTop=" + setTop +
                '}';
    }
}
