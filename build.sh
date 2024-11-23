#!/usr/bin/env bash
source common.sh

cd "$sdk_dir"
mvn package

cd "$script_dir"
rm -rf swim_wrk.jar
javac -cp $(print_sdk_jarpath) *.java
jar cvf swim_wrk.jar *.class
rm -rf *.class
