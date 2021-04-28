@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  pronto_restful_api startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and PRONTO_RESTFUL_API_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\pronto_restful_api.jar;%APP_HOME%\lib\javax.servlet-api-4.0.1.jar;%APP_HOME%\lib\mysql-connector-java-8.0.21.jar;%APP_HOME%\lib\javax.mail-1.6.2.jar;%APP_HOME%\lib\jaxrs-ri-2.33.jar;%APP_HOME%\lib\jersey-container-servlet-2.33.jar;%APP_HOME%\lib\jaxb-runtime-2.4.0-b180830.0438.jar;%APP_HOME%\lib\jaxb-api-2.4.0-b180830.0359.jar;%APP_HOME%\lib\jersey-container-grizzly2-http-2.33.jar;%APP_HOME%\lib\slf4j-simple-2.0.0-alpha1.jar;%APP_HOME%\lib\slf4j-api-2.0.0-alpha1.jar;%APP_HOME%\lib\junit-jupiter-5.4.2.jar;%APP_HOME%\lib\junit-4.12.jar;%APP_HOME%\lib\mockito-android-3.6.0.jar;%APP_HOME%\lib\protobuf-java-3.11.4.jar;%APP_HOME%\lib\activation-1.1.jar;%APP_HOME%\lib\jersey-hk2-2.33.jar;%APP_HOME%\lib\jersey-media-jaxb-2.33.jar;%APP_HOME%\lib\jersey-media-json-binding-2.33.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.33.jar;%APP_HOME%\lib\jersey-media-sse-2.33.jar;%APP_HOME%\lib\jersey-server-2.33.jar;%APP_HOME%\lib\jersey-client-2.33.jar;%APP_HOME%\lib\jersey-common-2.33.jar;%APP_HOME%\lib\jakarta.xml.bind-api-2.3.3.jar;%APP_HOME%\lib\jakarta.ws.rs-api-2.1.6.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\txw2-2.4.0-b180830.0438.jar;%APP_HOME%\lib\istack-commons-runtime-3.0.7.jar;%APP_HOME%\lib\stax-ex-1.8.jar;%APP_HOME%\lib\FastInfoset-1.2.15.jar;%APP_HOME%\lib\hk2-locator-2.6.1.jar;%APP_HOME%\lib\hk2-api-2.6.1.jar;%APP_HOME%\lib\hk2-utils-2.6.1.jar;%APP_HOME%\lib\jakarta.inject-2.6.1.jar;%APP_HOME%\lib\grizzly-http-server-2.4.4.jar;%APP_HOME%\lib\junit-jupiter-params-5.4.2.jar;%APP_HOME%\lib\junit-jupiter-engine-5.4.2.jar;%APP_HOME%\lib\junit-jupiter-api-5.4.2.jar;%APP_HOME%\lib\hamcrest-core-1.3.jar;%APP_HOME%\lib\mockito-core-3.6.0.jar;%APP_HOME%\lib\byte-buddy-android-1.10.15.jar;%APP_HOME%\lib\objenesis-2.6.jar;%APP_HOME%\lib\javassist-3.25.0-GA.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.5.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.3.jar;%APP_HOME%\lib\yasson-1.0.6.jar;%APP_HOME%\lib\jakarta.json-1.1.6.jar;%APP_HOME%\lib\jakarta.json-1.1.6-module.jar;%APP_HOME%\lib\jakarta.json.bind-api-1.0.2.jar;%APP_HOME%\lib\jakarta.json-api-1.1.6.jar;%APP_HOME%\lib\jakarta.validation-api-2.0.2.jar;%APP_HOME%\lib\jakarta.activation-api-1.2.2.jar;%APP_HOME%\lib\grizzly-http-2.4.4.jar;%APP_HOME%\lib\junit-platform-engine-1.4.2.jar;%APP_HOME%\lib\junit-platform-commons-1.4.2.jar;%APP_HOME%\lib\apiguardian-api-1.0.0.jar;%APP_HOME%\lib\opentest4j-1.1.1.jar;%APP_HOME%\lib\byte-buddy-1.10.15.jar;%APP_HOME%\lib\byte-buddy-agent-1.10.15.jar;%APP_HOME%\lib\dalvik-dx-9.0.0_r3.jar;%APP_HOME%\lib\aopalliance-repackaged-2.6.1.jar;%APP_HOME%\lib\grizzly-framework-2.4.4.jar


@rem Execute pronto_restful_api
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %PRONTO_RESTFUL_API_OPTS%  -classpath "%CLASSPATH%" org.demo.Main %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable PRONTO_RESTFUL_API_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%PRONTO_RESTFUL_API_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
