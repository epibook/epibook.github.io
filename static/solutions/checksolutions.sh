#!/bin/bash

longRunningTests["AddOperators"]="1"

for javasource in *.java
do
  # string substitution syntax from 
  # http://tldp.org/LDP/abs/html/string-manipulation.html
  echo "Processing $javasource"
  javac ${javasource%.java}.java > /dev/null
  java -ea ${javasource%.java} > /dev/null
  if [ "$?" -ne "0" ]; then
    echo "TEST FAILURE $javasource"
    # exit -1;
  fi
done
