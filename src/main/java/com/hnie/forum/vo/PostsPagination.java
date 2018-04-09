package com.hnie.forum.vo;

import com.hnie.forum.domain.Posts;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
public class PostsPagination extends Pagination{
    private List<Posts> postsList;
    private Posts posts = new Posts();

    public PostsPagination() {
        super();
    }

    public PostsPagination(Integer pageNo, Integer pageSize) {
        super(pageNo, pageSize);
    }

    public List<Posts> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Posts> postsList) {
        this.postsList = postsList;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }
}
