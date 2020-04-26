package com.zhangaochong.chh_crawler.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 钉钉机器人配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "dingtalk")
public class DingtalkProperties {
    /** 钉钉机器人地址 */
    private String url;

    /**
     * at群内用户
     * key: 用户手机号
     * value: 关键字列表
     */
    private Map<String, List<String>> atUserList;
}
