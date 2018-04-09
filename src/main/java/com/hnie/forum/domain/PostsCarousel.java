package com.hnie.forum.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/26.
 */
public class PostsCarousel implements Serializable {
    private Integer carouselId;
    private Integer postsId;
    private String imgAddress;
    private String postsTitle;
    private String postsUrl;

    public Integer getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getPostsTitle() {
        return postsTitle;
    }

    public void setPostsTitle(String postsTitle) {
        this.postsTitle = postsTitle;
    }

    public String getPostsUrl() {
        return postsUrl;
    }

    public void setPostsUrl(String postsUrl) {
        this.postsUrl = postsUrl;
    }

    @Override
    public String toString() {
        return "PostsCarousel{" +
                "carouselId=" + carouselId +
                ", postsId=" + postsId +
                ", imgAddress='" + imgAddress + '\'' +
                ", postsTitle='" + postsTitle + '\'' +
                ", postsUrl='" + postsUrl + '\'' +
                '}';
    }
}
