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
