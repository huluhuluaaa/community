package com.xcy.community;

import com.xcy.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)

public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testPlainMail() {
        mailClient.sendMail("626030651@qq.com", new String[]{"522022320160@smail.nju.edu.cn"}, new String[]{"hluhuluya@126.com", "hlhuluya@126.com"},"some_title", "Hey! How is it going, Justin?");
    }

    @Test
    public void testHtmlMail() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "huluhulu");
        map.put("password", "123321");
        Context context = new Context();
        context.setVariables(map);

        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("626030651@qq.com", new String[]{"hluhuluya@126.com", "hlhuluya@126.com"}, new String[]{"522022320160@smail.nju.edu.cn"}, "HTML", content);
    }

}
