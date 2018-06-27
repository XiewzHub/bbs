package com.hnie.forum.utils;

import com.hnie.forum.domain.Posts;
import com.hnie.forum.exception.BbsException;
import org.apache.commons.io.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */
public class PostsUtils {
    private static Logger logger = Logger.getLogger(PostsUtils.class);
    public static final String mdFileName = "srcText.md";
    public static final String h5FileName = "text.txt";


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
            String textPath = rootPath + posts.getText().replace("\\",File.separator);
            String srcTextPath = rootPath + posts.getSrcText().replace("\\",File.separator);
        // String separator = File.separator;
        try {
                // byte[] byteArray = MyFileUtils.readFileToByteArray(new File(rootPath + "123.xnk"));
                String text = new String(FileUtils.readFileToByteArray(new File(textPath)));
                String srcText = new String(FileUtils.readFileToByteArray(new File(srcTextPath)));
                posts.setText(text);
                posts.setSrcText(srcText);

            } catch (IOException e) {
                e.printStackTrace();
                throw new BbsException("请确认文件"+textPath+"与"+srcTextPath+"否存在",e);
            }
        // }

        return posts;
    }

    /**
     * 将帖子内容保存到指定文件夹下，分不同文件保存不同类型的内容
     *  仅仅做保存，不改变对象内容
     * @param targetDirPath 需要保存的文件夹目录
     * @param posts
     * @throws IOException
     */
    public static void postsSave2File(String targetDirPath,Posts posts) throws IOException {
        String srcText = posts.getSrcText();
        String text = posts.getText();
        // 替换分割符号，不同系统的文件夹分割符号不同
        targetDirPath.replace("\\",File.separator);

        logger.info("转换后的路径分别为"+targetDirPath+"---"+"  --File.separator="+File.separator);
        BufferedWriter mdFileWriter = MyFileUtils.newFile(targetDirPath, PostsUtils.mdFileName);
        BufferedWriter txtFileWriter = MyFileUtils.newFile(targetDirPath, PostsUtils.h5FileName);
        // 写文件
        mdFileWriter.write(srcText);
        txtFileWriter.write(text);
        // 关闭文件
        mdFileWriter.close();
        txtFileWriter.close();
    }
}
