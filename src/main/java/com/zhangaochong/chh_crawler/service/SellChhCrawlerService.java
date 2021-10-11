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
    public Message exec() throws IOException {
        Document document = Jsoup.connect(chhProperties.getSellUrl())
                .header("cookie", chhProperties.getCookie())
                .header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1").get();
        Element element = document.selectFirst(".bm_c.bt");

        Element a = element.selectFirst("a");
        String href = a.attr("href");
        String title = a.text();

        Element span = element.selectFirst(".xg1");
        String timeAndReplyNum = span.ownText();
        String author = span.selectFirst("a").text();
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
