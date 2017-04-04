lastModifiedcppFile = $(shell ls -rt *\.c* | tail -1)
clang: $(lastModifiedcppFile)
	$(MAKE) compile && ./a.out

compile: $(lastModifiedcppFile)
	clang++ -Weverything -Wno-c++98-compat -Wno-missing-prototypes $(lastModifiedcppFile) -stdlib=libc++ -std=c++14 -O3

lint: $(lastModifiedcppFile)
	python cpplint.py $(lastModifiedcppFile) 

cpp: $(lastModifiedcppFile)
	g++ -Wall -std=c++11 $(lastModifiedcppFile) -lm

lastModifiedjavaFile = $(shell ls -rt *.java | tail -1)
java: $(lastModifiedjavaFile)
	javac $(lastModifiedjavaFile)
