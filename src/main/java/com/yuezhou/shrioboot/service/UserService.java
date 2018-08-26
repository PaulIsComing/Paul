package com.yuezhou.shrioboot.service;

import com.yuezhou.shrioboot.po.UserInfo;
import com.yuezhou.shrioboot.utils.MD5Utils;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserService {

    private ArrayList<UserInfo> userList;

    public UserService() {
        for (int i = 0; i < 5; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(i);
            userInfo.setUserName("user" + String.valueOf(i));
            userInfo.setSalt(String.valueOf(i));
            userInfo.setPassword(MD5Utils.md5("password" + String.valueOf(i) + String.valueOf(i)));
            userInfo.setRole("user");
            if (i % 2 == 0) {
                userInfo.setRole("admin");
            }

            userInfo.setPremisson("curd");
            if (i % 3 == 0)
                userInfo.setPremisson("r");

            userList.add(userInfo);
        }
    }

    public UserInfo getUser(long userId) {

        Optional<UserInfo> findAny = userList.stream().filter(f -> f.getUserId() == userId).findAny();
        if (findAny.isPresent())
            return findAny.get();

        return null;

    }

    public UserInfo getUser(String userName) {

        Optional<UserInfo> findAny = userList.stream().filter(f -> f.getUserName() == userName).findAny();
        if (findAny.isPresent())
            return findAny.get();

        return null;

    }

}
