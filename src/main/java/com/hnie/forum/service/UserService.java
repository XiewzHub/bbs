package com.hnie.forum.service;

import com.hnie.forum.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */
public interface UserService {

    public List<User> findAllUsers();

    public void addUser(User user);

    public User findUserByNamePass(User user);

    public List<User> findUserByName(String name);

    public Integer modifyUser(User user);

    public User findUserByUserId(User user);
}
