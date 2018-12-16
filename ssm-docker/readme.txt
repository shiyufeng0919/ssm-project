example please view https://www.cnblogs.com/kaixinyufeng/p/9689982.html

apache-tomcat-7.0.86:应用该conf包下面的server.xml和tomcat_users.xml文件

image_tomcat:server.xml和tomcat_user.xml文件有相应修改,Dockerfile为定制的镜像文件

ssm-docker为项目的源码

(1)server.xml文件
<Connector executor="tomcatThreadPool"
               port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"
	       URIEncoding="UTF-8"  #增加
 />

(2)tomcat_users.xml文件
<role rolename="manager-gui" />
<role rolename="manager-script" />
<user username="account001" password="password001" roles="manager-gui,manager-script" />

(3)定制镜像Dockerfile
//###start 添加如下内容
#First docker file from bolingcavalry
#version 0.0.1
#Author:shiyufeng
#基础镜像
FROM tomcat:7.0.77-jre8

#作者
MAINTAINER shiyufeng

#定义工作目录
ENV WORK_PATH /usr/local/tomcat/conf

#定义要替换的文件名
ENV USER_CONF_FILE_NAME tomcat-users.xml

#定义要替换的server.xml文件名
ENV SERVER_CONF_FILE_NAME server.xml

#删除原文件tomcat-users.xml
RUN rm $WORK_PATH/$USER_CONF_FILE_NAME

#复制文件tomcat-users.xml
COPY ./$USER_CONF_FILE_NAME $WORK_PATH/

#删除原文件server.xml
RUN rm $WORK_PATH/$SERVER_CONF_FILE_NAME

#复制文件server.xml
COPY ./$SERVER_CONF_FILE_NAME $WORK_PATH/

//###end

构建镜像：
//在当前目录构建镜像。注意后面的点为当前目录
docker build -t tomcat001:0.0.1 .

//查看镜像
docker image ls tomcat001

(4)修改maven配置文件setting.xml
<server>
      <id>tomcat7</id>
      <username>account001</username>
      <password>password001</password>
  </server>
添加上述内容

（5）web项目,pom.xml目录执行
mvn clean package -U -Dmaven.test.skip=true tomcat7:redeploy

（6）浏览器访问即可
http://localhost:8080/loadbalancedemo/hello?name=开心玉凤
