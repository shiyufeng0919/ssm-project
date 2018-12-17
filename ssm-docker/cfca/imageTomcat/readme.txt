##定制tomcat镜像(cfca目录下)
docker build -t tomcat-cfca:v001 .

##maven命令
mvn clean package -U -Dmaven.test.skip=true -Dmaven.url=http://192.168.99.100:8090/manager/text   tomcat7:redeploy
