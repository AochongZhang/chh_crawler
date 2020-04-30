package com.zhangaochong.chh_crawler;

import com.zhangaochong.chh_crawler.properties.ChhProperties;
import com.zhangaochong.chh_crawler.properties.DingtalkProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class ChhCrawlerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ChhCrawlerApplication.class, args);
        ChhProperties chhProperties = ctx.getBean(ChhProperties.class);
        DingtalkProperties dingtalkProperties = ctx.getBean(DingtalkProperties.class);

        log.info("[chh配置] url={}", chhProperties.getUrl());
        log.info("[chh配置] sellUrl={}", chhProperties.getSellUrl());
        log.info("[chh配置] userAgent={}", chhProperties.getUserAgent());
        log.info("[chh配置] cookie={}", chhProperties.getCookie());

        log.info("[钉钉机器人配置] url={}", dingtalkProperties.getUrl());
        Map<String, List<String>> atUserList = dingtalkProperties.getAtUserList();
        for (String mobile : atUserList.keySet()) {
            log.info("[钉钉机器人配置] atUserList mobile={} keywordList={}", mobile, atUserList.get(mobile));
        }
    }

}
