import sys
import random


# @include
def longest_subarray_with_distinct_entries(A):
    # Records the most recent occurrences of each entry.
    most_recent_occurrence = {}
    longest_dup_free_subarray_start_idx = result = 0
    for i, a in enumerate(A):
        # Defer updating dup_idx until we see a duplicate.
        if a in most_recent_occurrence:
            dup_idx = most_recent_occurrence[a]
            # A[i] appeared before. Did it appear in the longest current
            # subarray?
            if dup_idx >= longest_dup_free_subarray_start_idx:
                result = max(result, i - longest_dup_free_subarray_start_idx)
                longest_dup_free_subarray_start_idx = dup_idx + 1
        most_recent_occurrence[a] = i
    return max(result, len(A) - longest_dup_free_subarray_start_idx)
# @exclude


# O(n^2) checking solution.
def check_ans(A):
    length = 0
    for i in range(len(A)):
        table = set()
        j = i
        while len(A) - i > length and j < len(A):
            if A[j] in table:
                break
            table.add(A[j])
            j += 1
        length = max(length, j - i)
    return length


def simple_test():
    assert 1 == longest_subarray_with_distinct_entries([1, 1, 1])
    assert 2 == longest_subarray_with_distinct_entries([1, 2, 1])
    assert 3 == longest_subarray_with_distinct_entries([1, 2, 1, 3, 1, 2, 1])
    assert 2 == longest_subarray_with_distinct_entries(
        [1, 2, 2, 3, 3, 1, 1, 2, 1])


def main():
    simple_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(0, 1000)
    print('n =', n)
    for _ in range(1000):
        A = [random.randint(0, 1000) for _ in range(n)]
        ans = longest_subarray_with_distinct_entries(A)
        golden_ans = check_ans(A)
        print(ans, golden_ans)
        assert ans == golden_ans


if __name__ == '__main__':
    main()
