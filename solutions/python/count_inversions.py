# Count_inversions.cc
import random
import sys

# @include
def count_inversions(A):
    return count_inversions_within_subarray(A, 0, len(A))

def count_inversions_within_subarray(A, start, end):
    if end - start <= 1:
        return 0

    mid = start + int((end - start) / 2)

    return count_inversions_within_subarray(A, start, mid) +\
           count_inversions_within_subarray(A, mid, end) +\
           merge_sort_and_count_inversions_across_subarrays(A, start, mid, end)

def merge_sort_and_count_inversions_across_subarrays(A, start, mid, end):
    inversion_count = 0
    sorted_A = []
    left, right = start, mid
    while left < mid and right < end:
        if A[left] <= A[right]:
            sorted_A.append(A[left])
            left += 1
        else:
            sorted_A.append(A[right])
            right += 1
            inversion_count += mid - left
    sorted_A.extend(A[left:mid])
    sorted_A.extend(A[right:end])
    A[start:end] = sorted_A
    return inversion_count

# @exclude
def n_2_check(A):
    count = 0
    for i in range(len(A)):
        for j in range(i + 1, len(A)):
            if A[i] > A[j]:
                count += 1

    return count

def main():
    num_cases = int(sys.argv[1]) if len(sys.argv) == 2 else 10

    for _ in range(num_cases):
        n = random.randint(1, 1000)
        A = [random.randint(-10000, 10000) for __ in range(n)]
        assert n_2_check(A) == count_inversions(A)

if __name__ == '__main__':
    main()
