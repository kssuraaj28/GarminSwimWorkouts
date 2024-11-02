#!/usr/bin/env bash
set -e

script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd "$script_dir"

find . -name "*.java" -exec javac -cp fit.jar {} +
# TODO: This is extremely braindead...
mv example_workouts/*.class .
java -cp .:fit.jar Example

watch_dir=${watch_dir:-"/Volumes/GARMIN/GARMIN/Workouts/"}

if [ -d "$watch_dir" ]; then
    echo "Directory exists! Copying fit files"
    echo *.fit
    cp *.fit "$watch_dir"
else
    echo "Watch directory does not exist!"
fi

