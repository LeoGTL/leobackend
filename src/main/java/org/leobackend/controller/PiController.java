package org.leobackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2020-12-27.
 * 树莓派相关
 *
 * @author Leo
 */
@RestController
@RequestMapping ("/api/pi")
public class PiController {

    @GetMapping ("hello")
    public String test () {
        return "你好！树莓派";
    }

}
