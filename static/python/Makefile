lastModifiedPyFile = $(shell ls -rt *\.py* | tail -1)
compile: $(lastModifiedPyFile)
	yapf -i $(lastModifiedPyFile) && python3.6 $(lastModifiedPyFile)

format:
	yapf -i *py

test:
	./autotest_py.sh
