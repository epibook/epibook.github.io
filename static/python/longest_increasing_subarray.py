import collections
import sys
import random

# @include
Subarray = collections.namedtuple('Subarray', ('start', 'end'))


def find_longest_increasing_subarray(A):
    result = Subarray(0, 0)
    i, max_length = 0, 1
    while i < len(A) - max_length:
        # Backward check and skip if A[j] >= A[j + 1].
        for j in range(i + max_length, i, -1):
            if A[j - 1] >= A[j]:
                i = j
                break
        else:  # Forward check if it is not skippable (the loop ended normally)
            i += max_length
            while i < len(A) and A[i - 1] < A[i]:
                i, max_length = i + 1, max_length + 1
            result = Subarray(i - max_length, i - 1)
    return result
# @exclude


def simple_test():
    result = find_longest_increasing_subarray([-1, -1])
    assert result.start == 0 and result.end == 0
    result = find_longest_increasing_subarray([1, 2])
    assert result.start == 0 and result.end == 1


def main():
    simple_test()
    for _ in range(1000):
        if len(sys.argv) > 2:
            A = list(map(int, sys.argv[1:]))
        else:
            n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(
                1, 1000000)
            A = [random.randint(-(n - 1), n - 1) for _ in range(n)]
        result = find_longest_increasing_subarray(A)
        print(*result)
        max_len = 1
        cur_len = 1
        for i in range(1, len(A)):
            if A[i] > A[i - 1]:
                cur_len += 1
                max_len = max(max_len, cur_len)
            else:
                cur_len = 1
        assert max_len == result.end - result.start + 1


if __name__ == '__main__':
    main()
