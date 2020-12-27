package org.leobackend.controller;

import org.leobackend.entity.LoginRequestDTO;
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
        boolean isValidUser = authService.userVerify (requestDTO.getUsername(), requestDTO.getPassword());
        return isValidUser ? TokenUtils.createToken(requestDTO.getUsername(), requestDTO.getPassword()) : "非法用户";
    }

    @GetMapping ("/error")
    public String error () {
        return "token error";
    }

}
