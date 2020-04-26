package com.zhangaochong.chh_crawler.dto;

import lombok.Data;

@Data
public class Message {
    private Integer id;
    private String title;
    private String content;
    private String href;
    private String timeAndReplyNum;
    private String author;
}
