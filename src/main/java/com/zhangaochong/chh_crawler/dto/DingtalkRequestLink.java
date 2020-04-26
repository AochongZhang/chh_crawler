package com.zhangaochong.chh_crawler.dto;

import lombok.Data;

@Data
public class DingtalkRequestLink extends DingtalkRequest {
    private Link link;

    @Data
    public static class Link {
        /** 消息标题 */
        private String title;
        /** 消息内容。如果太长只会部分展示 */
        private String text;
        /** 点击消息跳转的URL */
        private String messageUrl;
        /** 图片URL */
        private String picUrl;
    }

    public DingtalkRequestLink() {
        this.setMsgtype("link");
    }
}
