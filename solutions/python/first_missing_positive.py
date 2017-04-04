import sys
import random


def check_ans(A):
    A = sorted(A)
    target = 1
    for a in A:
        if a > 0:
            if a == target:
                target += 1
            elif a > target:
                return target
    return target


# @include
def find_first_missing_positive(A):
    # Record which values are present by writing A[i] to index A[i] - 1 if
    # A[i] is between 1 and len(A), inclusive. We save the value at index A[i]
    # - 1 by swapping it with the entry at i. If A[i] is negative or greater
    # than n, we just advance i.
    for i in range(len(A)):
        while 1 <= A[i] <= len(A) and A[i] != A[A[i] - 1]:
            A[A[i] - 1], A[i] = A[i], A[A[i] - 1]

    # Second pass through A to search for the first index i such that A[i] !=
    # i+1, indicating that i + 1 is absent. If all numbers between 1 and
    # len(A) are present, the smallest missing positive is len(A) + 1.
    return next((i + 1 for i, a in enumerate(A) if a != i + 1), len(A) + 1)
# @exclude


def main():
    for _ in range(1000):
        n = int(
            sys.argv[1]) if len(
            sys.argv) == 2 else random.randint(
            0,
            500000)
        A = [random.randint(0, n) for _ in range(n)]
        print('n =', n)
        # print(*A)
        #print(find_first_missing_positive(A), check_ans(A))
        assert find_first_missing_positive(A) == check_ans(A)


if __name__ == '__main__':
    main()
