package com.hnie.forum.service.impl;

import com.hnie.forum.domain.PostsCarousel;
import com.hnie.forum.mapper.PostsCarouselMapper;
import com.hnie.forum.service.PostsCarouselService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
@Service("postsCarouselService")
@Transactional
public class PostsCarouselServiceImpl implements PostsCarouselService {
    @Resource(name = "postsCarouselMapper")
    private PostsCarouselMapper postsCarouselMapper;


    @Override
    public List<PostsCarousel> findAllPostsCarousel() {
        List<PostsCarousel> postsCarouselList = null;
        return postsCarouselMapper.findAllPostsCarousel();
    }

    @Override
    public Integer deleteCarouselByPostsId(Integer postsId) {
        Integer num = this.postsCarouselMapper.deleteCarouselByPostsId(postsId);
        System.out.println(num);
        return num;
    }

    @Override
    public Integer addPostsCarousel(PostsCarousel carousel) {
        return this.postsCarouselMapper.addCarousel(carousel);
    }
}
