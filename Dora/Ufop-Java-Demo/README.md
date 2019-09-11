
### Ufop-Java-Demo

```
提前准备
1.安装 docker
MACOS：参考 https://yeasy.gitbooks.io/docker_practice/install/mac.html
（注意：MACOS下可能在执行「brew cask install docker」后要在 /Applications 下打开 docker 才能使用）
LINUX：https://runnable.com/docker/install-docker-on-linux
2. 安装 qdoractl
下载链接：https://developer.qiniu.com/dora/tools/1222/qdoractl
相关文档：https://developer.qiniu.com/dora/api/1224/quick-start

安装步骤
1. git clone git@github.com:peteryuanpan/qdemo.git
2. cd qdemo/Dora/Ufop-Java-Demo
3. docker build -t "ufop-java-demo:v1" .
4. docker run -p 9100:9100 ufop-java-demo:v1
5. 测试：http://localhost:9100/handler，结果：HelloWorld!! ufop-java-demo!
6. qdoractl push ufop-java-demo:v1
7. 到 https://portal.qiniu.com/dora/ufopv2/new 新建ufop
8. 填写版本号；选择镜像；选择资源配置；调整实例数；创建新版本
9. 测试：http://tswork.peterpy.cn/1.png?panyuan，结果：HelloWorld!! ufop-java-demo!
```
