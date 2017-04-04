import sys
import random


# @include
def find_duplicate_missing(A):
    running_sum = square_sum = 0
    for i, a in enumerate(A):
        running_sum += i - a
        square_sum += i**2 - a**2
    return (square_sum // running_sum - running_sum) // 2, (square_sum //
                                                            running_sum + running_sum) // 2
# @exclude


def main():
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
        print(ans[0], ans[1])
        assert ans[0] == dup and ans[1] == missing


if __name__ == '__main__':
    main()
