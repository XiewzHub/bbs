package com.hnie.forum.utils;

import com.hnie.forum.exception.BbsException;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2017/4/25.
 */
public class MyFileUtils {
    private static Logger logger = Logger.getLogger(MyFileUtils.class);
    /**
     * 默认文件编码
     */
    public static String FILE_CHARSET = "UTF-8";

    public static void moveFile(String srcPath, String destPath) {
        logger.info("文件移动：（原路径）"+srcPath+"  (目标路径)"+destPath);
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
//                    logger.info(temp.renameTo(dest));
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
            logger.info("复制整个文件夹内容操作出错");
            e.printStackTrace();
            throw new BbsException("移动文件出错！！！",e);
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

    /**
     * 获取文件的读取器
     *
     * @param path
     * @param fileName
     * @return
     * @throws YcLoansException
     */
    public static BufferedWriter newFile(String path, String fileName) throws BbsException {
        return newFile(path, fileName, false);
    }

    // 获取文件的读取器
    public static BufferedWriter newFile(String path, String fileName, boolean isAppend) throws BbsException {
        File file = new File(path, fileName);
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            if (!file.getParentFile().mkdirs()) {
                throw new BbsException("创建目标文件所在目录失败！" + file.getParentFile());
            }
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new BbsException("创建目标文件失败！" + file.getName());
            }
        }
        try {

            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, isAppend),
                    FILE_CHARSET));
        } catch (FileNotFoundException e) {
            throw new BbsException("file " + file.getPath() + " not found", e);
        } catch (IOException e) {
            throw new BbsException("io exeption", e);
        }
    }


    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            logger.info("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                logger.info("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                logger.info("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            logger.info("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            logger.info("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = MyFileUtils.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = MyFileUtils.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            logger.info("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            logger.info("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//  // 删除单个文件
//  String file = "c:/test/test.txt";
//  DeleteFileUtil.deleteFile(file);
//  logger.info();
        // 删除一个目录
        // String dir = "D:/home/web/upload/upload/files";
        String dir = "E:\\resource\\workspace\\MyProjects\\bbs\\src\\main\\webapp\\posts\\10005\\resources\\text\\srcText.md";
        MyFileUtils.deleteFile(dir);
//  logger.info();
//  // 删除文件
//  dir = "c:/test/test0";
//  DeleteFileUtil.delete(dir);

    }
}
