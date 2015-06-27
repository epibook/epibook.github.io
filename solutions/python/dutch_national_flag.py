# Dutch_national_flag.cc
import random
import sys

# @include
def dutch_flag_partition(pivot_index, A):
    pivot = A[pivot_index]
    smaller, equal, larger = 0, 0, len(A) - 1
    while equal <= larger:
        if A[equal] < pivot:
            A[smaller], A[equal] = A[equal], A[smaller]
            smaller, equal = smaller + 1, equal + 1
        elif A[equal] == pivot:
            equal += 1
        else:
            A[equal], A[larger] = A[larger], A[equal]
            larger -= 1

# @exclude
def is_dutch_flag_sorted(A, pivot):
    count_increase = 0
    pivot_index = A.index(pivot)
    for smaller in A[:pivot_index]:
        if smaller > pivot:
            return False
    while A[pivot_index] == pivot:
        if pivot_index == len(A) - 1:
            return True
        pivot_index += 1
    for larger in A[pivot_index:len(A)]:
        if larger < pivot:
            return False
    return True

def main():
    for _ in range(1000):
        if len(sys.argv) == 2:
            n = int(argv[1])
        else:
            n = random.randint(1, 100)
        A = [random.randint(0, 10) for _ in range(n)]
        pivot_index = random.randint(0, len(A)-1)
        pivot = A[pivot_index]
        dutch_flag_partition(pivot_index, A)
        assert(is_dutch_flag_sorted(A, pivot))

if __name__=='__main__':
    main()
