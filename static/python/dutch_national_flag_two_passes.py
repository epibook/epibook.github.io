import sys
import random

# @include
RED, WHITE, BLUE = range(3)


def dutch_flag_partition(pivot_index, A):
    pivot = A[pivot_index]
    # First pass: group elements smaller than pivot.
    smaller = 0
    for i in range(len(A)):
        if A[i] < pivot:
            A[i], A[smaller] = A[smaller], A[i]
            smaller += 1
    # Second pass: group elements larger than pivot.
    larger = len(A) - 1
    for i in reversed(range(len(A))):
        if A[i] < pivot:
            break
        elif A[i] > pivot:
            A[i], A[larger] = A[larger], A[i]
            larger -= 1
# @exclude


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 100)
        A = [random.randint(0, 2) for _ in range(n)]
        pivot_index = random.randrange(n)
        pivot = A[pivot_index]
        dutch_flag_partition(pivot_index, A)
        i = 0
        while i < len(A) and A[i] < pivot:
            print(A[i], end=' ')
            i += 1
        while i < len(A) and A[i] == pivot:
            print(A[i], end=' ')
            i += 1
        while i < len(A) and A[i] > pivot:
            print(A[i], end=' ')
            i += 1
        print()
        assert i == len(A)


if __name__ == '__main__':
    main()
