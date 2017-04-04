import collections
# @include
c = collections.Counter(a=3, b=1)
d = collections.Counter(a=1, b=2)
# add two counters together:  c[x] + d[x], collections.Counter({'a': 4, 'b': 3})
c + d                       
# subtract (keeping only positive counts), collections.Counter({'a': 2})
c - d                       
# intersection:  min(c[x], d[x]), collections.Counter({'a': 1, 'b': 1})
c & d                       
# union:  max(c[x], d[x]), collections.Counter({'a': 3, 'b': 2})
c | d                       
# @exclude
