#!/usr/bin/env bash
script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd "$script_dir"
source common.sh

cd "$sdk_dir"
mvn package

cd "$script_dir"
rm -rf swim_wrk.jar
javac -cp $(print_sdk_jarpath) *.java
jar cvf swim_wrk.jar *.class
jar tf swim_wrk.jar
rm -rf *.class
