package com.yuezhou.shrioboot.po;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;

public class CurrentUser {

    private static ThreadLocal<UserInfo> currentUser;

    public static UserInfo getUser() {
        return currentUser.get();
    }

    public static void setUser(UserInfo user) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            currentUser.set(user);
        }
    }

    public static void remove() {
        if (null != currentUser)
            currentUser.remove();
    }
}
