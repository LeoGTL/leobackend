package org.leobackend.entity;

import lombok.Data;
import lombok.NonNull;

/**
 * Created on 2020-12-26.
 * 登录请求参数封装
 *
 * @author Leo
 */
@Data
public class LoginRequestDTO {

    @NonNull
    private String username;
    @NonNull
    private String password;

}
