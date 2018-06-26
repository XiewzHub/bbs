package com.hnie.forum.service.impl;

import com.hnie.forum.domain.Posts;
import com.hnie.forum.mapper.PostsMapper;
import com.hnie.forum.service.PostsService;
import com.hnie.forum.vo.PostsPagination;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/24.
 */
@Service("postsService")
@Transactional
public class PostsServiceImpl implements PostsService {

    @Resource(name = "postsMapper")
    private PostsMapper postsMapper;

    @Override
    public Integer addPost(Posts posts) {
        if (StringUtils.isBlank(posts.getTitle())) {
            return 0;
        }
        Integer rowNum = postsMapper.addPosts(posts);
//        //得到插入的id
//        Integer postsId = posts.getId();
//        // 取出srcText和text内容
//        StringBuffer srcText = new StringBuffer(posts.getSrcText());
//        StringBuffer text = new StringBuffer(posts.getText());
//        //存入文件并返回文件路径

        return rowNum;
    }

    @Override
    public Integer addPost(Posts posts, HttpServletRequest request) throws IOException {
        if (StringUtils.isBlank(posts.getTitle())) {
            return 0;
        }

        // 取出srcText和text内容
        String srcText = posts.getSrcText();
        String text = posts.getText();
        // posts.setText("");
        // posts.setSrcText("");
        //获取路径
        String rootPath = request.getSession().getServletContext().getRealPath("");
        String srcTextPath = rootPath + "\\posts\\" + posts.getId() + "\\resources\\text\\srcText.md";
        String textPath = rootPath + "\\posts\\" + posts.getId() + "\\resources\\text\\text.txt";

        // 替换分割符号，不同系统的文件夹分割符号不同
        srcTextPath.replace("\\",File.separator);
        textPath.replace("\\",File.separator);

        //保存文本并返回路径
        FileUtils.writeByteArrayToFile(new File(srcTextPath), srcText.getBytes(), false);
        FileUtils.writeByteArrayToFile(new File(textPath), text.getBytes(), false);

        // 分割路径，获取相对路径
        srcTextPath = srcTextPath.replace(rootPath, "");
        textPath = textPath.replace(rootPath, "");
        // posts = this.findPostsById(postsId);

        //修改此贴
        posts.setSrcText(srcTextPath);
        posts.setText(textPath);
        // this.modifyPosts(posts);
        postsMapper.addPosts(posts);

        //得到插入的id
        Integer postsId = posts.getId();

        return postsId;
    }

    @Override
    public List<Posts> findAllPosts() {
        return postsMapper.findAllPosts();
    }

    @Override
    public Posts findPostsById(Integer id) {
        return postsMapper.findPostsById(id);
    }

    @Override
    public void modifyPosts(Posts posts) {
        if (posts != null) {
            this.postsMapper.updatePosts(posts);

        } else {
            System.out.println("posts没有值，出错了！！！");
            throw new IllegalArgumentException("");
        }
    }

