package org.leobackend.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created on 2020-12-27.
 * t_sys_user
 *
 * @author Leo
 */
@Data
public class User {

    private String id;
    private String username;
    private String password;
    private String nickname;
    private String tel;
    private String email;
    private String token;
    private String remark;
    private Date crateTime;
    private Date updateTime;

}
