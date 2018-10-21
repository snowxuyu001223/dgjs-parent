package com.muteng.dgjs.service;

import com.muteng.dgjs.common.exception.BusinessException;
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

    /**
     * 提交身份认证
     * @param id
     * @param name
     * @param idcard
     * @throws BusinessException
     */
    void method27(Long id, String name, String idcard) throws BusinessException;
}
