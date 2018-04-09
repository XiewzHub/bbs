package com.hnie.forum.mapper;

import com.hnie.forum.domain.PostsCarousel;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
public interface PostsCarouselMapper {
    public List<PostsCarousel> findAllPostsCarousel();

    public Integer deleteCarouselByPostsId(Integer postsId);

    public Integer addCarousel(PostsCarousel postsCarousel);
}
