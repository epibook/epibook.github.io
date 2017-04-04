import itertools
import sys
import random


# @include
def has_two_sum(A, t):
    i, j = 0, len(A) - 1

    while i <= j:
        if A[i] + A[j] == t:
            return True
        elif A[i] + A[j] < t:
            i += 1
        else:  # A[i] + A[j] > t.
            j -= 1
    return False
# @exclude


# n^2 solution.
def check_ans(A, t):
    return any(i + j == t for i, j in itertools.combinations(A, 2))


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        T = random.randrange(n)
        A = sorted([random.randint(-100000, 100000) for _ in range(n)])
        print(has_two_sum(A, T))
        assert check_ans(A, T) == has_two_sum(A, T)


if __name__ == '__main__':
    main()
