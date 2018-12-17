@echo off
cls

if "%JAVA_HOME%" == "" goto noJavaHome

set home=%cd%
set lib=%home%\lib
set classpath=.

setlocal enabledelayedexpansion

FOR /R %lib% %%f IN (*.jar) DO set classpath=%%f;!classpath!

start "Server" "%JAVA_HOME%"\bin\java cfca.kt.server.socket.Server %home%\KTConfig startup 0

endlocal
goto exit

:noJavaHome
echo The JAVA_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto exit

:exit