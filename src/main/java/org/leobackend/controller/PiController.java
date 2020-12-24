package org.leobackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello () {
        return "hello world";
    }

}
