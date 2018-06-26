package com.hnie.forum.web.spring.controller;

import com.hnie.forum.domain.Posts;
import com.hnie.forum.domain.PostsComment;
import com.hnie.forum.domain.User;
import com.hnie.forum.service.PostsCommentService;
import com.hnie.forum.service.PostsService;
import com.hnie.forum.utils.ControllerUtils;
import com.hnie.forum.vo.PostsCommentPagination;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/18.
 */
@Controller
@RequestMapping("/postsComment")
public class PostsCommentController {

    @Resource(name = "postsCommentService")
    private PostsCommentService postsCommentService;

    @Resource(name = "postsService")
    private PostsService postsService;

    @RequestMapping("/addComment")
    public String addPostsComment(HttpSession session, PostsComment postsComment) {
        if (ControllerUtils.isNoUser(session)) return "user/loginOrRegister";
        User user = (User) session.getAttribute("user");
        //设置用户ID
        postsComment.setCommentUserId(user.getId());
        //设置评论时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        postsComment.setCommentDate(currentDate);
        this.postsCommentService.addPostsComment(postsComment);
        Integer postsId = postsComment.getPostsId();
        //评论数加1
        Posts posts = this.postsService.findPostsById(postsId);
        posts.setCommentNum(posts.getCommentNum()+1);
        this.postsService.modifyPosts(posts);
        return "redirect:/posts/" + postsId + "/view";
    }

    @RequestMapping(value = "{postsId}/ajaxFindComment", method = RequestMethod.POST)
    @ResponseBody
    public Map findCommentByPostsId(@RequestBody PostsCommentPagination commentPagination, @PathVariable Integer postsId,HttpSession session) {
        commentPagination.getPostsComment().setPostsId(postsId);
        //如果总页数小于要跳转的页码就设置为最后一页
        if (commentPagination.getPagesAllNo() < commentPagination.getPageNo()) {
            commentPagination.setPageNo(commentPagination.getPagesAllNo());
        }
        this.postsCommentService.findPostsCommentPaginationByCommentPagination(commentPagination);
        List<PostsComment> postsCommentList = commentPagination.getPostsCommentList();

        Map result = new HashMap();
        //打印html页面
        String contextPath = session.getServletContext().getContextPath();
        StringBuffer html = getHtml(commentPagination,contextPath);

        result.put("html", html);
        return result;
    }

    public StringBuffer getHtml(PostsCommentPagination commentPagination,String contextPath) {
        StringBuffer html = new StringBuffer("");
        //生成分页栏
        html.append(postsCommentService.getPaginationHtml(commentPagination));
        //生成内容
        html.append(postsCommentService.getCommentHtml(commentPagination,contextPath));
        //生成分页栏
        html.append(postsCommentService.getPaginationHtml(commentPagination));
        return html;
    }



}
