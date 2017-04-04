import sys
import random


# @include
def merge_two_sorted_arrays(A, m, B, n):
    a, b, write_idx = m - 1, n - 1, m + n - 1
    while a >= 0 and b >= 0:
        if A[a] > B[b]:
            A[write_idx] = A[a]
            a -= 1
        else:
            A[write_idx] = B[b]
            b -= 1
        write_idx -= 1
    while b >= 0:
        A[write_idx] = B[b]
        write_idx, b = write_idx - 1, b - 1
# @exclude


def main():
    for _ in range(1000):
        if len(sys.argv) == 3:
            m = int(sys.argv[1])
            n = int(sys.argv[2])
        else:
            m = random.randint(0, 10000)
            n = random.randint(0, 10000)
        A = sorted(random.randint(-(m + n), m + n)
                   for _ in range(m)) + [None] * n
        B = sorted(random.randint(-(m + n), m + n) for _ in range(n))
        merge_two_sorted_arrays(A, m, B, n)
        assert all(A[i - 1] <= A[i] for i in range(1, len(A)))


if __name__ == '__main__':
    main()
