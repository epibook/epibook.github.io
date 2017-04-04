import sys
import itertools
import random

from next_permutation import next_permutation


# @include
def permutations(A):
    result = []
    while True:
        result.append(A.copy())
        A = next_permutation(A)
        if not A:
            break
    return result
# @exclude


def small_test():
    A = [1, 2, 3]
    result = permutations(A)
    golden_result = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2],
                     [3, 2, 1]]
    assert result == golden_result


def main():
    small_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10)
    A = list(range(n))
    result = permutations(A)
    print('n =', n)
    for vec in result:
        print(*vec)


if __name__ == '__main__':
    main()
