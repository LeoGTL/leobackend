package org.leobackend.service;

import org.springframework.stereotype.Service;

/**
 * Created on 2020-12-26.
 * 用户认证相关
 *
 * @author Leo
 */
public interface AuthService {

    /**
     * 验证用户是否合法
     * @param username 用户名
     * @param password 密码
     * @return true：合法  false：不合法
     */
    boolean userVerify(String username, String password);
}
