# @include
def read_file_as_array_buggy(filename, default=[]):
    try:
        filehandle = open(filename)
        return filename.read().split()
    except Exception:
        return default


def read_file_as_array_works(filename, default=None):
    if default is None:
        default = []
    try:
        filehandle = open(filename)
        return filename.read().split()
    except Exception:
        return default


A = read_file_as_array_buggy("does_not_exist.txt")
A.append("first")
B = read_file_as_array_buggy("does_not_exist.txt")
B.append("second")
print(B)  # ['first', 'second']
A = read_file_as_array_works("does_not_exist.txt")
A.append("first")
B = read_file_as_array_works("does_not_exist.txt")
B.append("second")
print(B)  # ['second']
# @exclude
