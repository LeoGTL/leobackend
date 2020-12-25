package org.leobackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2020-12-25.
 * TODO description
 *
 * @author Leo
 */
@RestController
@RequestMapping ("/")
public class PiController {

    @GetMapping("/hello")
    public String hello (String token) {
        System.out.println(token);
        return "hello world.....";
    }

}
