package com.zhangaochong.chh_crawler.schedul;

import com.zhangaochong.chh_crawler.dto.Message;
import com.zhangaochong.chh_crawler.service.ChhCrawlerService;
import com.zhangaochong.chh_crawler.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
@Slf4j
public class TaskSchedule {
    @Resource
    private ChhCrawlerService sellChhCrawlerService;

    @Resource
    private SendMessageService dingtalkSendMessageService;

    private static Integer lastId = 0;

    @Scheduled(fixedRate = 10000L)
    private void getSellArticle() throws IOException {
        log.info("[开始执行] ----------------------------------");
        Message message = sellChhCrawlerService.exce();
        if (lastId == 0) {
            lastId = message.getId();
        }
        if (!lastId.equals(message.getId())) {
            dingtalkSendMessageService.send(message);
            lastId = message.getId();
        }
        log.info("[执行完成] ----------------------------------");
    }
}
