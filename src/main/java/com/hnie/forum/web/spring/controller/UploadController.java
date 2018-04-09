package com.hnie.forum.web.spring.controller;

import com.hnie.forum.utils.ControllerUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/10/30.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

/*
    @Resource(name = "userService")
    private UserService userService;
*/


    /**
     * 在add页面的图片上传，此时没有对应的id
     *
     * @param request
     * @param response
     * @param attach
     */
    @RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
    public void uploadimage(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            /**
             * 针对json字符串的乱码解决方案
             */
            response.setContentType("application/json;charset=UTF-8");//防止json串的数据传递乱码

            /**
             * 因为前面的编辑页面放在posts文件夹里，前台url解析的地址前面也会带有posts，
             * 那个url是用来访问上传到服务器的图片的，所以服务器应该有对应的文件夹，也就是posts文件夹
             * 最前面需要有“/”。
             */
            String rootPath = request.getSession().getServletContext().getRealPath("/posts/resources/upload/");
//            将图片上传到服务器并且返回最终的文件名
            String fileName = copyFileAndGetFileName(attach, rootPath);


            //下面response返回的json格式是editor.md所限制的，规范输出就OK
            /**
             * url传这个resources/upload/就可以，前端会把editPage加到前面
             */
            response.getWriter().write("{\"success\": 1, \"message\":\"上传成功\",\"url\":\"resources/upload/" + fileName + "\"}");
        } catch (Exception e) {
            try {
                response.getWriter().write("{\"success\":0}");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }


    /**
     * 在编辑界面的图片上传，这时是修改，所以图片文件上传的地方有所改动
     *
     * @param id
     * @param request
     * @param response
     * @param attach
     */
    @RequestMapping(value = "/{id}/uploadimage", method = RequestMethod.POST)
    public void uploadimage(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            /**
             * 针对json字符串的乱码解决方案
             */
            response.setContentType("application/json;charset=UTF-8");//防止json串的数据传递乱码

            /**
             * 因为前面的编辑页面放在posts文件夹里，前台url解析的地址前面也会带有posts，
             * 那个url是用来访问上传到服务器的图片的，所以服务器应该有对应的文件夹，也就是posts文件夹
             * 最前面需要有“/”。
             */
            String rootPath = request.getSession().getServletContext().getRealPath("/posts/" + id + "/resources/upload/");

//            将图片上传到服务器并且返回最终的文件名
            String fileName = copyFileAndGetFileName(attach, rootPath);

            //下面response返回的json格式是editor.md所限制的，规范输出就OK
            /**
             * url传这个resources/upload/就可以，前端会把editPage加到前面
             */
            response.getWriter().write("{\"success\": 1, \"message\":\"上传成功\",\"url\":\"resources/upload/" + fileName + "\"}");
        } catch (Exception e) {
            try {
                response.getWriter().write("{\"success\":0}");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * 展示到主页使用的图片上传
     *
     * @param request
     * @param response
     * @param attach
     */
    @RequestMapping(value = "uploadCarouselImg", method = RequestMethod.POST)
    public void uploadCarouselImg(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "uploadImg", required = false) MultipartFile attach) {

        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            /**
             * 针对json字符串的乱码解决方案
             */
            response.setContentType("application/json;charset=UTF-8");//防止json串的数据传递乱码

            String rootPath = request.getSession().getServletContext().getRealPath("/images/carousel/");

//            将图片上传到服务器并且返回最终的文件名
            String fileName = copyFileAndGetFileName(attach, rootPath);


            response.getWriter().write("{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/smart_froum/images/carousel/"
                    + fileName + "\"}");

        } catch (Exception e) {
            try {
                response.getWriter().write("{\"success\":0}");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "uploadMobileTypeHead", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadMobileTypeHead(HttpSession session, HttpServletRequest request, @RequestParam(value = "uploadImg", required = false) MultipartFile attach) {

        Map result = new HashMap();
        if (ControllerUtils.isNoUser(session)) {
            result.put("result", 2);
            return result;
        }
        try {
            String rootPath = session.getServletContext().getRealPath("/images/mobile");
            String fileName = copyFileAndGetFileName(attach, rootPath);

            result.put("success", 1);
            result.put("message", "上传成功");
            result.put("imgUrl", "/smart_froum/images/mobile/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 用户头像上传
     *
     * @param session
     * @param attach
     * @return
     */
    @RequestMapping(value = "uploadUserHead", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadUserHead(HttpSession session, @RequestParam(value = "uploadUserHeadImg", required = false) MultipartFile attach) {
        Map result = new HashMap();
        if (ControllerUtils.isNoUser(session)) {
            result.put("result", 2);
            return result;
        }
        try {
            String rootPath = session.getServletContext().getRealPath("/images/head");
            String fileName = copyFileAndGetFileName(attach, rootPath);
            result.put("success", 1);
            result.put("message", "上传成功");
            result.put("imgUrl", "/smart_froum/images/head/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 用uuid生成文件名字
     *
     * @param fileName
     * @return
     */
    private String getUUIDFileName(String fileName) {
        String uuid = UUID.randomUUID().toString();
//        去掉中间的“-”
        uuid = uuid.replaceAll("-", "");

        //按照“.”来分割字符串，用于获取文件后缀
        String[] strArray = fileName.split("[.]");
        String newName = uuid + "." + strArray[strArray.length - 1];

        return newName;
    }

    /**
     * 将图片上传到服务器并且返回最终的文件名
     *
     * @param attach
     * @param rootPath
     * @return filename
     * @throws IOException
     */
    private String copyFileAndGetFileName(MultipartFile attach, String rootPath) throws IOException {
        /**
         * 文件路径不存在则需要创建文件路径
         */
        File filePath = new File(rootPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        String fileName = attach.getOriginalFilename();
        //把名字变化一下变成uuid的文件名
        fileName = getUUIDFileName(fileName);

        //最终文件名
        File realFile = new File(rootPath + File.separator + fileName);
        FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);
        return fileName;
    }

}
