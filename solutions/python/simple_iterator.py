import random


# @include
# Iterable object, implements __iter__(), __next__().
class RandomIncrement():
    def __init__(self, limit):
        self._offset = 0.0
        self._limit = limit

    def __iter__(self):
        return self

    def __next__(self):
        self._offset += random.random()
        if (self._offset > self._limit):
            raise StopIteration()
        return self._offset

    # Call this to change the stop condition. It's safe to interleave this with
    # usage of the iterator.
    def increment_limit(self, increment_amount):
        self._limit += increment_amount
# @exclude

ri = RandomIncrement(10.0)
for i in ri:
    print(i)
print("Done first iteration")
ri.increment_limit(10.0)
for i in ri:
    print(i)
