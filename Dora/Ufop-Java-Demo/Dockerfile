FROM ubuntu:14.04
FROM java:8

MAINTAINER panyuan@qiniu.com

RUN mkdir -p /root/ufop-java-demo
#ADD jdk-8u121-linux-x64.tar.gz /root/ufop-java-demo/
ADD ufop-java-demo.jar /root/ufop-java-demo/ufop-java-demo.jar

RUN mkdir -p /root/ufop-java-demo/ufop-java-demo_lib
ADD ufop-java-demo_lib/*.jar /root/ufop-java-demo/ufop-java-demo_lib/

#RUN sudo apt-get update
#RUN sudo apt-get -y install fontconfig
#RUN sudo fc-cache -fv

ENV PATH=$PATH:/root/ufop-java-demo/
ENV PATH=$PATH:/root/ufop-java-demo/jdk1.8.0_121/bin
EXPOSE 9100

WORKDIR /root/ufop-java-demo/
ENTRYPOINT java -jar ufop-java-demo.jar
