# 3-sum.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random


# @include
def has_three_sum(A, t):
    A.sort()

    for a in A:
        # Finds if the sum of two numbers in A equals to t - a.
        if has_two_sum(A, t - a):
            return True
    return False

def has_two_sum(A, t):
    j = 0
    k = len(A) - 1

    while j <= k:
        if A[j] + A[k] == t:
            return True
        elif A[j] + A[k] < t:
            j += 1
        else:  # A[j] + A[k] > t.
            k -= 1
    return False
# @exclude


# n^3 solution.
def check_ans(A, t):
    for i in A:
        for j in A:
            for k in A:
                if i + j + k == t:
                    return True
    return False


def main():
    for _ in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 10000)
        T = random.randrange(n)

        A = [random.randint(-100000, 100000) for i in range(n)]

        print(has_three_sum(A, T))
        assert check_ans(A, T) == has_three_sum(A, T)


if __name__ == '__main__':
    main()
