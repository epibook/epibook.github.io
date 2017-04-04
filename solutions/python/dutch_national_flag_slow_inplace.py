import sys
import random

# @include
RED, WHITE, BLUE = range(3)


def dutch_flag_partition(pivot_index, A):
    pivot = A[pivot_index]
    # First pass: group elements smaller than pivot.
    for i in range(len(A)):
        # Look for a smaller element.
        for j in range(i + 1, len(A)):
            if A[j] < pivot:
                A[i], A[j] = A[j], A[i]
                break
    # Second pass: group elements larger than pivot.
    for i in reversed(range(len(A))):
        if A[i] < pivot:
            break
        # Look for a larger element. Stop when we reach an element less than
        # pivot, since first pass has moved them to the start of A.
        for j in reversed(range(i)):
            if A[j] > pivot:
                A[i], A[j] = A[j], A[i]
                break
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
