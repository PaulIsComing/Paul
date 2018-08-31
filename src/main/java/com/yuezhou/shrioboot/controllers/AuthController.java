package com.yuezhou.shrioboot.controllers;

import com.yuezhou.shrioboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {
    @Autowired
    UserService userService;


}
