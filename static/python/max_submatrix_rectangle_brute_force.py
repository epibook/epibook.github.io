# Max_submatrix_rectangle_brute_force.h bd9b3e8c6bc4755e176bbf01d16d2a77b2bf5147
# O(m^3 n^3) time solution.


def max_rectangle_submatrix_brute_force(A):
    max = 0
    for a in range(len(A)):
        for b in range(len(A[a])):
            for c in range(a, len(A)):
                for d in range(b, len(A[c])):
                    all_1 = True
                    count = 0
                    for i in range(a, c + 1):
                        for j in range(b, d + 1):
                            if A[i][j] == False:
                                all_1 = False
                                count = 0
                                break
                            else:
                                count += 1
                        if not all_1:
                            break
                    if all_1 and count > max:
                        max = count
    return max
