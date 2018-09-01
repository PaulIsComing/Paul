package com.yuezhou.shrioboot;

import com.yuezhou.shrioboot.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShrioBootApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void contextLoads() {


        redisUtils.set("test", "value");

        String value =(String) redisUtils.get("test");

    }

}

