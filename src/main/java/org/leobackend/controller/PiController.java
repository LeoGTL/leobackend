package org.leobackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2020-12-25.
 * TODO description
 *
 * @author Leo
 */
@RestController ("/")
public class PiController {

    @GetMapping ("/hello")
    public String hello () {
        return "hello world";
    }

}
