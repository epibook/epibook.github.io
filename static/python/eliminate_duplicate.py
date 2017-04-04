import itertools
import sys
import random


# @include
class Name:

    def __init__(self, first_name, last_name):
        self.first_name, self.last_name = first_name, last_name

    def __eq__(self, other):
        return self.first_name == other.first_name

    def __lt__(self, other):
        return (self.first_name < other.first_name
                if self.first_name != other.first_name else
                self.last_name < other.last_name)
# @exclude

    def __repr__(self):
        return '%s %s' % (self.first_name, self.last_name)


# @include


def eliminate_duplicate(A):
    A.sort()  # Makes identical elements become neighbors.
    write_idx = 1
    for cand in A[1:]:
        if cand != A[write_idx - 1]:
            A[write_idx] = cand
            write_idx += 1
    del A[write_idx:]
# @exclude


def eliminate_duplicate_pythonic(A):
    A.sort()
    write_idx = 0
    for cand, _ in itertools.groupby(A):
        A[write_idx] = cand
        write_idx += 1
    del A[write_idx:]


def small_test():
    A = [Name('Foo', 'Bar'), Name('ABC', 'XYZ'), Name('Foo', 'Widget')]
    eliminate_duplicate(A)
    assert len(A) == 2


def main():
    small_test()
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(0, 1000)
        A = [
            Name(str(random.randrange(n)), str(random.randrange(n)))
            for _ in range(n)
        ]
        A_copy = A.copy()
        eliminate_duplicate(A)
        eliminate_duplicate_pythonic(A_copy)
        assert all(a != b for a, b in zip(A, A[1:]))
        assert all(a != b for a, b in zip(A_copy, A_copy[1:]))
        assert A == A_copy


if __name__ == '__main__':
    main()
