package com.hnie.forum.utils;

import com.hnie.forum.domain.Posts;
import org.apache.commons.io.*;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */
public class PostsUtils {

    /**
     * 重载方法
     * @param postsList
     * @param session
     * @return
     */
    public static List<Posts> getPostsText(List<Posts> postsList, HttpSession session) {
        String rootPath = session.getServletContext().getRealPath("");
        return getPostsText(postsList, rootPath);
    }
    public static List<Posts> getPostsText(List<Posts> postsList, HttpServletRequest request) {
        String rootPath = request.getSession().getServletContext().getRealPath("");
        return getPostsText(postsList, rootPath);
    }

    public static List<Posts> getPostsText(List<Posts> postsList, String rootPath) {
        List<Posts> result = new ArrayList<Posts>();
        for (Posts posts : postsList) {
            result.add(getPostsText(posts, rootPath));
        }
        return result;
    }

    public static Posts getPostsText(Posts posts, HttpSession session) {
        String rootPath = session.getServletContext().getRealPath("");
        posts = getPostsText(posts, rootPath);
        return posts;
    }

    public static Posts getPostsText(Posts posts, HttpServletRequest request) {
        String rootPath = request.getSession().getServletContext().getRealPath("");
        posts = getPostsText(posts, rootPath);
        return posts;
    }

    /**
     * 通过路径获取帖子内容
     * @param posts 原帖子对象
     * @param rootPath 根路径
     * @return 完整内容的帖子
     */
    public static Posts getPostsText(Posts posts, String rootPath) {
        // if (posts.getId() > 87) {
            String textPath = posts.getText();
            String srcTextPath = posts.getSrcText();
            try {
                byte[] byteArray = FileUtils.readFileToByteArray(new File(rootPath + textPath));
                String text = new String(FileUtils.readFileToByteArray(new File(rootPath + textPath)));
                String srcText = new String(FileUtils.readFileToByteArray(new File(rootPath + srcTextPath)));
                posts.setText(text);
                posts.setSrcText(srcText);

            } catch (IOException e) {
                e.printStackTrace();
            }
        // }

        return posts;
    }
}
