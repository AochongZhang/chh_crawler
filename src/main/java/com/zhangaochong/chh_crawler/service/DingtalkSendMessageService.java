package com.zhangaochong.chh_crawler.service;

import com.alibaba.fastjson.JSON;
import com.zhangaochong.chh_crawler.dto.DingtalkRequest;
import com.zhangaochong.chh_crawler.dto.DingtalkRequestLink;
import com.zhangaochong.chh_crawler.dto.DingtalkRequestText;
import com.zhangaochong.chh_crawler.dto.Message;
import com.zhangaochong.chh_crawler.properties.ChhProperties;
import com.zhangaochong.chh_crawler.properties.DingtalkProperties;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class DingtalkSendMessageService implements SendMessageService {
    @Resource
    private DingtalkProperties dingtalkProperties;

    @Resource
    private ChhProperties chhProperties;

    @Override
    public void send(Message message) {
        sendLink(message.getTitle(),
                message.getTimeAndReplyNum() + " " + message.getAuthor(),
                chhProperties.getUrl() + message.getHref());
        Set<String> atMobileList = matchAt(message, dingtalkProperties.getAtUserList());
        if (atMobileList.size() != 0) {
            sendText(message.getTitle(), atMobileList);
        }
    }

    private Set<String> matchAt(Message message, Map<String, List<String>> atUserList) {
        String title = message.getTitle().toLowerCase().replace(" ", "");
        Set<String> atMobileList = new HashSet<>();
        for (String mobile : atUserList.keySet()) {
            List<String> keywordList = atUserList.get(mobile);
            for (String keyword : keywordList) {
                if (title.contains(keyword)) {
                    atMobileList.add(mobile);
                    break;
                }
            }
        }
        log.info("[匹配@用户] title={}, atMobileList={}", title, atMobileList);
        return atMobileList;
    }

    private void sendLink(String title, String text, String messageUrl) {
        log.info("[发送钉钉通知] title={}, text={}, messageUrl={}", title, text, messageUrl);
        DingtalkRequestLink requestLink = new DingtalkRequestLink();
        DingtalkRequestLink.Link link = new DingtalkRequestLink.Link();
        link.setTitle(title);
        link.setText(text);
        link.setMessageUrl(messageUrl);
        requestLink.setLink(link);
        try {
            Jsoup.connect(dingtalkProperties.getUrl())
                    .header("Content-Type", "application/json")
                    .requestBody(JSON.toJSONString(requestLink))
                    .ignoreContentType(true)
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendText(String content, Set<String> atMobileList) {
        log.info("[发送钉钉通知] content={}, atMobileList={}", content, atMobileList);
        DingtalkRequestText requestText = new DingtalkRequestText();
        DingtalkRequestText.Text text = new DingtalkRequestText.Text();
        text.setContent(content);
        requestText.setText(text);
        DingtalkRequest.At at = new DingtalkRequest.At();
        at.setAtMobiles(atMobileList);
        requestText.setAt(at);
        try {
            System.out.println(JSON.toJSONString(requestText));
            Jsoup.connect(dingtalkProperties.getUrl())
                    .header("Content-Type", "application/json")
                    .requestBody(JSON.toJSONString(requestText))
                    .ignoreContentType(true)
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
