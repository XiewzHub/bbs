package com.hnie.forum.mapper;

import com.hnie.forum.domain.PostsComment;
import com.hnie.forum.vo.PostsCommentPagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface PostsCommentMapper {
    /**
     * 查询评论总数
     *
     * @return
     */

    public Integer addPostsComment(PostsComment postsComment);

    public Integer deletePostsCommentByCommentId(@Param("commentId") Integer commentId);

    public Integer updatePostsCoumment(PostsComment postsComment);

    public List<PostsComment> findPostsCommentByCommentPagination(PostsCommentPagination commentPagination);

    public Integer findCountByCommentPagination(PostsCommentPagination commentPagination);

    public Integer findPostsCommentCount();
}
