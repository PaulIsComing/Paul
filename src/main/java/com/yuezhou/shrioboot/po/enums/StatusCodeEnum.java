package com.yuezhou.shrioboot.po.enums;

public enum StatusCodeEnum {
    OK(200),
    AUTHFAIL(401),
    SYSTEMERROR(500);

    private int code;

    StatusCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
