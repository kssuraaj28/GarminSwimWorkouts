set -e
script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
sdk_dir=${sdk_dir:-"$script_dir/fit-java-sdk"}

print_sdk_jarpath() {
    ls $sdk_dir/target/fit-??.???.?.jar
}
