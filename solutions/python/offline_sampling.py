import sys
import random


# @include
def random_sampling(k, A):
    for i in range(k):
        # Generate a random index in [i, len(A) - 1].
        r = random.randint(i, len(A) - 1)
        A[i], A[r] = A[r], A[i]
# @exclude


# Pythonic solution
def random_sampling_pythonic(k, A):
    A[:] = random.sample(A, k)


def main():
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
        k = random.randint(1, n)
    elif len(sys.argv) == 3:
        n = int(sys.argv[1])
        k = int(sys.argv[2])
    else:
        n = random.randint(1, 10)
        k = random.randint(1, n)

    A = list(range(n))
    print(n, k)
    random_sampling(k, A)
    print(*A)
    A = list(range(n))
    random_sampling_pythonic(k, A)
    print(*A)


if __name__ == '__main__':
    main()
