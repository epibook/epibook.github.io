lastModifiedCppFile = $(shell ls -rt *\.c* | tail -1)
clang: $(lastModifiedCppFile)
	$(MAKE) compile && ./a.out

compile: $(lastModifiedCppFile)
	clang++ -Weverything -Wno-c++98-compat -Wno-missing-prototypes $(lastModifiedCppFile) -stdlib=libc++ -std=c++1z -O3

lint: $(lastModifiedCppFile)
	python cpplint.py $(lastModifiedCppFile) 

test:
	./autotest_cc.sh

format:
	./format.sh
