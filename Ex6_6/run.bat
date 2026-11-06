@echo off
setlocal
cd /d "%~dp0"
if not exist out mkdir out
javac -encoding UTF-8 -d out src\ex66\*.java
if errorlevel 1 exit /b 1
java -cp out ex66.Main
if errorlevel 1 exit /b 1
java -cp out ex66.Tests
exit /b %errorlevel%
