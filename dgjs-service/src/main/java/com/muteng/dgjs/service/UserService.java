package com.muteng.dgjs.service;

import com.muteng.dgjs.domain.User;

import java.util.List;

/**
 * date: 2018/10/20 00:20
 * author: snowxuyu
 * describe:
 */
public interface UserService {

    List<User> getAllUser() throws RuntimeException;

    void addUser(User user) throws RuntimeException;
}
