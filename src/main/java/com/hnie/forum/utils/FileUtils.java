package com.hnie.forum.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/4/25.
 */
public class FileUtils {


    public static void moveFile(String srcPath, String destPath) {

        try {
            (new File(destPath)).mkdirs();  //如果文件夹不存在  则建立新文件夹
            File a = new File(srcPath); //原路径封装为file对象
            String[] file = a.list(); //列出原来路径下的文件以及文件夹
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                //遍历
                if (srcPath.endsWith(File.separator)) { //
                    temp = new File(srcPath + file[i]);
                } else {
                    temp = new File(srcPath + File.separator + file[i]);
                }

                if (temp.isDirectory()) {//如果是子文件夹
                    moveFile(srcPath + "/" + file[i], destPath + "/" + file[i]);
                    temp.delete();//删除文件夹
                }

                if (temp.isFile()) {//如果是子文件

                    FileInputStream input = new FileInputStream(temp);
//                    File dest = new File(destPath + "/" + (temp.getName()).toString());
//                    System.out.println(temp.renameTo(dest));
                    FileOutputStream output = new FileOutputStream(destPath + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                    temp.delete();//删除文件
                }

                /*if (i == file.length - 1) {
                    return;
                }*/
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
