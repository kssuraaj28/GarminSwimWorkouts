# This file is supposed to be sourced
# __variables are 'internal'
__this_src_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

# These definitions are 'external'
sdk_dir=${sdk_dir:-"$__this_src_dir/fit-java-sdk"}
print_sdk_jarpath() {
    ls $sdk_dir/target/fit-??.???.?.jar
}
