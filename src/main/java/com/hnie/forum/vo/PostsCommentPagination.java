package com.hnie.forum.vo;

import com.hnie.forum.domain.PostsComment;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public class PostsCommentPagination extends Pagination {
    private PostsComment postsComment = new PostsComment();
    private List<PostsComment> postsCommentList;

    public PostsCommentPagination() {
        super();
    }

    public PostsCommentPagination(PostsComment postsComment) {
        this.postsComment = postsComment;
    }

    public PostsCommentPagination(Integer pageNo, Integer pageSize) {
        super(pageNo, pageSize);
    }

    public PostsComment getPostsComment() {
        return postsComment;
    }

    public void setPostsComment(PostsComment postsComment) {
        this.postsComment = postsComment;
    }

    public List<PostsComment> getPostsCommentList() {
        return postsCommentList;
    }

    public void setPostsCommentList(List<PostsComment> postsCommentList) {
        this.postsCommentList = postsCommentList;
    }
}
