package com.hnie.forum.service;

import com.hnie.forum.domain.PostsCarousel;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
public interface PostsCarouselService {
    public List<PostsCarousel> findAllPostsCarousel();

    public Integer deleteCarouselByPostsId(Integer postsId);

    public Integer addPostsCarousel(PostsCarousel carousel);
}
