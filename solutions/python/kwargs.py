# @include
def foo(u, v, *args, **kwargs):
    print('u,v = ' + str((u, v)))
    for i in range(len(args)):
        print('args {0} = {1}'.format(str(i), str(args[i])))
    for (keyword, value) in kwargs.items():
        print('keyword,value =  ' + str((keyword, value)))


foo(1, 'euler', 2.71, [6, 28], name='cfg', rank=1)
# Alternate call
args = (2.71, [6, 28])
kwargs = {'name': 'cfg', 'rank': 1}
foo(1, 'euler', *args, **kwargs)
# @exclude
