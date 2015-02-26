# Intersect_sorted_arrays1.h 4a4c5f91493e5e482eaa79892816c1ccefa084f4
# @include
def intersect_two_sorted_arrays(A, B):
    intersection_A_B = []
    for i in range(len(A)):
        if i == 0 or A[i] != A[i - 1]:
            for b in B:
                if A[i] == b:
                    intersection_A_B.append(A[i])
                    break
    return intersection_A_B
# @exclude
