package com.hnie.forum.service;

import com.hnie.forum.domain.Posts;
import com.hnie.forum.vo.PostsPagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
public interface PostsService {
    /**
     * 添加帖子
     *
     * @return
     */
    public Integer addPost(Posts posts);

    /**
     * 添加帖子,
     * 将传入服务器路径
     *
     * @return
     */
    public Integer addPost(Posts posts, HttpServletRequest request) throws IOException;

    /**
     * 查找所有的帖子，此方法不分页，不排序
     * @return
     */
    public List<Posts> findAllPosts();

    /**
     * 通过id查找帖子
     * @param id
     * @return
     */
    public Posts findPostsById(Integer id);

    /**
     * 传入新的posts对象，修改这个对象，id必须不为空
     * @param posts
     */
    public void modifyPosts(Posts posts);

    /**
     * 重载修改
     * @param posts
     * @param session
     */
    public void modifyPosts(Posts posts, HttpSession session);

    /**
     * 查找所有帖子，不分页，排序
     * @return 帖子列表（10条数据）
     */
    public List<Posts> findPostsToHome();

    /**
     *自动查出分页好的分页对象，只能查出一页
     * 基本没什么用
     * @return 分页对象
     */
//    @Deprecated
    public PostsPagination findAllPostsDefaultPages();

    /**
     * 传入分页对象，自动填入内容
     * 此方法没有条件查询，只能分页查询所有的帖子
     * @param postsPagination
     */
    public void findPostsByPages(PostsPagination postsPagination);


    /**
     * 查找某个用户的帖子
     * 分页，时间排序
     * @param userName
     * @return
     */
    public PostsPagination findPostsPagesByUserName(String userName);

    public PostsPagination findPostsPagesByUserName(String userName, Integer pageNo, Integer pageSize);

    /**
     * 此方法有条件查询，并且分页
     * 查询条件条件：用户名author，标题title，内容text，置顶setTop
     * @param postsPagination
     * @return
     */
    public PostsPagination findPostsPagesByPostsPagination(PostsPagination postsPagination);

    public Integer deletePostsById(Integer id);

    public Integer setPostsTop(Integer postsId);

    public Integer cancelPostsTop(Integer postsId);

    public Integer findPostsCountByUserId(String userName);
}
