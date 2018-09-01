package com.yuezhou.shrioboot.service;

import com.yuezhou.shrioboot.po.UserInfo;
import com.yuezhou.shrioboot.po.enums.PageEnum;
import com.yuezhou.shrioboot.utils.MD5Utils;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;

import java.lang.reflect.Array;
import java.util.*;

@Component
public class UserService {

    private ArrayList<UserInfo> userList;

    public UserService() {
        userList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(i);
            userInfo.setUserName("user" + String.valueOf(i));
            userInfo.setSalt(String.valueOf(i));
            userInfo.setPassword(MD5Utils.md5("password" + String.valueOf(i) + String.valueOf(i)));
            Set<String> roles = new HashSet<>();
            roles.add("user");
            if (i % 2 == 0) {
                roles.add("admin");
            }
            userInfo.setRole(roles);

            Set<String> permissions = new HashSet<>();
            permissions.add(PageEnum.HOME.getPageAction());
            permissions.add(PageEnum.MANAGEMENT.getPageAction(PageEnum.ActionEnum.READ));
            if (i % 1 == 0)
                permissions.add(PageEnum.MANAGEMENT.getPageAction(PageEnum.ActionEnum.CREAT));
            if (i % 2 == 0)
                permissions.add(PageEnum.MANAGEMENT.getPageAction(PageEnum.ActionEnum.UPDATE));
            if (i % 3 == 0)
                permissions.add(PageEnum.MANAGEMENT.getPageAction(PageEnum.ActionEnum.DELETE));

            userInfo.setPremisson(permissions);
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
