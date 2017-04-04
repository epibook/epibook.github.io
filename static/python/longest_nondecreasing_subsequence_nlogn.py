# Longest_nondecreasing_subsequence_nlogn.h
# bd9b3e8c6bc4755e176bbf01d16d2a77b2bf5147
import bisect


# @include
def longest_nondecreasing_subsequence_length(A):
    tail_values = []
    for a in A:
        it = bisect.bisect(tail_values, a)
        if it == len(tail_values):
            tail_values.append(a)
        else:
            tail_values[it] = a
    return len(tail_values)


# @exclude
