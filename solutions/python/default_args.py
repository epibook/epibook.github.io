# @include
def foo(x=[]):
    x.append(1)
    return x

res = foo()
print(res)
res = foo()
print(res)
# @exclude
