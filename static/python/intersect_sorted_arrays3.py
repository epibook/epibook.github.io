# Intersect_sorted_arrays3.h 4a4c5f91493e5e482eaa79892816c1ccefa084f4
# @include
def intersect_two_sorted_arrays(A, B):
    intersection_A_B = []
    i = 0
    j = 0
    while i < len(A) and j < len(B):
        if A[i] == B[j]:
            if i == 0 or A[i] != A[i - 1]:
                intersection_A_B.append(A[i])
            i += 1
            j += 1
        elif A[i] < B[j]:
            i += 1
        else:  # A[i] > B[j].
            j += 1

    return intersection_A_B
# @exclude
