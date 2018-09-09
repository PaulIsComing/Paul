package com.yuezhou.shrioboot;

import com.yuezhou.shrioboot.service.UserService;
import com.yuezhou.shrioboot.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Console;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShrioBootApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {


        System.out.println("100" + "1");


        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        while (!"exit".equalsIgnoreCase(read)) {
            String a = read;
            read = scan.nextLine();
            System.out.println(a + read);
        }


//
//        redisUtils.del("userlist");
//
//        UserInfo userInfo = userService.getUser(1);

    }

}

