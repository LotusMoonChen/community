package com.coderyang.community;

import com.coderyang.community.utils.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author by Chen
 * @date 2020/10/27.
 * @link
 * @describe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTest {

    @Autowired
    private MailClient mailClient;

    // 要在test中使用thymeleaf模板引擎，注入模板管理类
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("chenyang_ma@aliyun.com", "test", "Welcome");
    }

    /**
     * 发送html内容
     */
    @Test
    public void testHtmlMail() {
        // 将内容存入thymeleaf的context域中
        Context context = new Context();
        context.setVariable("username", "sunday");

        // 使用模板引擎生成动态模板网页
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);
        mailClient.sendMail("chenyang_ma@aliyun.com", "html", content);
    }
}
