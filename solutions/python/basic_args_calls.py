from basic_args import foo

# @include
foo(1, 2, 3)
foo(x=1, y=2, z=3)
foo(1, y=2, z=3)
foo(1, z=3, y=3)
foo(z=3, y=3, x=1)
# This is a syntax error, keyword arguments must follow positional arguments.
foo(x=1, 2, z=3)
# This is a syntax error, a variable cannot be assigned twice.
foo(1, x=2, z=3, y=4)
# @exclude
