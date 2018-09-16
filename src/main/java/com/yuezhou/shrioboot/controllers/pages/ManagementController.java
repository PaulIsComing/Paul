package com.yuezhou.shrioboot.controllers.pages;

import com.yuezhou.shrioboot.po.enums.PageEnum;
import com.yuezhou.shrioboot.po.enums.RoleEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class ManagementController {

    @RequestMapping("")
    @RequiresPermissions(value = PageEnum.MANAGEMENT_QUERY)
    @RequiresRoles(value = RoleEnum.ADMIN_ROLE)
    public String management() {
        return "welcome to management";
    }
}
