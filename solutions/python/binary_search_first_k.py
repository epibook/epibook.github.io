# Binary_search_first_k.cpp 69dab7578339012270872ba200cfff02f59ce894
import sys
import random
import bisect


# @include
def search_first_of_k(A, k):
    left = 0
    right = len(A) - 1
    result = -1
    # [left : right] is the candidate set.
    while left <= right:
        mid = left + ((right - left) // 2)
        if A[mid] > k:
            right = mid - 1
        elif A[mid] == k:
            result = mid
            right = mid - 1  # Nothing to the right of mid can be solution.
        else:  # A[mid] < k.
            left = mid + 1
    return result
# @exclude


# Pythonic solution
def search_first_of_k_pythonic(A, k):
    i = bisect.bisect_left(A, k)
    if i < len(A) and A[i] == k:
        return i
    return -1


def main():
    for _ in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 100000)
        k = random.randrange(n)
        A = sorted(random.randrange(n) for i in range(n))
        ans = search_first_of_k(A, k)
        print('k =', k, 'locates at', ans)
        if ans != -1:
            print('A[k] =', A[ans])
        try:
            it = A.index(k)
        except ValueError:
            it = -1
        assert it == ans
        assert it == search_first_of_k_pythonic(A, k)

if __name__ == '__main__':
    main()
