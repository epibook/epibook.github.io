import sys
import random


# @include
def rearrange(A):
    for i in range(len(A)):
        A[i:i + 2] = sorted(A[i:i + 2], reverse=i % 2)
# @exclude


def check_answer(A):
    for i in range(len(A)):
        if i % 2:
            assert A[i] >= A[i - 1]
            if i + 1 < len(A):
                assert A[i] >= A[i + 1]
        else:
            if i > 0:
                assert A[i - 1] >= A[i]
            if i + 1 < len(A):
                assert A[i + 1] >= A[i]


def main():
    for _ in range(10000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        A = [random.randint(-n, n) for _ in range(n)]
        rearrange(A)
        check_answer(A)


if __name__ == '__main__':
    main()
