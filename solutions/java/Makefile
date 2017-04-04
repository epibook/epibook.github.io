lastModifiedJavaFile = $(shell ls -rt *\.java | tail -1)
lastModifiedJavaClass = $(shell echo $(lastModifiedJavaFile) | cut -f 1 -d '.')
compile: $(lastModifiedJavaFile)
	 javac $(lastModifiedJavaFile) && java $(lastModifiedJavaClass)

format:
	./format.sh
