@echo off
set CLASSPATH=../lib/antlr-4.9.1-complete.jar;
java org.antlr.v4.Tool -visitor -package generated ../language/SatelliteBalise.g4
pause

move "*.java" "../src/generated"
del "*.interp"
del "*.tokens" 