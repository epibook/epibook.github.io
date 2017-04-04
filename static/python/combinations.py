import sys
import random


# @include
def combinations(n, k):
    def directed_combinations(offset, partial_combination):
        if len(partial_combination) == k:
            result.append(list(partial_combination))
            return

        # Generate remaining combinations over {offset, ..., n - 1} of size
        # num_remaining.
        num_remaining = k - len(partial_combination)
        i = offset
        while i <= n and num_remaining <= n - i + 1:
            directed_combinations(i + 1, partial_combination + [i])
            i += 1

    result = []
    directed_combinations(1, [])
    return result
# @exclude


def combinations_pythonic(n, k):
    res = [[]]
    for _ in range(k):
        res = [[i] + c for c in res for i in range(1, c[0] if c else n + 1)]
    return res


def small_test():
    assert combinations(4, 2) == [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4],
                                  [3, 4]]
    assert combinations_pythonic(4, 2) == [[1, 2], [1, 3], [2, 3], [1, 4],
                                           [2, 4], [3, 4]]


def main():
    small_test()
    if len(sys.argv) == 3:
        n = int(sys.argv[1])
        k = int(sys.argv[2])
    else:
        n = random.randint(1, 10)
        k = random.randint(0, n)
    result = combinations(n, k)
    print('n = ', n, ', k = ', k, sep='')
    for vec in result:
        print(*vec)


if __name__ == '__main__':
    main()
