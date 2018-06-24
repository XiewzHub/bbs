package com.hnie.forum.web.spring.controller;

import com.hnie.forum.domain.User;
import com.hnie.forum.service.MobileTypeService;
import com.hnie.forum.service.PostsCarouselService;
import com.hnie.forum.service.PostsService;
import com.hnie.forum.service.UserService;
import com.hnie.forum.utils.ControllerUtils;
import com.hnie.forum.vo.MobilePagination;
import com.hnie.forum.vo.PostsPagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2016/10/30.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "postsService")
    private PostsService postsService;

    @Resource(name = "postsCarouselService")
    private PostsCarouselService carouselService;

    @Resource(name = "mobileTypeService")
    private MobileTypeService mobileTypeService;


    //好像没有被用到
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "common/error";
        }

        this.userService.addUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public String updateUser(Model model, HttpSession session, User user) {
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        User currentUser = (User) session.getAttribute("user");
        if (user.getId() != null) {
            Integer num = this.userService.modifyUser(user);
            if (num == 1) {
                currentUser = this.userService.findUserByUserId(user);
                session.setAttribute("user", currentUser);
            }
        }
        return "redirect:/user/manager";
    }

    /**
     * 用户登录功能，
     * 查询数据库是否有该用户，有则跳转到首页，没有则提示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(User user, HttpSession session, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "text/html");
            response.setContentType("application/json;charset=UTF-8");
            User user1 = this.userService.findUserByNamePass(user);
            session.setAttribute("user", user1);
            if (user1 == null) {
                session.setAttribute("stuts", "未登录");
                response.getWriter().write("{\"success\":0,\"message\":\"用户名或密码错误\"}");
            } else {
                session.setAttribute("stuts", "已登录");
                response.getWriter().write("{\"success\":1,\"message\":\"登录成功\",\"url\":\""+session.getServletContext().getContextPath()+"/\"}");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注销登录
     *
     * @param user
     * @param session
     * @param response
     */
    @RequestMapping(value = "/logout")
    public void logout(User user, HttpSession session, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "text/html");
            response.setContentType("application/json;charset=UTF-8");

            //修改登录状态
            session.setAttribute("stuts", "未登录");
            session.removeAttribute("user");

            response.getWriter().write("{\"success\":1,\"message\":\"成功注销，请重新登录。\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //跳转到登录注册页面
    @RequestMapping("/loginOrRegister")
    public String loginOrRegister() {

        return "user/loginOrRegister";
    }

    //跳转到注册页面
    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("info", "register");
        return "user/loginOrRegister";
    }

    /**
     * 注册信息，添加用户
     *
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, User user) {
        if (user != null && StringUtils.isNotBlank(user.getName())) {
            String name = user.getName();
            // TODO: 2017/5/11 这里需要优化，可能会有异常
            List<User> userListByName = this.userService.findUserByName(name);
            if (userListByName.isEmpty()) {
                this.userService.addUser(user);
                model.addAttribute("registerInfo", "注册成功，您可以登录了。");
            } else {
                model.addAttribute("info", "register");
                model.addAttribute("registerInfo", "该用户已经被注册");
            }
        } else {
            model.addAttribute("info", "register");
            model.addAttribute("registerInfo", "请输入信息。");
        }
        return "user/loginOrRegister";
    }


    /**
     * 进入用户个人中心
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String loginSuccess(Model model, HttpSession session) {
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        User user = (User) session.getAttribute("user");
        myPostsInit(model, new PostsPagination(), user);
        //是管理员
        if (user.getIsAdmin().equals("Y")) {
            allPostsInit(model, new PostsPagination());
            MobilePagination mobilePagination = new MobilePagination();
            mobilePagination.setPageSize(5);
            mobileTypeInit(model, mobilePagination);
        }
        model.addAttribute("info", "baseData");
        return "user/userManager";
    }

    /**
     * 进入我的帖子页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/myPostsManager")
    public String myPostsManager(Model model, HttpSession session) {
        return myPostsManager(model, session, new PostsPagination(), null);
    }

    /**
     * 点击分页执行该方法
     * 根据用户名分页查找出帖子
     *
     * @param model
     * @param postsPagination
     * @return
     */
    @RequestMapping(value = "myPostsManager/{pageNo}/{pageSize}/list")
    public String myPostsManager(Model model, HttpSession session, PostsPagination postsPagination, String title) {
        // TODO: 2017/5/13 分页查询我的帖子，还要根据作者查询
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        User user = (User) session.getAttribute("user");

        postsPagination.getPosts().setTitle(title);
        myPostsInit(model, postsPagination, user);
        //是管理员
        if (user.getIsAdmin().equals("Y")) {
            allPostsInit(model, new PostsPagination());
            MobilePagination mobilePagination = new MobilePagination();
            mobilePagination.setPageSize(5);
            mobileTypeInit(model, mobilePagination);
        }

        model.addAttribute("info", "myPosts");
        return "user/userManager";
    }

    @RequestMapping(value = "/allPostsManager")
    public String allPostsManagerDefault(Model model, PostsPagination postsPagination, HttpSession session) {
        postsPagination.setPageNo(1);
        postsPagination.setPageSize(5);
        return this.allPostsManager(model, postsPagination, session, null, null);
    }

    /**
     * 分页查询所有的帖子
     * 根据分页参数查出对应帖子
     *
     * @param model
     * @param postsPagination
     * @param session
     * @param temp
     * @return
     */
    @RequestMapping(value = "allPostsManager/{pageNo}/{pageSize}/list")
    public String allPostsManager(Model model, PostsPagination postsPagination, HttpSession session, String author, String title) {
        // TODO: 2017/5/13 分页查询我的帖子，
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        User user = (User) session.getAttribute("user");

        //初始化自己的帖子
        myPostsInit(model, new PostsPagination(), user);

        //是管理员
        if (user.getIsAdmin().equals("Y")) {
            postsPagination.getPosts().setAuthor(author);
            postsPagination.getPosts().setTitle(title);
            allPostsInit(model, postsPagination);
            MobilePagination mobilePagination = new MobilePagination();
            mobilePagination.setPageSize(5);
            mobileTypeInit(model, mobilePagination);
        }

        model.addAttribute("info", "allPosts");
        return "user/userManager";
    }

    @RequestMapping(value = "/mobileTypeManager")
    public String mobileTypeManager(Model model, HttpSession session) {
        MobilePagination mobilePagination = new MobilePagination();
        mobilePagination.setPageSize(5);
        return mobileTypeManager(model, session, mobilePagination);
    }

    @RequestMapping(value = "/mobileTypeManager/{pageNo}/{pageSize}/list")
    public String mobileTypeManager(Model model, HttpSession session, MobilePagination mobilePagination) {
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        User user = (User) session.getAttribute("user");

        //初始化自己的帖子
        myPostsInit(model, new PostsPagination(), user);

        //是管理员
        if (user.getIsAdmin().equals("Y")) {
            allPostsInit(model, new PostsPagination());
            //查找手机
            mobileTypeInit(model, mobilePagination);
        }

        model.addAttribute("info", "mobileTypeManager");
        return "user/userManager";
    }

    /**
     * 初始化手机型号
     *
     * @param model
     * @param mobilePagination
     */
    private void mobileTypeInit(Model model, MobilePagination mobilePagination) {
        mobilePagination = this.mobileTypeService.findMobileTypeByMobilePagination(mobilePagination);
        model.addAttribute("mobilePagination", mobilePagination);
    }


    private void myPostsInit(Model model, PostsPagination postsPagination, User user) {
        //根据用户名分页查找帖子
        String userName = user.getName();
        postsPagination.getPosts().setAuthor(userName);

        postsPagination = this.postsService.findPostsPagesByPostsPagination(postsPagination);

        //设置属性
        model.addAttribute("postsList", postsPagination.getPostsList());
        model.addAttribute("postsPages", postsPagination);
    }

    private void allPostsInit(Model model, PostsPagination postsPagination) {
        //根据用户名分页查找帖子

        postsPagination = this.postsService.findPostsPagesByPostsPagination(postsPagination);

        //设置属性
        model.addAttribute("allPostsList", postsPagination.getPostsList());
        model.addAttribute("postsPagesAll", postsPagination);
    }


}
