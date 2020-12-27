package org.leobackend.service.impl;

import org.leobackend.dao.impl.UserDaoImpl;
import org.leobackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2020-12-26.
 * 用户认证相关
 *
 * @author Leo
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public boolean userVerify(String username, String password) {
        return userDao.isValid (username, password);
    }
}
