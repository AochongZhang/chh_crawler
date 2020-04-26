package com.zhangaochong.chh_crawler.dto;

import lombok.Data;

@Data
public class DingtalkRequestText extends DingtalkRequest {
    private Text text;

    @Data
    public static class Text {
        private String content;
    }

    public DingtalkRequestText() {
        this.setMsgtype("text");
    }
}
