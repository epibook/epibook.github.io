# Number_ways_obstacles.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random


# @include
# Given the dimensions of A, n and m, and B, return the number of ways
# from A[0][0] to A[n - 1][m - 1] considering obstacles.
def number_of_ways_with_obstacles(n, m, B):
    A = [[0] * m for i in range(n)]

    if B[0][0]:  # No way to start from (0, 0) if B[0][0] == True.
        return 0

    A[0][0] = 1
    for i in range(n):
        for j in range(m):
            if not B[i][j]:
                A[i][j] += (0 if i < 1 else A[i - 1][j]) + (0 if j < 1 else A[i][j - 1])
# @exclude
            print('%5d' % A[i][j], end=' ')
        print()
# @include
    return A[-1][-1]
# @exclude


def main():
    if len(sys.argv) == 3:
        n = int(sys.argv[1])
        m = int(sys.argv[2])
    else:
        n = random.randint(1, 10)
        m = random.randint(1, 10)

    B = []
    for i in range(n):
        B.append([random.randint(0, 4) == 0 for j in range(m)])
        
    for i in range(n):
        for j in range(m):
            print('%5d' % B[i][j], end=' ')
        print()
    print()

    print(number_of_ways_with_obstacles(n, m, B))


if __name__ == '__main__':
    main()
