# @include
def foo(u, v, *args):
    print('u,v = ' + str((u, v)))
    for i in range(len(args)):
        print('args {0} = {1}'.format(str(i), str(args[i])))


foo(1, 'euler', 2.71, [6, 28])
'''
Alternative call.
With the *, foo gets 4 arguments, not 3. Prints
u,v = (1, 'euler')
args 0 = 2.71
args 1 = [6, 28]
'''
args = (2.71, [6, 28])
foo(1, 'euler', *args)
'''
Without the *, foo gets 3 arguments, not 4. Prints
u,v = (1, 'euler')
args 0 = (2.71, [6, 28])
'''
foo(1, 'euler', args)
# @exclude