    /**
     * 修改前需要查询查询出路径，修改文件
     *
     * @param posts
     * @param session
     */
    @Override
    public void modifyPosts(Posts posts, HttpSession session) {
        Posts postsById = this.findPostsById(posts.getId());
        String rootPath = session.getServletContext().getRealPath("");
        String srcTextPath = rootPath + postsById.getSrcText();
        String textPath = rootPath + postsById.getText();

        //保存文本并返回路径
        try {
            FileUtils.writeByteArrayToFile(new File(srcTextPath), posts.getSrcText().getBytes(), false);
            FileUtils.writeByteArrayToFile(new File(textPath), posts.getText().getBytes(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        posts.setSrcText(postsById.getSrcText());
        posts.setText(postsById.getText());
        // modifyPosts(posts);
    }

    @Override
    public List<Posts> findPostsToHome() {
        List<Posts> postsList = this.postsMapper.findPostsOrderViewTop();
        return postsList;
    }

    @Override
    public PostsPagination findAllPostsDefaultPages() {
        PostsPagination postsPagination = new PostsPagination();
        findByPages(postsPagination);

        return postsPagination;
    }


    @Override
    public void findPostsByPages(PostsPagination postsPagination) {
        findByPages(postsPagination);
    }

    /**
     * 查找某个用户的帖子
     * 默认分页，时间排序
     *
     * @param postsPagination
     * @return 分页对象
     */
    @Override
    public PostsPagination findPostsPagesByUserName(String userName) {
        PostsPagination postsPagination = new PostsPagination();
        postsPagination.getPosts().setAuthor(userName);
        postsPagination = this.findPostsPagesByPostsPagination(postsPagination);
        return postsPagination;
    }

    /**
     * 查找某个用户的帖子
     * 分页，时间排序
     *
     * @param userName
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PostsPagination findPostsPagesByUserName(String userName, Integer pageNo, Integer pageSize) {
        PostsPagination postsPagination = new PostsPagination();
        postsPagination.getPosts().setAuthor(userName);
        postsPagination.setPageNo(pageNo);
        postsPagination.setPageSize(pageSize);

        postsPagination = this.findPostsPagesByPostsPagination(postsPagination);
        return postsPagination;
    }

    /**
     * 此方法有条件查询，并且分页
     * 查询条件条件：用户名author，标题title，内容text，置顶setTop
     *
     * @param postsPagination
     * @return
     */
    public PostsPagination findPostsPagesByPostsPagination(PostsPagination postsPagination) {
        Integer temp = postsPagination.getPageNo();

        //将pageNo变成用于分页查询的第一个参数值，也就是limit的第一个参数
        postsPagination.setPageNo((temp - 1) * postsPagination.getPageSize());

        //查找分页结果并设置值
        List<Posts> postsList = this.postsMapper.findPostsPageByPostsPagination(postsPagination);
        postsPagination.setPostsList(postsList);

        // TODO: 2017/5/13 按条件查询记录总数
        Integer postsCount = this.postsMapper.findPostsCountByPostsPagination(postsPagination);
        postsPagination.setTotal(postsCount);
        postsPagination.setPagesAllNo((int) Math.ceil((float) postsPagination.getTotal() / (float) postsPagination.getPageSize()));

        //查询完后就将pageNo设置成原来的值
        postsPagination.setPageNo(temp);
        return postsPagination;
    }

    @Override
    public Integer deletePostsById(Integer id) {
        return this.postsMapper.deletePostsById(id);
    }

    @Override
    public Integer setPostsTop(Integer postsId) {
        return this.postsMapper.setPostsTop(postsId);
    }

    @Override
    public Integer cancelPostsTop(Integer postsId) {
        return this.postsMapper.cancelPostsTop(postsId);
    }

    @Override
    public Integer findPostsCountByUserId(String userName) {
        return this.postsMapper.findPostsCountByUserId(userName);
    }


    /**
     * 私有方法，传入分页对象，自动填入内容
     * 此方法没有条件查询，只能分页查询所有的帖子
     *
     * @param postsPagination
     */
    private void findByPages(PostsPagination postsPagination) {
        if (postsPagination == null) {
            postsPagination = new PostsPagination();
        }
        if (postsPagination.getPageNo() == null || postsPagination.getPageSize() == null) {
            postsPagination = new PostsPagination();
        }
        //设置limit后面的分页参数，
        Map pageMap = new HashMap();
        pageMap.put("pageNo", (postsPagination.getPageNo() - 1) * postsPagination.getPageSize());
        pageMap.put("pageSize", postsPagination.getPageSize());
        List<Posts> postsList = this.postsMapper.findAllPostsPages(pageMap);
        postsPagination.setPostsList(postsList);

        Integer postsCount = this.postsMapper.findPostsCount();
        postsPagination.setTotal(postsCount);
        postsPagination.setPagesAllNo((int) Math.ceil((float) postsPagination.getTotal() / (float) postsPagination.getPageSize()));
    }


}
