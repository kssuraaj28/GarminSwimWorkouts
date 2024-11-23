#!/usr/bin/env bash
java_file_input=$(realpath $1)
fit_name=$2 

source common.sh
cd "$script_dir"

wrk_dirname=$(dirname $java_file_input)
wrk_java=$(basename $java_file_input)
wrk="${wrk_java%.java}"
wrk_class=$wrk.class
generated_fitfile=${fit_name}_workout.fit


echo "Compiling $java_file_input to $wrk_class_file..."
javac -cp "swim_wrk.jar" "$java_file_input"


echo "Compiling to .fit file"
java -cp ".:$wrk_dirname:swim_wrk.jar:$(print_sdk_jarpath)" \
                 Runner "$wrk" "$fit_name" 


echo "Moving $generated_fitfile to $wrk_dirname"
mv "$generated_fitfile" "$wrk_dirname"
