#!/usr/bin/env bash
tests=""
if [ `echo $1 | tr '[:upper:]' '[:lower:]'` == "all" ] && [ $# -eq 1 ]
then
    sbt -v "testOnly problems.*"
else
    for i in "$@"; do
        tests="$tests""problems.$i""Tests "
    done
    sbt -v "testOnly $tests"
fi