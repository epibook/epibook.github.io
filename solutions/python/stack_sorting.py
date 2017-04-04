# Stack_sorting.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random


# @include
def sort(S):
    if S:
        e = S.pop()
        sort(S)
        insert(e, S)


def insert(e, S):
    if not S or S[-1] <= e:
        S.append(e)
    else:
        f = S.pop()
        insert(e, S)
        S.append(f)


# @exclude


def main():
    for _ in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, sys.getrecursionlimit() - 3)
        S = [random.randint(0, 999999) for i in range(n)]
        sort(S)
        pre = float('inf')
        while S:
            assert pre >= S[-1]
            print(S[-1])
            pre = S.pop()


if __name__ == '__main__':
    main()
