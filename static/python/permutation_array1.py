# @include
def apply_permutation(perm, A):
    for i in range(len(A)):
        # Check if the element at index i has not been moved by checking if
        # perm[i] is nonnegative.
        next = i
        while perm[next] >= 0:
            A[i], A[perm[next]] = A[perm[next]], A[i]
            temp = perm[next]
            # Subtracts len(perm) from an entry in perm to make it negative,
            # which indicates the corresponding move has been performed.
            perm[next] -= len(perm)
            next = temp
    # Restore perm.
    perm[:] = [a + len(perm) for a in perm]
# @exclude
