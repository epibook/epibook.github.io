import time


# @include
def time_function(f):
    def wrapper(*args, **kwargs):
        begin = time.time()
        result = f(*args, **kwargs)
        end = time.time()
        print("Function call with arguments {all_args} took ".format(
            all_args="\t".join((str(args), str(kwargs)))) + str(end - begin) +
              " seconds to execute.")
        return result

    return wrapper


@time_function
def foo():
    print("I am foo()")


@time_function
def ackermann(m, n):
    if m == 0:
        return n + 1
    elif n == 0:
        return ackermann(m - 1, 1)
    else:
        return ackermann(m - 1, ackermann(m, n - 1))


@time_function
def bar(*args, **kwargs):
    print(sum(args) * sum(kwargs.values()))
# @exclude

foo()
ackermann(3, 4)
bar(1, 2, 3, x=4)
