clear

lib=./lib
classpath=.

for file in ${lib}/*.jar
    do classpath=${classpath}:$file
done

$JAVA_HOME/bin/java -Xms512M -Xmx1024M -Dfile.encoding=utf-8 -classpath ${classpath} cfca.kt.server.socket.Server ./KTConfig startup 0