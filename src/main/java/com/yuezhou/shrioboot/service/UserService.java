package com.yuezhou.shrioboot.service;

import com.yuezhou.shrioboot.po.UserInfo;
import com.yuezhou.shrioboot.po.enums.PageEnum;
import com.yuezhou.shrioboot.po.enums.RoleEnum;
import com.yuezhou.shrioboot.utils.MD5Utils;
import com.yuezhou.shrioboot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {


    @Autowired
    private RedisUtils redisUtils;

    public UserService() {
    }

    public UserInfo getUser(long userId) {
        ArrayList<UserInfo> userList = (ArrayList<UserInfo>) redisUtils.get("userlist");

        Optional<UserInfo> findAny = userList.stream().filter(f -> f.getUserId() == userId).findAny();
        if (findAny.isPresent())
            return findAny.get();

        return null;

    }

    public UserInfo getUser(String userName) {

        ArrayList<UserInfo> userList = (ArrayList<UserInfo>) redisUtils.get("userlist");

        if (null != userList) {
            Optional<UserInfo> findAny = userList.stream().filter(f -> userName.equalsIgnoreCase(f.getUserName())).findAny();
            if (findAny.isPresent())
                return findAny.get();
        }

        return null;
    }

    public boolean updateLastLogin(Long userId) {
        return true;
    }

    public boolean registerUser(String userName, String password) {
        ArrayList<UserInfo> userList = (ArrayList<UserInfo>) redisUtils.get("userlist");

        boolean add = true;
        if (null != userList) {
            Optional<UserInfo> optional = userList.stream().filter(f -> userName.equalsIgnoreCase(f.getUserName())).findAny();
            add = !optional.isPresent();
        }
        if (add) {

            Long userId;
            if (null == userList) {
                userList = new ArrayList<>();
                userId = 1L;
            } else
                userId = (long) (userList.size() + 1);

            UserInfo newUser = new UserInfo();
            newUser.setUserId(userId);
            newUser.setUserName(userName);
            String salt = MD5Utils.md5(userName).toUpperCase().substring(5, 15);
            newUser.setPassword(MD5Utils.md5(MD5Utils.md5(password) + salt));
            newUser.setSalt(salt);

            Set<String> roles = new HashSet<>();
            roles.add(RoleEnum.USER_ROLE);
            if (userId % 2 == 0)
                roles.add(RoleEnum.ADMIN_ROLE);

            newUser.setRole(roles);

            Set<String> permissions = new HashSet<>();
            permissions.add(PageEnum.HOME.getPageAction());
            permissions.add(PageEnum.MANAGEMENT.getPageAction(PageEnum.ActionEnum.READ));

            if (userId % 2 == 0) {
                permissions.add(PageEnum.MANAGEMENT.getPageAction(PageEnum.ActionEnum.CREAT));
                permissions.add(PageEnum.MANAGEMENT.getPageAction(PageEnum.ActionEnum.UPDATE));
                permissions.add(PageEnum.MANAGEMENT.getPageAction(PageEnum.ActionEnum.DELETE));
            }
            newUser.setPremisson(permissions);


            userList.add(newUser);
            redisUtils.set("userlist", userList);
            return true;
        }

        return false;
    }
}
