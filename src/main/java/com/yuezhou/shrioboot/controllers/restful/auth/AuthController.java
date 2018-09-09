package com.yuezhou.shrioboot.controllers.restful.auth;

import com.yuezhou.shrioboot.po.LoginRequest;
import com.yuezhou.shrioboot.po.ResponseBean;
import com.yuezhou.shrioboot.po.UserInfo;
import com.yuezhou.shrioboot.po.enums.StatusCodeEnum;
import com.yuezhou.shrioboot.service.AuthService;
import com.yuezhou.shrioboot.service.UserService;
import com.yuezhou.shrioboot.shrio.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restful/auth")
public class AuthController {
    private final Long EXPIRE_DAY = 24 * 60 * 60 * 1000L;
    private final Long EXPIRE_MONTH = 30 * 24 * 60 * 60 * 1000L;

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseBean login(@RequestBody LoginRequest request) {

        ResponseBean response = ResponseBean.init();
        try {

            UserInfo userInfo = authService.loginUser(request.getUserName(), request.getPassword());

            if (null != userInfo) {
                long expire = request.isRemember() ? EXPIRE_MONTH : EXPIRE_DAY;
                expire = System.currentTimeMillis() + expire;

                Map<String, Object> data = new HashMap<>();
                data.put("token", JWTUtil.sign(userInfo.getUserId(), request.getPassword(), expire));
                data.put("expire", expire);
                response.setData(data);
            } else {
                response.setCode(StatusCodeEnum.AUTHFAIL.getCode());
            }
        } catch (Throwable e) {
            response.setCode(StatusCodeEnum.SYSTEMERROR.getCode());
            response.setMsg(e.getMessage());
        } finally {
            return response;
        }
    }

    @PostMapping("/register")
    public ResponseBean register(@RequestBody LoginRequest request) {
        ResponseBean response = ResponseBean.init();
        boolean register = userService.registerUser(request.getUserName(), request.getPassword());
        if (!register) {
            response.setCode(StatusCodeEnum.AUTHFAIL.getCode());
            response.setMsg("register fail");
        }
        return response;
    }


}
