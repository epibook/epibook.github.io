import sys
import random
import rotate_array_permutation


# @include
def rotate_array(i, A):
    i %= len(A)

    def reverse(begin, end):
        while begin < end:
            A[begin], A[end] = A[end], A[begin]
            begin, end = begin + 1, end - 1

    reverse(0, len(A) - 1)
    reverse(0, i - 1)
    reverse(i, len(A) - 1)


# Although the following function is very natural way to rotate an array,
# its use of sublists leads to copy from original list, and therefore
# linear space complexity.
def rotate_array_naive(i, A):
    i %= len(A)
    A[:] = A[::-1]  # reverse whole list
    A[:i] = A[:i][::-1]  # reverse A[:i] part
    A[i:] = A[i:][::-1]  # reverse A[i:] part
# @exclude


def check_answer(A, i, rotated):
    assert len(A) == len(rotated)
    assert all(rotated[(idx + i) % len(rotated)] == A[idx]
               for idx in range(len(A)))


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        A = [random.randint(0, n) for _ in range(n)]
        i = random.randrange(n)
        B = A.copy()
        rotate_array(i, B)
        check_answer(A, i, B)
        C = A.copy()
        rotate_array_permutation.rotate_array(i, C)
        check_answer(A, i, C)


if __name__ == '__main__':
    main()
