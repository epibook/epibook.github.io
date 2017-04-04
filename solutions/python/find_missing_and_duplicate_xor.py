import collections
import functools
import sys
import random

# @include
DuplicateAndMissing = collections.namedtuple('DuplicateAndMissing',
                                             ('duplicate', 'missing'))


def find_duplicate_missing(A):
    # Compute the XOR of all numbers from 0 to |A| - 1 and all entries in A.
    miss_XOR_dup = functools.reduce(lambda v, i: v ^ i[0] ^ i[1],
                                    enumerate(A), 0)

    # We need to find a bit that's set to 1 in miss_XOR_dup. Such a bit must
    # exist if there is a single missing number and a single duplicated number
    # in A.
    #
    # The bit-fiddling assignment below sets all of bits in differ_bit
    # to 0 except for the least significant bit in miss_XOR_dup that's 1.
    differ_bit, miss_or_dup = miss_XOR_dup & (~(miss_XOR_dup - 1)), 0
    for i, a in enumerate(A):
        # Focus on entries and numbers in which the differ_bit-th bit is 1.
        if i & differ_bit:
            miss_or_dup ^= i
        if a & differ_bit:
            miss_or_dup ^= a

    # miss_or_dup is either the missing value or the duplicated entry.
    if any(a == miss_or_dup for a in A):
        # miss_or_dup is the duplicate.
        return DuplicateAndMissing(miss_or_dup, miss_or_dup ^ miss_XOR_dup)
    # miss_or_dup is the missing value.
    return DuplicateAndMissing(miss_or_dup ^ miss_XOR_dup, miss_or_dup)
# @exclude


def simple_test():
    A = [0, 1, 2, 4, 5, 6, 6]
    ans = find_duplicate_missing(A)
    assert ans.duplicate == 6 and ans.missing == 3

    A = [0, 0, 1]
    ans = find_duplicate_missing(A)
    assert ans.duplicate == 0 and ans.missing == 2

    A = [1, 3, 2, 5, 3, 4]
    ans = find_duplicate_missing(A)
    assert ans.duplicate == 3 and ans.missing == 0


def main():
    simple_test()
    for times in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(2, 10000)
        A = list(range(n))
        missing_idx = random.randrange(n)
        missing = A[missing_idx]
        dup_idx = random.randrange(n)
        while dup_idx == missing_idx:
            dup_idx = random.randrange(n)
        dup = A[dup_idx]
        A[missing_idx] = dup
        ans = find_duplicate_missing(A)
        print('times =', times)
        print(dup, missing)
        print(ans.duplicate, ans.missing)
        assert ans.duplicate == dup and ans.missing == missing


if __name__ == '__main__':
    main()
