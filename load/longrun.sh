#!/bin/bash
dir=$(dirname $0)

jps | grep project-place-load | awk '{print $1}' | xargs -i{} kill {} 2>&1 /dev/null
nohup java -jar ${dir}/target/*-jar-with-dependencies.jar $@ &
