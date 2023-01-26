#!/usr/bin/env bash
tests=""
if [ `echo $1 | tr '[:upper:]' '[:lower:]'` == "all" ] && [ $# -eq 1 ]
then
    sbt -v "testOnly solutions.*"
else
    for i in "$@"; do
        tests="$tests""solutions.$i""Tests "
    done
    sbt -v "testOnly $tests"
fi