package com.hnie.forum.web.spring.controller;

import com.hnie.forum.domain.MobileType;
import com.hnie.forum.domain.Posts;
import com.hnie.forum.domain.PostsCarousel;
import com.hnie.forum.domain.User;
import com.hnie.forum.service.MobileTypeService;
import com.hnie.forum.service.PostsCarouselService;
import com.hnie.forum.service.PostsService;
import com.hnie.forum.service.UserService;
import com.hnie.forum.vo.Pagination;
import com.hnie.forum.vo.PostsPagination;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Resource(name = "postsCarouselService")
    private PostsCarouselService carouselService;

    @Resource(name = "postsService")
    private PostsService postsService;

    @Resource(name = "mobileTypeService")
    private MobileTypeService mobileTypeService;


    //跳到主页

    /**
     * 系统入口，主页跳转
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String home(Model model) {
//        List<PostsCarousel> postsCarouselList = new ArrayList<PostsCarousel>();
        PostsPagination postsPagination = new PostsPagination();
        postsPagination.setPageSize(5);
        init(model, postsPagination);

        return "index";
    }

    private void init(Model model, PostsPagination postsPagination) {
        // 查轮播列表
        List<PostsCarousel> postsCarouselList = carouselService.findAllPostsCarousel();
//        反转list
        Collections.reverse(postsCarouselList);
//        查帖子，按照浏览量
        this.postsService.findPostsByPages(postsPagination);

        model.addAttribute("carouselList", postsCarouselList);
        model.addAttribute("postsList", postsPagination.getPostsList());
        model.addAttribute("postsPages", postsPagination);
    }

    @RequestMapping(value = "homePosts/{pageNo}/{pageSize}/list")
    public String homePosts(Model model, PostsPagination postsPagination) {
        init(model, postsPagination);
        return "index";
    }


   /* @RequestMapping(value = "/test")
    public String test(Model model) {
        List<MobileType> mobileTypeList = this.mobileTypeService.findAllMobileTypes();
        model.addAttribute("mobileTypeList", mobileTypeList);
        return "test";
    }

    @RequestMapping(value = "/search")
    public String searchTest(Model model, MobileType mobileType) {
        *//*if (mobileType.getMobiBrand() == "") {
            mobileType.setMobiBrand(null);
        }
        if (mobileType.getMobiSeries() == "") {
            mobileType.setMobiSeries(null);
        }
        if (mobileType.getMobiType() == "") {
            mobileType.setMobiType(null);
        }*//*
        List<MobileType> mobileTypeList = this.mobileTypeService.findMobileTypeBySelf(mobileType);
        model.addAttribute("mobileTypeList", mobileTypeList);
        return "test";
    }*/


}
