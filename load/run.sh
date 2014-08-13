#!/bin/bash
dir=$(dirname $0)
java -jar ${dir}/target/*-jar-with-dependencies.jar $@
