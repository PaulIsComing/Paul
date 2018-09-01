package com.yuezhou.shrioboot.controllers;

import com.yuezhou.shrioboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restful/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public void login(@RequestParam String userName, @RequestParam String md5Password) {

    }

    @RequestMapping("/register")
    public void register() {

    }


}
