#!/bin/tcsh
foreach f (`ls *.cpp *.cc *.h`)
  echo $f
  cat $f | aspell --mode=ccpp --personal=./c++.aspell.en.pws list | sort | uniq -c
  # cat epi.tex | aspell --mode=tex --personal=./epi.aspell.en.pws list  | sort | uniq -c
end
# use something like this to examine report:
# cat solutions.spell_report | awk '{print $2}' | sort | uniq -c | sort -nr |less
