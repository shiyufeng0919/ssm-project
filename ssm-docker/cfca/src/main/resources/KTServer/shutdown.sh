clear

lib=./lib
classpath=.

for file in ${lib}/*.jar
    do classpath=${classpath}:$file
done

$JAVA_HOME/bin/java -classpath "$classpath" cfca.kt.server.socket.Server ./KTConfig shutdown 0
