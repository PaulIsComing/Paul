package com.yuezhou.shrioboot.controllers.pages;

import com.yuezhou.shrioboot.po.enums.RoleEnum;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/home", ""})
public class HomeController {

    private static final String USER = "USER";

    @ResponseBody
    @RequestMapping("")
    //@RequiresRoles(logical = Logical.OR, value = {RoleEnum.USER_ROLE, RoleEnum.ADMIN_ROLE})
    public String home() {
        return "welcome to home";
    }
}
