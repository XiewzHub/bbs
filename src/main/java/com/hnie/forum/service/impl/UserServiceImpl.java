package com.hnie.forum.service.impl;

import com.hnie.forum.domain.User;
import com.hnie.forum.mapper.PostsMapper;
import com.hnie.forum.mapper.UserMapper;
import com.hnie.forum.service.PostsService;
import com.hnie.forum.service.UserService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource
    private PostsService postsService;


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<User> findAllUsers() {
        return this.userMapper.findAllUsers();
    }

    @Override
    public void addUser(User user) {
        user.setHead("/smart_froum/images/head/defaultHead.png");
        this.userMapper.addUser(user);
    }

    @Override
    public User findUserByNamePass(User user) {
        if (user == null) {
            return null;
        }
        user.setPhonenum(user.getName());

        user = this.userMapper.selectUserByLogin(user);
        //todo 用户发帖数要查询到
        if (user != null) {
            Integer postsNum = this.postsService.findPostsCountByUserId(user.getName());
            user.setPostsNum(postsNum);
        }
        return user;
    }

    @Override
    public List<User> findUserByName(String name) {
        return this.userMapper.findUserByName(name);
    }

    @Override
    public Integer modifyUser(User user) {
        return this.userMapper.updateUser(user);
    }

    @Override
    public User findUserByUserId(User user) {
        user = this.userMapper.findUserById(user.getId());
        //todo 用户发帖数要查询到
        Integer postsNum = this.postsService.findPostsCountByUserId(user.getName());
        user.setPostsNum(postsNum);
        return user;
    }


}
