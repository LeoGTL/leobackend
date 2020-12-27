package org.leobackend.dao;

import org.leobackend.entity.User;

/**
 * Created on 2020-12-27.
 * TODO description
 *
 * @author Leo
 */
public interface UserDao {

    /**
     * 判断用户名密码是否正确
     * @param username 用户名
     * @param password 密码
     * @return true：正确 false：不正确
     */
    boolean isValid(String username, String password);
}
