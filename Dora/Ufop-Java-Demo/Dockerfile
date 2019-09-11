FROM ubuntu:14.04
FROM java:8

MAINTAINER panyuan@qiniu.com

RUN mkdir -p /root/ufop-java-demo
ADD ufop-java-demo.jar /root/ufop-java-demo/ufop-java-demo.jar

RUN mkdir -p /root/ufop-java-demo/ufop-java-demo_lib
ADD ufop-java-demo_lib/*.jar /root/ufop-java-demo/ufop-java-demo_lib/

WORKDIR /root/ufop-java-demo/
ENTRYPOINT java -jar ufop-java-demo.jar
