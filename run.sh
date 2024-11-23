#!/usr/bin/env bash
wrk_java_file=$(realpath $1)
fit_name=$2 

source common.sh
cd "$script_dir"

wrk_class="${wrk_java_file%.java}"
wrk_class_file="$wrk_class".class


echo "Compiling $wrk_java_file to $wrk_class_file..."
javac -cp "swim_wrk.jar" "$wrk_java_file"

echo "Moving $wrk_class_file to current dir.." #I don't know why I need to do this..
mv "$wrk_class_file" .

echo "Compiling to .fit file"
java -cp ".:swim_wrk.jar:$(print_sdk_jarpath)" Runner "$(basename $wrk_class)" "$fit_name" #The classpath including . is supposed to help load runner
exit

#java -cp MyApp.jar your.package.MainClass
# jar tf yourfile.jar

# TODO: This is extremely braindead...
mv example_workouts/*.class .
java -cp .:fit.jar Runner

watch_dir=${watch_dir:-"/Volumes/GARMIN/GARMIN/Workouts/"}

if [ -d "$watch_dir" ]; then
    echo "Directory exists! Copying fit files"
    echo *.fit
    cp *.fit "$watch_dir"
    sync
else
    echo "Watch directory does not exist!"
fi

