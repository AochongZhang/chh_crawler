package com.zhangaochong.chh_crawler.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtils {
    private static final Pattern TID_PATTERN = Pattern.compile("tid=(\\d{7})");

    public static Integer parseArticleIdFromHref(String href) {
        Matcher matcher = TID_PATTERN.matcher(href);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }

}
