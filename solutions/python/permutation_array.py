import sys
import random
import permutation_array1
import permutation_array2


def permute(P, A):
    P = P.copy()
    for _ in range(len(P)):
        for i in range(len(P)):
            A[i], A[P[i]] = A[P[i]], A[i]
            P[P[i]], P[i] = P[i], P[P[i]]


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 100)

        A = list(range(n))
        perm = A.copy()

        # Knuth shuffle
        random.shuffle(perm)
        print(*perm)

        B = A.copy()
        permutation_array1.apply_permutation(perm, B)
        print(*B)
        C = A.copy()
        permutation_array2.apply_permutation(perm, C)
        print(*C)
        D = A.copy()
        permute(perm, D)
        print(*D)
        # Check answer by comparing the two permutations.
        assert B == C == D


if __name__ == '__main__':
    main()
