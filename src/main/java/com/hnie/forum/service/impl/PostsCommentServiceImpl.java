package com.hnie.forum.service.impl;

import com.hnie.forum.domain.PostsComment;
import com.hnie.forum.domain.User;
import com.hnie.forum.mapper.PostsCommentMapper;
import com.hnie.forum.service.PostsCommentService;
import com.hnie.forum.vo.PostsCommentPagination;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@Service("postsCommentService")
@Transactional
public class PostsCommentServiceImpl implements PostsCommentService {
    @Resource(name = "postsCommentMapper")
    private PostsCommentMapper postsCommentMapper;

    @Override
    public Integer addPostsComment(PostsComment postsComment) {
        return this.postsCommentMapper.addPostsComment(postsComment);
    }

    @Override
    public Integer deletePostsCommentByCommentId(Integer commentId) {
        return this.postsCommentMapper.deletePostsCommentByCommentId(commentId);
    }

    @Override
    public Integer modifyPostsCoumment(PostsComment postsComment) {
        return this.postsCommentMapper.updatePostsCoumment(postsComment);
    }

    @Override
    public PostsCommentPagination findPostsCommentPaginationByCommentPagination(PostsCommentPagination commentPagination) {
        Integer pageNo = commentPagination.getPageNo();
        Integer pageSize = commentPagination.getPageSize();
        commentPagination.setPageNo((pageNo - 1) * pageSize);

        List<PostsComment> postsCommentList = this.postsCommentMapper.findPostsCommentByCommentPagination(commentPagination);
        commentPagination.setPostsCommentList(postsCommentList);

        Integer count = this.postsCommentMapper.findCountByCommentPagination(commentPagination);
        commentPagination.setPageNo(pageNo);
        commentPagination.setPagesAllNoByCountAndPageSize(count, pageSize);

        return commentPagination;
    }

    /**
     * 帖子内容HTML打印
     *
     * @param commentPagination
     * @return
     */
    public StringBuffer getCommentHtml(PostsCommentPagination commentPagination,String contextPath) {
        StringBuffer html = new StringBuffer("");
        List<PostsComment> commentList = commentPagination.getPostsCommentList();
        Integer pageNo = commentPagination.getPageNo();
        Integer pageSize = commentPagination.getPageSize();
        html.append("<ul class=\"remove-li-point\">");
        //循环帖子内容
        for (int i = 0; i < commentList.size(); i++) {
            PostsComment postsComment = commentList.get(i);
            User user = postsComment.getUser();
            Integer count = ((pageNo - 1) * pageSize) + i + 1;
            html.append("<div class=\"fade-in\"><li class=\"row\">");
            html.append("<div class=\"col-md-2\">");
            html.append("<a href=\"javascript:void(0);\"><img src=\"" + contextPath + user.getHead() + "\"class=\"head-style-set\" style=\"width: 80px;height: 80px;\"></a>");
            html.append("</div>");

            html.append("<div class=\"col-md-10\" style=\"float: left;\">");
            html.append("<div class=\"row\" style=\"padding-right: 10px;\">");
            html.append("<div style=\"font-size: small;color: #888;float:left;\">");
            html.append("<span>评论者:" + user.getName() + "</span><span>|</span>");
            html.append("<span>评论时间:" + postsComment.getCommentDate() + "</span></div>");
            //楼层
            html.append("<div style=\"font-size: small;color: #888;float:right;\">");
            html.append("<span>" + count + "楼</span>");
            html.append("</div>");
            html.append("<div style=\"float: left;text-align: left;padding: 18px;padding-left: 1px;width: 100%;\">");
            html.append("<span style=\"font-size: 16px;color: #000\">" + postsComment.getCommentText() + "</span>");

            html.append("</div></div></div>");
            html.append("</li><ol class=\"divider\"></ol></div>");
        }

        html.append("</ul>");
        return html;
    }

