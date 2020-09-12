# chh_crawler Chiphell论坛爬虫

## docker方式安装

### 安装nacos
```shell
docker run -d \
    --name nacos \
    -e MODE=standalone \
    -p 8848:8848 \
    nacos/nacos-server
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
