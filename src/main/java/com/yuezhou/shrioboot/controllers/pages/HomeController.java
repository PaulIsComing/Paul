package com.yuezhou.shrioboot.controllers.pages;

import com.yuezhou.shrioboot.po.enums.RoleType;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private static final String USER = "USER";

    @RequestMapping("")
    @RequiresRoles(logical = Logical.OR, value = {RoleType.USER})
    public void home() {


    }
}
