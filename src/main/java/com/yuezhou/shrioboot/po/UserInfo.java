package com.yuezhou.shrioboot.po;

import java.util.Set;

public class UserInfo {

    private String userName;
    private String password;
    private String salt;
    private Set<String> role;
    private Set<String> premisson;
    private long userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public Set<String> getPremisson() {
        return premisson;
    }

    public void setPremisson(Set<String> premisson) {
        this.premisson = premisson;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
