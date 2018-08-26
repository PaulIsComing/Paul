package com.yuezhou.shrioboot.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MD5Utils {

    public static String md5(String beforeMD5) {
        try {
            //确定计算方法
            MessageDigest md5Encode = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            return base64en.encode(md5Encode.digest(beforeMD5.getBytes("utf-8")));
        } catch (Exception ex) {
            return null;
        }
    }
}
