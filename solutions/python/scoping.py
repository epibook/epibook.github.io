# @include
x, y, z = 'global-x', 'global-y', 'global-z'

def basic_scoping():
    print(x)  # global-x
    y = 'local-y'
    global z
    z = 'local-z'

basic_scoping()
print(x, y, z)  # global-x global-y local-z

def inner_outer_scoping():
    def inner1():
        print(x)  # outer-x

    def inner2():
        x = 'inner2-x'
        print(x)  # inner2-x

    def inner3():
        nonlocal x
        x = 'inner3-x'
        print(x)  # inner3-x

    x = "outer-x"
    inner1(), inner2(), inner3()
    print(x)  # inner3-x

inner_outer_scoping()
print(x, y, z)  # global-x global-y local-z

def outer_scope_error():
    def inner():
        try:
            x = x + 321
        except NameError:
            print('Error: x is local, and so x + 1 is not defined yet')

    x = 123
    inner()

outer_scope_error()  # prints 'Error: ...'

def outer_scope_array_no_error():
    def inner():
        x[0] = -x[0]  # x[0] isn't a variable, it's resolved from outer x.

    x = [314]
    inner()
    print(x[0])  # -314

outer_scope_array_no_error()
# @exclude