    public StringBuffer getPaginationHtml(PostsCommentPagination commentPagination) {
        Integer pageNo = commentPagination.getPageNo();//当前页
        Integer pageSize = commentPagination.getPageSize();//页面尺寸
        Integer allPageNo = commentPagination.getPagesAllNo();//总页数
        Integer displayNo = 5;//展示页数

        StringBuffer html = new StringBuffer("");
        html.append("<ul class=\"pagination\">");
        //如果pageNo是1大于就生成上一页
        if (pageNo > 1) {
            html.append("<li><a name=\"pagination\" pageNo=\"" + (pageNo - 1) + "\" pageSize=\"" + pageSize + "\" pageAllNo=\"" + allPageNo + "\" href=\"javascript:void(0);\">上一页</a></li>");
        }
        if (allPageNo > displayNo) {
            //得到中间的页数
            Integer midPageNo;
            //开始的页数
            Integer startPageNo;
            //结束的页数
            Integer endPageNo;
            if (displayNo % 2 == 0) {
                //如果显示页数是双数，就取半数作为中间，如显示4页的中间也是第2页
                midPageNo = displayNo / 2;
            } else {
                //单数就取最中间的数字，共5页就取第3页
                midPageNo = (int) Math.ceil(displayNo / 2);
            }
            //当前页大于等于中间页，就可以用公式计算出开始页
            startPageNo = pageNo - midPageNo + 1;
            if (startPageNo <= 1) {
                startPageNo = 1;
            }
            //结束页=开始页+展示页
            endPageNo = startPageNo + displayNo;
            //结束页大于等于总页数
            if (endPageNo >= allPageNo) {
                endPageNo = allPageNo;
            }
            //如果结束页-开始页<展示页
            if ((endPageNo - startPageNo) < displayNo) {
                //开始页变为
                startPageNo = endPageNo - displayNo + 1;
            }
            //生成每一页
            for (int i = 0; i < displayNo; i++) {
                //当前页加标注
                if (pageNo == (i + startPageNo)) {
                    html.append("<li class=\"active\"><a name=\"pagination\" pageNo=\"" + pageNo + "\" pageSize=\"" + pageSize + "\" pageAllNo=\"" + allPageNo + "\" href=\"javascript:void(0);\">" + pageNo + "</a></li>");
                } else {
                    html.append("<li><a name=\"pagination\" pageNo=\"" + (startPageNo + i) + "\" pageSize=\"" + pageSize + "\" pageAllNo=\"" + allPageNo + "\" href=\"javascript:void(0);\">" + (startPageNo + i) + "</a></li>");
                }
            }
        } else {
            //生成每一页
            for (int i = 1; i <= allPageNo; i++) {
                //当前页加标注
                if (pageNo == (i)) {
                    html.append("<li class=\"active\"><a name=\"pagination\" pageNo=\"" + pageNo + "\" pageSize=\"" + pageSize + "\" pageAllNo=\"" + allPageNo + "\" href=\"javascript:void(0);\">" + pageNo + "</a></li>");
                } else {
                    html.append("<li><a name=\"pagination\" pageNo=\"" + (i) + "\" pageSize=\"" + pageSize + "\" pageAllNo=\"" + allPageNo + "\" href=\"javascript:void(0);\">" + (i) + "</a></li>");
                }
            }

        }

        //生成下一页
        if (allPageNo != 0 && pageNo < allPageNo) {
            html.append("<li><a name=\"pagination\" pageNo=\"" + (pageNo + 1) + "\" pageSize=\"" + pageSize + "\" pageAllNo=\"" + allPageNo + "\" href=\"javascript:void(0);\">下一页</a></li>");
        }

        if (allPageNo != 0) {
            html.append("<li><input name=\"skipPageNo\" class=\"inputPageNo form-control\" style=\"margin-right:6px;margin-left: 12px;float: left;padding: 6px 12px;width: 10%;\" type=\"text\"></li>");
            html.append("<li><a name=\"skipBtn\" pageSize=\"" + pageSize + "\" pageAllNo=\"" + allPageNo + "\" href=\"javascript:void(0);\">跳转</a></li>");
            html.append("<li><span>共" + allPageNo + "页</span></li></ul>");
        }

        return html;
    }

//    @Override
//    public Integer findCountByCommentPagination(PostsCommentPagination commentPagination) {
//        return null;
//    }
}
