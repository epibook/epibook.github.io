import sys
import operator
import itertools
import random


# @include
class IndexPair:

    def __init__(self, index_1, index_2):
        self.index_1 = index_1
        self.index_2 = index_2
# @exclude

    def __repr__(self):
        return r'%d,%d' % (self.index_1, self.index_2)


# @include


def find_pair_sum_k(A, k):
    def find_pair_using_compare(A, k, comp):
        result = IndexPair(0, len(A) - 1)
        while result.index_1 < result.index_2 and comp(A[result.index_1], 0):
            result.index_1 += 1
        while result.index_1 < result.index_2 and comp(A[result.index_2], 0):
            result.index_2 -= 1

        while result.index_1 < result.index_2:
            if A[result.index_1] + A[result.index_2] == k:
                return result
            elif comp(A[result.index_1] + A[result.index_2], k):
                while True:
                    result.index_1 += 1
                    if result.index_1 >= result.index_2 or not comp(
                            A[result.index_1], 0):
                        break
            else:
                while True:
                    result.index_2 -= 1
                    if result.index_1 >= result.index_2 or not comp(
                            A[result.index_2], 0):
                        break
        return IndexPair(-1, -1)  # No answer.

    def find_positive_negative_pair(A, k):
        # result.index_1 for positive, and result.index_2 for negative.
        result = IndexPair(len(A) - 1, len(A) - 1)
        # Find the last positive or zero.
        while result.index_1 >= 0 and A[result.index_1] < 0:
            result.index_1 -= 1

        # Find the last negative.
        while result.index_2 >= 0 and A[result.index_2] >= 0:
            result.index_2 -= 1

        while result.index_1 >= 0 and result.index_2 >= 0:
            if A[result.index_1] + A[result.index_2] == k:
                return result
            elif A[result.index_1] + A[result.index_2] > k:
                while True:
                    result.index_1 -= 1
                    if result.index_1 < 0 or A[result.index_1] >= 0:
                        break
            else:  # A[result.index_1] + A[result.index_2] < k.
                while True:
                    result.index_2 -= 1
                    if result.index_2 < 0 or A[result.index_2] < 0:
                        break
        return IndexPair(-1, -1)  # No answer.

    result = find_positive_negative_pair(A, k)
    if result.index_1 == -1 and result.index_2 == -1:
        return find_pair_using_compare(A, k, operator.lt
                                       if k >= 0 else operator.ge)
    return result
# @exclude


def find_pair_sum_k_pythonic(A, k):
    # iterate array in increasing order (negative numbers backwards then
    # positive numbers forward)
    l_indices = itertools.chain(
        filter(lambda i: A[i] < 0, reversed(range(len(A)))),
        filter(lambda i: A[i] >= 0, range(len(A))))
    # iterate array in decreasing order (positive numbers backwards then
    # negative numbers forward)
    r_indices = itertools.chain(
        filter(lambda i: A[i] >= 0, reversed(range(len(A)))),
        filter(lambda i: A[i] < 0, range(len(A))))
    # enumerate left indices forward
    l_enum = enumerate(l_indices)
    # enumerate right indices backward
    r_enum = zip(reversed(range(len(A))), r_indices)

    l_pos, l = next(l_enum)
    r_pos, r = next(r_enum)
    while l_pos < r_pos:
        if A[l] + A[r] == k:
            return IndexPair(l, r)
        elif A[l] + A[r] < k:
            l_pos, l = next(l_enum)
        else:
            r_pos, r = next(r_enum)
    return IndexPair(-1, -1)


def simple_test():
    A = (0, 0, -1, 2, -3, -3)
    ans = find_pair_sum_k(A, 2)
    assert ans.index_1 != -1


def main():
    simple_test()
    for times in range(10000):
        n = int(sys.argv[1]) if len(sys.argv) >= 2 else random.randint(1, 10000)
        A = sorted((random.randint(-10000, 10000) for i in range(n)), key=abs)
        k = random.randint(-10000, 10000)
        ans = find_pair_sum_k(A, k)
        ans2 = find_pair_sum_k_pythonic(A, k)
        if ans.index_1 != -1 and ans.index_2 != -1:
            assert A[ans.index_1] + A[ans.index_2] == k
            assert A[ans2.index_1] + A[ans2.index_2] == k
            print('%d+%d=%d' % (A[ans.index_1], A[ans.index_2], k))
        else:
            assert ans2.index_1 == -1 and ans2.index_2 == -1
            A.sort()
            l = 0
            r = len(A) - 1
            found = False
            while l < r:
                if A[l] + A[r] == k:
                    print('%d+%d=%d' % (A[l], A[r], k))
                    found = True
                    break
                elif A[l] + A[r] < k:
                    l += 1
                else:
                    r -= 1
            print('no answer')
            assert not found


if __name__ == '__main__':
    main()
