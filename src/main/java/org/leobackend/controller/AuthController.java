package org.leobackend.controller;

import org.apache.commons.lang3.StringUtils;
import org.leobackend.entity.LoginRequestDTO;
import org.leobackend.entity.User;
import org.leobackend.service.AuthService;
import org.leobackend.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2020-12-26.
 * 用户认证相关
 *
 * @author Leo
 */
@RestController
@RequestMapping ("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping ("/getToken")
    public String getToken (@RequestBody LoginRequestDTO requestDTO) {
        String token = authService.getToken (requestDTO);
        if (StringUtils.isEmpty(token)) {
            return "用户不存在";
        }
        return token;
    }

    @GetMapping ("/error")
    public String error () {
        return "token error";
    }

}
