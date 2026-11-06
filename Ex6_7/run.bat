@echo off
setlocal
cd /d "%~dp0"
if not exist out mkdir out
javac -encoding UTF-8 -d out src\ex67\*.java
if errorlevel 1 exit /b 1
java -cp out ex67.Main
if errorlevel 1 exit /b 1
java -cp out ex67.Tests
exit /b %errorlevel%
