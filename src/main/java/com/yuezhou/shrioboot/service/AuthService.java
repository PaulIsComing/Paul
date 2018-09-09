package com.yuezhou.shrioboot.service;

import com.yuezhou.shrioboot.po.UserInfo;
import com.yuezhou.shrioboot.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    public UserInfo loginUser(String userName, String password) {
        UserInfo userInfo = userService.getUser(userName);
        if (null != userInfo && MD5Utils.md5(MD5Utils.md5(password) + userInfo.getSalt()).equalsIgnoreCase(userInfo.getPassword())) {
            userService.updateLastLogin(userInfo.getUserId());
            return userInfo;
        } else {
            return null;
        }
    }
}
