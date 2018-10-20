package com.muteng.dgjs.dao.impl;

import com.muteng.dgjs.dao.UserDao;
import com.muteng.dgjs.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * date: 2018/10/20 00:12
 * author: snowxuyu
 * describe:
 */

@Repository
public class UserDaoImpl implements UserDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String NAME_SPACES = UserDaoImpl.class.getName();

    @Resource
    private SqlSession sqlSession;

    @Override
    public List<User> getAllUser() {
        logger.info(">>>> userDao getAllUser ");
        List<User> userList = sqlSession.selectList(NAME_SPACES + ".getAllUser");
        return userList;
    }

    @Override
    public void addUser(User user) {
        logger.info(">>>> userDao addUser ");
        int insert = sqlSession.insert(NAME_SPACES + ".insert", user);
        logger.info(">>>>> addUserResult:{}", insert);
    }
}
