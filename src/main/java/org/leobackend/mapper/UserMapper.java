package org.leobackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.leobackend.entity.User;

/**
 * Created on 2020-12-27.
 * 用户相关数据库操作
 *
 * @author Leo
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_sys_user WHERE username=#{username} and password=#{password}")
    User getUserByNameAndPasswd(String username, String password);

    @Select("SELECT * FROM t_sys_user WHERE username=#{username}")
    User getUserByName(String username);

}
