package com.coderyang.community;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testUUID() {
        String s = UUID.randomUUID().toString();
        System.out.println(s);
    }
}
