package org.leobackend.service.impl;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.leobackend.dao.impl.UserDaoImpl;
import org.leobackend.entity.LoginRequestDTO;
import org.leobackend.entity.User;
import org.leobackend.service.AuthService;
import org.leobackend.utils.TokenUtils;
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
    public String getToken(LoginRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        String password = requestDTO.getPassword();
        User user = userDao.getUserByNameAndPasswd(username, password);
        if (user == null) {
            return null;
        }
        String existsToken = user.getToken();
        if (!StringUtils.isEmpty(existsToken)) {
            return existsToken;
        }
        String token = TokenUtils.createToken(username, TokenUtils.getMd5(password));
        try {
            userDao.updateTokenForUser (username, token);
            return token;
        } catch (Exception e) {
            return null;
        }
    }
}
