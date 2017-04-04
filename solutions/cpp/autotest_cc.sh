#!/bin/sh
for f in ./*\.cc
do
  echo "Compiling $f..."
  touch $f
  make
  if [ "$?" = "0" ]; then
    ./a.out
    if [ "$?" != "0" ]; then
      echo "Execution error on $f" 1>&2
      exit 1
    fi
  else
    echo "Compile error on $f" 1>&2
    exit 1
  fi
done
