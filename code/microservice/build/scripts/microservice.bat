@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  microservice startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and MICROSERVICE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Djava.net.preferIPv4Stack=true" "-Djava.net.preferIPv6Addresses=false" "-server" "-Xmx2048m" "-Xms128m"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\microservice-1.0.jar;%APP_HOME%\lib\rxnetty-http-0.5.2.jar;%APP_HOME%\lib\jersey-server-2.7.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.7.jar;%APP_HOME%\lib\jersey-container-jetty-http-2.7.jar;%APP_HOME%\lib\slf4j-log4j12-1.7.25.jar;%APP_HOME%\lib\karyon2-servo-2.7.5.jar;%APP_HOME%\lib\karyon2-hystrix-stream-0.1.1.jar;%APP_HOME%\lib\jetty-server-9.3.13.v20161014.jar;%APP_HOME%\lib\jetty-servlet-9.3.13.v20161014.jar;%APP_HOME%\lib\ribbon-2.1.0.jar;%APP_HOME%\lib\ribbon-core-2.1.0.jar;%APP_HOME%\lib\ribbon-httpclient-2.1.0.jar;%APP_HOME%\lib\ribbon-transport-2.1.0.jar;%APP_HOME%\lib\ribbon-loadbalancer-2.1.0.jar;%APP_HOME%\lib\ribbon-httpasyncclient-0.3.13.jar;%APP_HOME%\lib\gson-2.3.1.jar;%APP_HOME%\lib\hystrix-core-1.4.26.jar;%APP_HOME%\lib\hystrix-metrics-event-stream-1.4.26.jar;%APP_HOME%\lib\guice-servlet-4.0.jar;%APP_HOME%\lib\jedis-2.9.0.jar;%APP_HOME%\lib\karyon2-eureka-2.7.5.jar;%APP_HOME%\lib\ribbon-eureka-2.1.0.jar;%APP_HOME%\lib\karyon2-admin-healthcheck-plugin-2.7.5.jar;%APP_HOME%\lib\rxjava-1.2.6.jar;%APP_HOME%\lib\rxnetty-common-0.5.2.jar;%APP_HOME%\lib\rxnetty-tcp-0.5.2.jar;%APP_HOME%\lib\netty-codec-http-4.1.5.Final.jar;%APP_HOME%\lib\jersey-common-2.7.jar;%APP_HOME%\lib\jersey-client-2.7.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\hk2-api-2.2.0.jar;%APP_HOME%\lib\javax.inject-2.2.0.jar;%APP_HOME%\lib\hk2-locator-2.2.0.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\jetty-continuation-9.1.1.v20140108.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\log4j-1.2.17.jar;%APP_HOME%\lib\karyon2-governator-2.7.5.jar;%APP_HOME%\lib\karyon-rest-router-1.2.2.jar;%APP_HOME%\lib\hystrix-rx-netty-metrics-stream-1.4.20.jar;%APP_HOME%\lib\jetty-security-9.3.13.v20161014.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\commons-configuration-1.8.jar;%APP_HOME%\lib\archaius-core-0.6.6.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\commons-collections-3.2.1.jar;%APP_HOME%\lib\jersey-client-1.11.jar;%APP_HOME%\lib\jersey-apache-client4-1.11.jar;%APP_HOME%\lib\netflix-commons-util-0.1.1.jar;%APP_HOME%\lib\rxnetty-contexts-0.4.9.jar;%APP_HOME%\lib\netflix-statistics-0.1.1.jar;%APP_HOME%\lib\httpasyncclient-4.0.jar;%APP_HOME%\lib\jackson-core-2.5.2.jar;%APP_HOME%\lib\commons-pool2-2.4.2.jar;%APP_HOME%\lib\eureka2-client-shaded-2.0.0-rc.2.jar;%APP_HOME%\lib\karyon2-core-2.7.5.jar;%APP_HOME%\lib\karyon2-admin-web-2.7.5.jar;%APP_HOME%\lib\netty-handler-4.1.5.Final.jar;%APP_HOME%\lib\netty-codec-4.1.5.Final.jar;%APP_HOME%\lib\jersey-guava-2.7.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\hk2-utils-2.2.0.jar;%APP_HOME%\lib\aopalliance-repackaged-2.2.0.jar;%APP_HOME%\lib\governator-1.9.3.jar;%APP_HOME%\lib\reflections-0.9.9.jar;%APP_HOME%\lib\commons-codec-1.6.jar;%APP_HOME%\lib\httpcore-nio-4.3.jar;%APP_HOME%\lib\freemarker-2.3.15.jar;%APP_HOME%\lib\karyon2-admin-2.7.5.jar;%APP_HOME%\lib\netty-buffer-4.1.5.Final.jar;%APP_HOME%\lib\netty-transport-4.1.5.Final.jar;%APP_HOME%\lib\jsr311-api-1.1.1.jar;%APP_HOME%\lib\jetty-6.1.26.jar;%APP_HOME%\lib\jersey-guice-1.9.1.jar;%APP_HOME%\lib\jersey-servlet-1.18.1.jar;%APP_HOME%\lib\jersey-server-1.18.1.jar;%APP_HOME%\lib\pytheas-core-1.25.jar;%APP_HOME%\lib\jetty-util-6.1.26.jar;%APP_HOME%\lib\jersey-json-1.11.jar;%APP_HOME%\lib\commons-beanutils-1.8.2.jar;%APP_HOME%\lib\high-scale-lib-1.1.2.jar;%APP_HOME%\lib\commons-io-1.4.jar;%APP_HOME%\lib\jaxb-impl-2.2.3-1.jar;%APP_HOME%\lib\jackson-jaxrs-1.9.2.jar;%APP_HOME%\lib\jackson-xc-1.9.2.jar;%APP_HOME%\lib\stax-api-1.0.1.jar;%APP_HOME%\lib\jaxb-api-2.2.2.jar;%APP_HOME%\lib\stax-api-1.0-2.jar;%APP_HOME%\lib\activation-1.1.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\jetty-http-9.3.13.v20161014.jar;%APP_HOME%\lib\jetty-io-9.3.13.v20161014.jar;%APP_HOME%\lib\jetty-util-9.3.13.v20161014.jar;%APP_HOME%\lib\servo-core-0.9.2.jar;%APP_HOME%\lib\rxnetty-servo-0.4.9.jar;%APP_HOME%\lib\jackson-annotations-2.4.3.jar;%APP_HOME%\lib\jackson-databind-2.4.3.jar;%APP_HOME%\lib\guice-4.0.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\eureka-client-1.1.153.jar;%APP_HOME%\lib\netflix-eventbus-0.1.2.jar;%APP_HOME%\lib\xstream-1.4.2.jar;%APP_HOME%\lib\commons-math-2.2.jar;%APP_HOME%\lib\netflix-infix-0.1.2.jar;%APP_HOME%\lib\xmlpull-1.1.3.1.jar;%APP_HOME%\lib\xpp3_min-1.1.4c.jar;%APP_HOME%\lib\joda-time-2.3.jar;%APP_HOME%\lib\commons-jxpath-1.3.jar;%APP_HOME%\lib\antlr-runtime-3.4.jar;%APP_HOME%\lib\stringtemplate-3.2.1.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\rxnetty-0.4.12.jar;%APP_HOME%\lib\httpclient-4.3.1.jar;%APP_HOME%\lib\httpcore-4.3.jar;%APP_HOME%\lib\commons-logging-1.1.3.jar;%APP_HOME%\lib\jackson-mapper-asl-1.9.11.jar;%APP_HOME%\lib\guava-16.0.1.jar;%APP_HOME%\lib\javassist-3.18.2-GA.jar;%APP_HOME%\lib\annotations-2.0.1.jar;%APP_HOME%\lib\netty-resolver-4.1.5.Final.jar;%APP_HOME%\lib\jersey-core-1.18.1.jar;%APP_HOME%\lib\governator-annotations-1.9.3.jar;%APP_HOME%\lib\governator-core-1.9.3.jar;%APP_HOME%\lib\hibernate-validator-4.1.0.Final.jar;%APP_HOME%\lib\asm-5.0.4.jar;%APP_HOME%\lib\guice-grapher-4.0.jar;%APP_HOME%\lib\servo-internal-0.9.2.jar;%APP_HOME%\lib\jettison-1.2.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.0.Beta6.jar;%APP_HOME%\lib\jackson-core-asl-1.9.11.jar;%APP_HOME%\lib\netty-common-4.1.5.Final.jar;%APP_HOME%\lib\guice-multibindings-4.0.jar;%APP_HOME%\lib\guice-assistedinject-4.0.jar

@rem Execute microservice
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %MICROSERVICE_OPTS%  -classpath "%CLASSPATH%" com.packtpub.microservice.server.ServerRunner %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable MICROSERVICE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%MICROSERVICE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
