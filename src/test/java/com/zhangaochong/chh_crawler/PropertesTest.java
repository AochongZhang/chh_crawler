package com.zhangaochong.chh_crawler;

import com.zhangaochong.chh_crawler.properties.ChhProperties;
import com.zhangaochong.chh_crawler.properties.DingtalkProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PropertesTest {
    @Resource
    private ChhProperties chhProperties;

    @Resource
    private DingtalkProperties dingtalkProperties;

    @Test
    public void propertiesTest() {
        System.out.println(chhProperties);
        System.out.println(dingtalkProperties);
    }
}
