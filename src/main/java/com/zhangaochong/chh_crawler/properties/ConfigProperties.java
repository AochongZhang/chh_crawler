package com.zhangaochong.chh_crawler.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {
    /** 配置版本 */
    private String version;
}
