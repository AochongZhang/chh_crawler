# chh_crawler
Chiphell论坛爬虫, 主要用来爬取"出售发布区"发布的内容，并通过钉钉机器人发送通知。
通过配置钉钉用户手机号实现匹配内容@到指定用户。
采用nacos实现动态配置。

## docker方式安装

### 安装nacos
```shell
docker run -d \
    --name nacos \
    -e MODE=standalone \
    -p 8848:8848 \
    nacos/nacos-server
```

### 增加配置
1. 访问nacos管理界面，http://ip地址:8848/nacos/index.html
2. 新增配置，DataID：chh-crawler.yaml
####
配置内容模版
```yaml
chh:
  # chh主站地址
  url: https://www.chiphell.com/
  # 出售发布区电脑版地址
  sell-url: https://www.chiphell.com/forum.php?mod=forumdisplay&fid=26&filter=author&orderby=dateline&mobile=1
  # 用户cookie，浏览器中获取
  cookie:
# 钉钉机器人配置
dingtalk:
  # 钉钉机器人地址
  url:
  # at群内用户
  at-user-list:
    # 用户手机号，匹配的内容会@该用户，可配置多个用户
    131xxxxxxxx:
      # 关键字列表，用于匹配内容，可配置多个
      - k1
      - k2
    132xxxxxxxx:
      - k3
```

### 安装chh_crawler
```shell
docker run -d \
    --name chh_crawler \
    --link nacos \
aochongzhang/chh_crawler
```

### 查看日志
```shell
docker logs -f chh_crawler
```