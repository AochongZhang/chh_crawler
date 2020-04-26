package com.zhangaochong.chh_crawler.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * chh配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "chh")
public class ChhProperties {
    /** chh主站地址 */
    private String url;

    /** 出售发布区标准版地址 */
    private String sellUrl;

    /** 浏览器标识 */
    private String userAgent;

    /** 用户cookie，浏览器中获取 */
    private String cookie;
}
