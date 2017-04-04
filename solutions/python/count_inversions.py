import sys
import random


# @include
def count_inversions(A):
    # Return the number of inversions in A[start:end].
    def count_subarray_inversions(start, end):
        # Merge two sorted subarrays A[start:mid] and A[mid:end] into
        # A[start:end] and return the number of inversions across A[start:mid]
        # and A[mid:end].
        def merge_sort_and_count_inversions_across_subarrays(start, mid, end):
            sorted_A = []
            left_start, right_start, inversion_count = start, mid, 0

            while left_start < mid and right_start < end:
                if A[left_start] <= A[right_start]:
                    sorted_A.append(A[left_start])
                    left_start += 1
                else:
                    # A[left_start:mid] are the inversions of A[right_start].
                    inversion_count += mid - left_start
                    sorted_A.append(A[right_start])
                    right_start += 1

            # Updates A with sorted_A.
            A[start:end] = sorted_A + A[left_start:mid] + A[right_start:end]
            return inversion_count

        if end - start <= 1:
            return 0
        mid = start + ((end - start) // 2)
        return (
            count_subarray_inversions(start, mid) +
            count_subarray_inversions(mid, end) +
            merge_sort_and_count_inversions_across_subarrays(start, mid, end))

    return count_subarray_inversions(0, len(A))
# @exclude


# O(n^2) check of inversions.
def n2_check(A):
    count = sum(A[i] > A[j] for i in range(len(A))
                for j in range(i + 1, len(A)))
    print(count)
    return count


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        A = [random.randint(-1000000, 1000000) for _ in range(n)]
        assert n2_check(A) == count_inversions(A)


if __name__ == '__main__':
    main()
