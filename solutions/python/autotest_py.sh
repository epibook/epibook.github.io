#!/bin/sh
for f in ./*\.py
do
  echo "Testing $f..."
  python3.6 $f
  if [ "$?" != "0" ]; then
    echo "Execution error on $f" 1>&2
    exit 1
  fi
done
