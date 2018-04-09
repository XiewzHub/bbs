package com.hnie.forum.mapper;

import com.hnie.forum.domain.Posts;
import com.hnie.forum.vo.PostsPagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/24.
 */

public interface PostsMapper {
    /**
     * 查询帖子总数
     * @return
     */
    public Integer findPostsCount();

    /**
     * 查询用户发帖数
     * @param userId
     * @return
     */
    public Integer findPostsCountByUserId(@Param("userName") String userName);

    /**
     * 添加帖子
     * @param posts
     * @return
     */
    public Integer addPosts(Posts posts);

    /**
     * 查找所有的帖子，不分页
     * @return
     */
    public List<Posts> findAllPosts();

    /**
     * 根据id查找帖子
     * @param id
     * @return
     */
    public Posts findPostsById(Integer id);

    /**
     * 更新帖子
     * @param posts
     */
    public void updatePosts(Posts posts);

    /**
     * 查询所有的，排序查询 不分页
     * @return
     */
    public List<Posts> findPostsOrderViewTop();

    /**
     * 按照置顶和观看数排序查询 分页
     * @param pageMap 含有pageNo和pageSize
     * @return
     */
    public List<Posts> findAllPostsPages(Map pageMap);



    /**
     * 查询帖子，根据参数postsPagination分页查询
     */
    public List<Posts> findPostsPageByPostsPagination(PostsPagination postsPagination);


    public Integer findPostsCountByPostsPagination(PostsPagination postsPagination);

    public Integer deletePostsById(@Param("postsId") Integer id);

    public Integer setPostsTop(@Param("postsId") Integer postsId);

    public Integer cancelPostsTop(@Param("postsId") Integer postsId);
}
