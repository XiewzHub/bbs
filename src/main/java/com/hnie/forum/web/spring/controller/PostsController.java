package com.hnie.forum.web.spring.controller;

import com.hnie.forum.domain.MobileType;
import com.hnie.forum.domain.Posts;
import com.hnie.forum.domain.User;
import com.hnie.forum.service.MobileTypeService;
import com.hnie.forum.service.PostsCarouselService;
import com.hnie.forum.service.PostsCommentService;
import com.hnie.forum.service.PostsService;
import com.hnie.forum.utils.ControllerUtils;
import com.hnie.forum.utils.FileUtils;
import com.hnie.forum.utils.PostsUtils;
import com.hnie.forum.vo.PostsCommentPagination;
import com.hnie.forum.vo.PostsPagination;
// import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/4/24.
 */

/**
 * 帖子管理类
 */
@Controller
@RequestMapping("/posts")
public class PostsController {

    @Resource(name = "postsService")
    private PostsService postsService;

    @Resource(name = "postsCarouselService")
    private PostsCarouselService carouselService;

    @Resource(name = "mobileTypeService")
    private MobileTypeService mobileTypeService;

    @Resource(name = "postsCommentService")
    private PostsCommentService postsCommentService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpSession session, Model model) {
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";

        List<String> mobileBrandList = this.mobileTypeService.findMobileBrandList();
        model.addAttribute("mobileBrandList", mobileBrandList);
        return "posts/posts_add";
    }


    /**
     * 帖子发布方法，将内容存放到数据库，并将图片转移到对应的文件夹
     *
     * @param request
     * @param posts
     * @param bindingResult
     * @param mobileType    在添加页面选择好手机型号，封装在这个对象里使用
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpSession session, HttpServletRequest request, Posts posts, BindingResult bindingResult, MobileType mobileType) throws IOException {
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        User user = (User) session.getAttribute("user");
        if (bindingResult.hasErrors()) {
            return "common/error";
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentData = sdf.format(date);
        posts.setDate(currentData);
        posts.setAuthor(user.getName());
        posts.setMobileId(mobileType.getMobiId());
        increasePostsNum(session, user);
//        插入一个帖子并查询返回受影响的行数
        Integer num = postsService.addPost(posts,request);

        //获取图片上传的原路径
        String srcPath = request.getSession().getServletContext().getRealPath("/posts/resources/");
        if (new File(srcPath).exists()) {
//        创建图片上传的新路径
            String destPath = request.getSession().getServletContext().getRealPath("/posts/") + "\\" + posts.getId() + "\\resources";
            FileUtils.moveFile(srcPath, destPath);
            System.out.println(FileUtils.deleteDir(new File(srcPath)));//删除文件夹
        }

        // TODO: 2017/5/14 这里跳转是重定向，一般跳转到置顶板块的帖子列表
        return "redirect:/user/myPostsManager";
    }



    /**
     * 查询出所有的帖子并展示
     *
     * @param model
     * @param mobileType 可以根据手机型号查询帖子
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model, MobileType mobileType) {
        if (mobileType.getMobiId() == null) {
            model.addAttribute("message", "all");
        }
        PostsPagination postsPagination = new PostsPagination();
        postsPagination.setPageSize(10);
        return this.list(model, postsPagination, mobileType);

    }
    @RequestMapping(value = "/search")
    public String search(Model model, MobileType mobileType,String search) {
        search = search.trim();
        model.addAttribute("search",search);
        if (mobileType.getMobiId() == null) {
            model.addAttribute("message", "all");
        }

        PostsPagination postsPagination = new PostsPagination();
        postsPagination.setPageSize(10);
        postsPagination.getPosts().setTitle(search);
        postsPagination.getPosts().setText(search);
        return this.list(model, postsPagination, mobileType);
    }
    @RequestMapping(value = "{pageNo}/{pageSize}/search")
    public String search(Model model,MobileType mobileType,PostsPagination postsPagination,String search) {
        search = search.trim();
        model.addAttribute("search",search);
        if (mobileType.getMobiId() == null) {
            model.addAttribute("message", "all");
        }

        postsPagination.getPosts().setTitle(search);
        postsPagination.getPosts().setText(search);
        return this.list(model, postsPagination, mobileType);
    }

    @RequestMapping(value = "{pageNo}/{pageSize}/list")
    public String list(Model model, PostsPagination postsPagination, MobileType mobileType) {
        postsPagination.getPosts().setMobileId(mobileType.getMobiId());
        try {
            this.postsService.findPostsPagesByPostsPagination(postsPagination);
        } catch (Exception e) {
            return "common/error";
        }
        if (mobileType.getMobiId() != null) {
            mobileType = this.mobileTypeService.findMobileTypeBySelf(mobileType).get(0);
        } else {
            model.addAttribute("message", "all");

        }
        model.addAttribute("mobileType", mobileType);
        model.addAttribute("postsList", postsPagination.getPostsList());
        model.addAttribute("postsPages", postsPagination);
        return "posts/posts_list";
    }
    //    编辑帖子

    /**
     * 编辑帖子，按照编号查询出所要编辑的帖子并展示到编辑页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}/edit")
    public String edit(HttpSession session, @PathVariable(value = "id") Integer id, Model model) {
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        Posts posts = postsService.findPostsById(id);
        MobileType mobileType = new MobileType();
        //从帖子中得到手机id并且赋值
        mobileType.setMobiId(posts.getMobileId());
        //根据手机id查找手机型号

        List<String> mobileBrandList = this.mobileTypeService.findMobileBrandList();
        //手机型号设置进去
        model.addAttribute("mobileBrandList", mobileBrandList);
        model.addAttribute("mobileType", posts.getMobileType());
        model.addAttribute("posts", PostsUtils.getPostsText(posts,session));
        return "posts/posts_edit";
    }


    /**
     * 确认修改，在编辑界面点击确认修改会执行这个方法
     *
     * @return
     */
    @RequestMapping(value = "/modify")
    public String modify(HttpSession session, Posts posts, BindingResult bindingResult) {
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        User user = (User) session.getAttribute("user");
        posts.setAuthor(user.getName());
        if (bindingResult.hasErrors()) {
            //跳转到错误页面
            return "common/error";
        }

        this.postsService.modifyPosts(posts,session);
        return "redirect:/user/myPostsManager";
    }

    /**
     * 设置和取消帖子置顶
     * @param model
     * @param session
     * @param postsId
     * @return
     */
    @RequestMapping(value = "{postsId}/ajaxSetTop")
    @ResponseBody
    public Map setTop(Model model, HttpSession session, @PathVariable Integer postsId) {
        Map result = new HashMap();
        //查询帖子置顶情况
        Posts posts = this.postsService.findPostsById(postsId);
        //如果该帖没有置顶
        if(posts.getSetTop()==0){
            Integer num = this.postsService.setPostsTop(postsId);
            if (num == 1) {
                result.put("result", 1);
                result.put("message", "设置成功，该帖已被置顶");
            } else {
                result.put("result", 0);
                result.put("message", "后台错误，请联系管理员...");
            }
        }else {//如果已经置顶 就取消置顶
            if(this.postsService.cancelPostsTop(postsId)==1){
                result.put("result", 2);
                result.put("message", "该帖已被取消置顶");
            }else {
                result.put("result", 0);
                result.put("message", "后台错误，请联系管理员...");
            }
        }
        return result;
    }

    /**
     * 按照id查询所要查看的帖子，并展示到查看页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}/view")
    public String view(@PathVariable(value = "id") Integer id, Model model,HttpSession session) {
        String rootPath = session.getServletContext().getRealPath("");
        Posts posts = postsService.findPostsById(id);
        increaseViewNum(posts);
        PostsCommentPagination commentPagination = new PostsCommentPagination(1,10);
        commentPagination.getPostsComment().setPostsId(id);
        this.postsCommentService.findPostsCommentPaginationByCommentPagination(commentPagination);
        StringBuffer paginationHtml = this.postsCommentService.getPaginationHtml(commentPagination);

        model.addAttribute("paginationHtml",paginationHtml);
        model.addAttribute("commentPagination",commentPagination);
        model.addAttribute("posts", PostsUtils.getPostsText(posts,rootPath));
        return "posts/posts_view";
    }
    @RequestMapping(value = "{id}/delete")
    public String delete(@PathVariable(value = "id") Integer id, Model model,HttpSession session) {
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        this.postsService.deletePostsById(id);
        User user = (User) session.getAttribute("user");
        downPostsNum(session, user);
        return "redirect:/user/myPostsManager";
    }


    @RequestMapping(value = "{id}/ajaxDelete")
    public void ajaxDeletePostsById(@PathVariable Integer id, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        Integer num = 0;
        if (!ControllerUtils.isNoUser(session)) {
            // TODO: 2017/5/14 删除帖子，在删除之前必须判断是否有轮播，有的话也要删除
            this.carouselService.deleteCarouselByPostsId(id);
            num = this.postsService.deletePostsById(id);
        }
        try {
            request.getAttribute("info");
            response.setHeader("Content-Type", "text/html");
            response.setContentType("application/json;charset=UTF-8");//防止json串的数据传递乱码

            if (num >= 1) {
                User user = (User) session.getAttribute("user");
                downPostsNum(session, user);
                response.getWriter().write("{\"result\": 1}");
            } else {
                response.getWriter().write("{\"result\": 0}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: 2017/5/14 使用ajax提交时，不会跳转页面，只能通过return返回json串(此时在方法的返回类型上加@ResponseBody)，否则会会回调error方法。
//        return "";
    }

    /**
     * 观看数加一
     *
     * @param posts
     */
    private void increaseViewNum(Posts posts) {
        Integer viewNum = posts.getViewNum();
        posts.setViewNum(viewNum + 1);
        postsService.modifyPosts(posts);
    }

    /**
     * 评论数数加一
     *
     * @param posts
     */
    private void increaseCommentNum(Posts posts) {
        Integer commentNum = posts.getCommentNum();
        posts.setCommentNum(commentNum + 1);
        postsService.modifyPosts(posts);
    }

    /**
     * 帖子数加一
     * @param session
     * @param user
     */
    private void increasePostsNum(HttpSession session, User user) {
        user.setPostsNum(user.getPostsNum()+1);
        session.setAttribute("user",user);
    }
    /**
     * 帖子数减一
     * @param session
     * @param user
     */
    private void downPostsNum(HttpSession session, User user) {
        user.setPostsNum(user.getPostsNum()-1);
        session.setAttribute("user",user);
    }

}
