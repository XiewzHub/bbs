package com.hnie.forum.web.spring.controller;

import com.hnie.forum.domain.PostsCarousel;
import com.hnie.forum.service.PostsCarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/27.
 */
@Controller
@RequestMapping("/carousel")
public class CarouselController {

    @Resource(name = "postsCarouselService")
    private PostsCarouselService carouselService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(PostsCarousel carousel, HttpServletResponse response, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            /**
             * 针对json字符串的乱码解决方案
             */
            response.setContentType("application/json;charset=UTF-8");//防止json串的数据传递乱码
//            System.out.println(carousel);
            Integer id = this.carouselService.addPostsCarousel(carousel);

            response.getWriter().write("{\"success\": 1, \"message\":\"上传成功,您现在可以从主页上查看\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除，需要优化
     * @param carousel
     * @param response
     */
    @RequestMapping("/delete")
    public void delete(PostsCarousel carousel, HttpServletResponse response) {
        try {
            response.setContentType("application/json;charset=UTF-8");//防止json串的数据传递乱码
            System.out.println(carousel);
            Integer num = this.carouselService.deleteCarouselByPostsId(carousel.getPostsId());
            if (num >= 1) {
                response.getWriter().write("{\"success\": 1, \"message\":\"删除成功,您现在可以从主页上查看\"}");
            } else {
                response.getWriter().write("{\"success\": 0, \"message\":\"删除失败,请确定主页上是否有该轮播\"}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
