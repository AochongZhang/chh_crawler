FROM hub.c.163.com/library/java:8-alpine

ENV TIME_ZONE="Asia/Shanghai"

RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

ADD target/*.jar app.jar

ADD bootstrap.yml bootstrap.yml

ENTRYPOINT ["java", "-jar", "/app.jar"]