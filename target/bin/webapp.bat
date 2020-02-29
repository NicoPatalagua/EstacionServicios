@REM ----------------------------------------------------------------------------
@REM Copyright 2001-2004 The Apache Software Foundation.
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM ----------------------------------------------------------------------------
@REM

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\glassfish\extras\glassfish-embedded-web\3.1.1\glassfish-embedded-web-3.1.1.jar;"%REPO%"\com\sun\jersey\jersey-server\1.8\jersey-server-1.8.jar;"%REPO%"\asm\asm\3.1\asm-3.1.jar;"%REPO%"\com\sun\jersey\jersey-core\1.8\jersey-core-1.8.jar;"%REPO%"\org\web3j\core\3.4.0\core-3.4.0.jar;"%REPO%"\org\web3j\crypto\3.4.0\crypto-3.4.0.jar;"%REPO%"\org\web3j\rlp\3.4.0\rlp-3.4.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.8.5\jackson-databind-2.8.5.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.8.0\jackson-annotations-2.8.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.8.5\jackson-core-2.8.5.jar;"%REPO%"\org\web3j\tuples\3.4.0\tuples-3.4.0.jar;"%REPO%"\com\github\jnr\jnr-unixsocket\0.15\jnr-unixsocket-0.15.jar;"%REPO%"\com\github\jnr\jnr-ffi\2.1.2\jnr-ffi-2.1.2.jar;"%REPO%"\com\github\jnr\jffi\1.2.14\jffi-1.2.14.jar;"%REPO%"\com\github\jnr\jffi\1.2.14\jffi-1.2.14-native.jar;"%REPO%"\org\ow2\asm\asm\5.0.3\asm-5.0.3.jar;"%REPO%"\org\ow2\asm\asm-commons\5.0.3\asm-commons-5.0.3.jar;"%REPO%"\org\ow2\asm\asm-analysis\5.0.3\asm-analysis-5.0.3.jar;"%REPO%"\org\ow2\asm\asm-tree\5.0.3\asm-tree-5.0.3.jar;"%REPO%"\org\ow2\asm\asm-util\5.0.3\asm-util-5.0.3.jar;"%REPO%"\com\github\jnr\jnr-x86asm\1.0.2\jnr-x86asm-1.0.2.jar;"%REPO%"\com\github\jnr\jnr-constants\0.9.6\jnr-constants-0.9.6.jar;"%REPO%"\com\github\jnr\jnr-enxio\0.14\jnr-enxio-0.14.jar;"%REPO%"\com\github\jnr\jnr-posix\3.0.33\jnr-posix-3.0.33.jar;"%REPO%"\com\squareup\okhttp3\okhttp\3.8.1\okhttp-3.8.1.jar;"%REPO%"\com\squareup\okio\okio\1.13.0\okio-1.13.0.jar;"%REPO%"\com\squareup\okhttp3\logging-interceptor\3.8.1\logging-interceptor-3.8.1.jar;"%REPO%"\io\reactivex\rxjava\1.2.4\rxjava-1.2.4.jar;"%REPO%"\org\web3j\abi\3.4.0\abi-3.4.0.jar;"%REPO%"\org\web3j\utils\3.4.0\utils-3.4.0.jar;"%REPO%"\org\bouncycastle\bcprov-jdk15on\1.54\bcprov-jdk15on-1.54.jar;"%REPO%"\org\slf4j\slf4j-simple\1.7.5\slf4j-simple-1.7.5.jar;"%REPO%"\org\slf4j\slf4j-api\1.7.5\slf4j-api-1.7.5.jar;"%REPO%"\com\tikal\jee\sample\Gasolinera\1.0-SNAPSHOT\Gasolinera-1.0-SNAPSHOT.jar
set EXTRA_JVM_ARGUMENTS=
goto endInit

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% %EXTRA_JVM_ARGUMENTS% -classpath %CLASSPATH_PREFIX%;%CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dbasedir="%BASEDIR%" MainGlassfish %CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=1

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@endlocal

:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
