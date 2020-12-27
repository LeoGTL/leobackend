package org.leobackend.service;

import org.leobackend.entity.LoginRequestDTO;
import org.leobackend.entity.User;

/**
 * Created on 2020-12-26.
 * 用户认证相关
 *
 * @author Leo
 */
public interface AuthService {

    /**
     * 验证用户是否合法
     * @return User
     */
    String getToken(LoginRequestDTO requestDTO);
}
