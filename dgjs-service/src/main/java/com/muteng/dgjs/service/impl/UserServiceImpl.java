package com.muteng.dgjs.service.impl;

import com.muteng.dgjs.common.exception.BusinessException;
import com.muteng.dgjs.dao.UserDao;
import com.muteng.dgjs.domain.User;
import com.muteng.dgjs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * date: 2018/10/20 00:21
 * author: snowxuyu
 * describe:
 */

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserDao userDao;

    @Override
    public List<User> getAllUser() {
        logger.info(">>>>> userService getAllUser ");
        return userDao.getAllUser();
    }

    @Override
    public void addUser(User user) throws RuntimeException {
        userDao.addUser(user);
    }

    @Override
    public void method27(Long id, String name, String idcard) throws BusinessException {
        /**
         * 具体逻辑：
         *  1、根据用户ID查询用户信息
         *  2、设置用户的姓名和身份证号 。应该调用实名认证的接口
         *  3、更新用户信息
         */
        //User user = userDao.getById(id);
    }
}
