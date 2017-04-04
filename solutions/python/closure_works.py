# @include
def create_increment_function(x):
    return lambda y: y + x

increment_by_i = [create_increment_function(i) for i in range(10)]

print(increment_by_i[3](4))
# @exclude
