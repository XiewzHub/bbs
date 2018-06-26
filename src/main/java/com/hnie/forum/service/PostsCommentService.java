package com.hnie.forum.service;

import com.hnie.forum.domain.PostsComment;
import com.hnie.forum.vo.PostsCommentPagination;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface PostsCommentService {
    public Integer addPostsComment(PostsComment postsComment);

    public Integer deletePostsCommentByCommentId(Integer commentId);

    public Integer modifyPostsCoumment(PostsComment postsComment);

    public PostsCommentPagination findPostsCommentPaginationByCommentPagination(PostsCommentPagination commentPagination);

    public StringBuffer getPaginationHtml(PostsCommentPagination commentPagination);

    public StringBuffer getCommentHtml(PostsCommentPagination commentPagination,String contextPath);

}
