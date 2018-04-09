package com.hnie.forum.utils;

import com.hnie.forum.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/5/14.
 */
public class ControllerUtils {

    public static boolean isNoUser(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user==null){
            return true;
        }
        return false;
    }
}
