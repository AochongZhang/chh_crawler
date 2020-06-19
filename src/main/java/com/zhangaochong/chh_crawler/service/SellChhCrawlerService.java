package com.zhangaochong.chh_crawler.service;

import com.zhangaochong.chh_crawler.dto.Message;
import com.zhangaochong.chh_crawler.properties.ChhProperties;
import com.zhangaochong.chh_crawler.util.ParseUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Service
public class SellChhCrawlerService implements ChhCrawlerService {
    @Resource
    private ChhProperties chhProperties;

    @Override
    public Message exce() throws IOException {
        Document document = Jsoup.connect(chhProperties.getSellUrl())
                .header("Cookie", chhProperties.getCookie()).get();
        Element element = document.selectFirst("[id^='normalthread_']");

        Element a = element.selectFirst(".s.xst");
        String href = a.attr("href");
        String title = a.text();

        Element by = element.selectFirst(".by");
        String timeAndReplyNum = by.selectFirst(".xi1").text();
        String author = by.selectFirst("a").text();
        Integer id = ParseUtils.parseArticleIdFromHref(href);
        Message message = new Message();
        message.setId(id);
        message.setHref(href);
        message.setTitle(title);
        message.setTimeAndReplyNum(timeAndReplyNum);
        message.setAuthor(author);
        log.info("[获取出售发布贴] id={}, title={}", message.getId(), message.getTitle());
        return message;
    }
}
