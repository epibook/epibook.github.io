import sys
import random


def check_ans(A):
    A = sorted(A)
    max_interval_size = 1
    pre = A[0]
    length = 1
    for i in range(1, len(A)):
        if A[i] == pre + 1:
            length += 1
        elif A[i] != pre:
            max_interval_size = max(max_interval_size, length)
            length = 1
        pre = A[i]
    max_interval_size = max(max_interval_size, length)
    print(max_interval_size)
    return max_interval_size


def find_longest_contained_range(A):
    if not A:
        return 0

    t = set()  # records the unique appearance.
    # L[i] stores the upper range for i.
    L = {}
    # U[i] stores the lower range for i.
    U = {}
    for a in A:
        if a not in t:
            t.add(a)
            L[a] = U[a] = a
            # Merges with the interval starting on A[i] + 1.
            if a + 1 in L:
                L[U[a]] = L[a + 1]
                U[L[a + 1]] = U[a]
                del L[a + 1]
                del U[a]
            # Merges with the interval ending on A[i] - 1.
            if a - 1 in U:
                U[L[a]] = U[a - 1]
                L[U[a - 1]] = L[a]
                del U[a - 1]
                del L[a]

    m = max(L.items(), key=lambda a: a[1] - a[0])
    return m[1] - m[0] + 1


# @include
def longest_contained_range(A):
    # unprocessed_entries records the existence of each entry in A.
    unprocessed_entries = set(A)

    max_interval_size = 0
    while unprocessed_entries:
        a = unprocessed_entries.pop()

        # Finds the lower bound of the largest range containing a.
        lower_bound = a - 1
        while lower_bound in unprocessed_entries:
            unprocessed_entries.remove(lower_bound)
            lower_bound -= 1

        # Finds the upper bound of the largest range containing a.
        upper_bound = a + 1
        while upper_bound in unprocessed_entries:
            unprocessed_entries.remove(upper_bound)
            upper_bound += 1

        max_interval_size = max(max_interval_size,
                                upper_bound - lower_bound - 1)
    return max_interval_size
# @exclude


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(0, 10000)
        A = [random.randint(0, n) for _ in range(n)]
        assert find_longest_contained_range(A) == check_ans(A)
        assert longest_contained_range(A) == check_ans(A)


if __name__ == '__main__':
    main()
