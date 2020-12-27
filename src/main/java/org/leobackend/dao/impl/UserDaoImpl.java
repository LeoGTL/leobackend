package org.leobackend.dao.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.leobackend.dao.UserDao;
import org.leobackend.mapper.UserMapper;
import org.leobackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created on 2020-12-26.
 * 用户相关数据库操作
 *
 * @author Leo
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    public boolean isValid(String username, String password) {
        User user = userMapper.getUserByNameAndPasswd(username, password);
        return user != null;
    }

}
