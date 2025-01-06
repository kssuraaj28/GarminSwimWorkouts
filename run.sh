#!/usr/bin/env bash
set -e
script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
source "$script_dir"/common.sh


java_file_input=$(realpath $1)
fit_name=$2 
runner_log=${3:-/dev/stdout}
touch "$runner_log"
runner_log=$(realpath "$runner_log")

cd "$script_dir"
source common.sh

wrk_dirname=$(dirname $java_file_input)
wrk_java=$(basename $java_file_input)
wrk="${wrk_java%.java}"
wrk_class=$wrk.class
generated_fitfile=${fit_name}_workout.fit


echo "Compiling $wrk_java to $wrk_class..."
javac -cp "swim_wrk.jar" "$java_file_input"


echo "Compiling $wrk_class to $fitname..."
java -cp ".:$wrk_dirname:swim_wrk.jar:$(print_sdk_jarpath)" \
                 Runner "$wrk" "$fit_name"  > $runner_log


echo "Moving $generated_fitfile to $wrk_dirname"
mv "$generated_fitfile" "$wrk_dirname"
