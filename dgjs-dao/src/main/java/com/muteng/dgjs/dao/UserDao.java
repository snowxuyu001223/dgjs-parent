package com.muteng.dgjs.dao;

import com.muteng.dgjs.domain.User;

import java.util.List;

/**
 * date: 2018/10/20 00:11
 * author: snowxuyu
 * describe:
 */
public interface UserDao {

    List<User> getAllUser();

    void addUser(User user);
}
