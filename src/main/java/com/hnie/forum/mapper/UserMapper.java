package com.hnie.forum.mapper;

import com.hnie.forum.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */
public interface UserMapper {

    public List<User> findAllUsers();

    public User findUserById(Integer id);

    public Integer deleteUser(User user);

    public Integer updateUser(User user);

    public void addUser(User user);

    public User selectUserByLogin(User user);

    public List<User> findUserByName(@Param(value = "name") String name);
}
