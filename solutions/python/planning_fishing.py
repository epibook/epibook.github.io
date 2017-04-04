import sys
import random


# @include
def maximize_fishing(A):
    for i in range(len(A)):
        for j in range(len(A[i])):
            A[i][j] += max(0 if i < 1 else A[i - 1][j], 0
                           if j < 1 else A[i][j - 1])
    return A[-1][-1]
# @exclude


def main():
    if len(sys.argv) == 3:
        n = int(sys.argv[1])
        m = int(sys.argv[2])
    else:
        n = random.randint(1, 100)
        m = random.randint(1, 100)
    A = [[random.randrange(1000) for j in range(m)] for i in range(n)]
    for a in A:
        print(*a)
    print(maximize_fishing(A))


if __name__ == '__main__':
    main()
