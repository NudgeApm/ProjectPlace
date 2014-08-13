#!/bin/bash
dir=$(dirname $0)

jps | grep 'project-place-load' | awk '{print $1}' | xargs kill
rm ./nohup.out
nohup java -jar ${dir}/target/*-jar-with-dependencies.jar $@ &
