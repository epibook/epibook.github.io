# @include
def longest_nondecreasing_subsequence_length(A):
    # max_length[i] holds the length of the longest nondecreasing subsequence
    # of A[:i + 1].
    max_length = [1] * len(A)
    for i in range(1, len(A)):
        max_length[i] = max(1 + max(max_length[j] for j in range(i)
                                    if A[i] >= A[j]), max_length[i])
    return max(max_length)
# @exclude
