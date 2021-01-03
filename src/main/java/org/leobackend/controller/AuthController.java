package org.leobackend.controller;

import org.apache.commons.lang3.StringUtils;
import org.leobackend.entity.LoginRequestDTO;
import org.leobackend.entity.ResultEntity;
import org.leobackend.entity.ResultStatusEnum;
import org.leobackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020-12-26.
 * 用户认证相关
 *
 * @author Leo
 */
@RestController
@RequestMapping ("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController (AuthService authService) {
        this.authService = authService;
    }

    @PostMapping ("/getToken")
    public ResultEntity<Map<String, String>> getToken (@RequestBody LoginRequestDTO requestDTO) {
        ResultEntity<Map<String, String>> result = new ResultEntity<Map<String, String>>();
        String token = authService.getToken (requestDTO);
        if (StringUtils.isEmpty(token)) {
            result.setStatus(ResultStatusEnum.PARAMETER_ERROR.getValue());
            result.setMessage("用户名或密码错误");
            return result;
        }
        result.setStatus(ResultStatusEnum.OK.getValue());
        result.setMessage(ResultStatusEnum.OK.getMessage());
        Map<String, String> resultData = new HashMap<String, String>();
        resultData.put("token", token);
        result.setData(resultData);
        return result;
    }

    @GetMapping ("/error")
    public String error () {
        return "token error";
    }

}
