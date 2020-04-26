package com.zhangaochong.chh_crawler.dto;

import lombok.Data;

import java.util.Set;

@Data
public class DingtalkRequest {
    private String msgtype;
    private At at;

    @Data
    public static class At {
        private Set<String> atMobiles;
        private Boolean isAtAll = false;
    }
}
