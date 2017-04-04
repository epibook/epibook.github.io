import itertools
import random
import sys


# @include
def next_permutation(perm):
    # Find the first entry from the right that is smaller than the entry
    # immediately after it.
    inversion_point = len(perm) - 2
    while (inversion_point >= 0 and
           perm[inversion_point] >= perm[inversion_point + 1]):
        inversion_point -= 1
    if inversion_point == -1:
        return []  # perm is the last permutation.

    # Swap the smallest entry after index inversion_point that is greater than
    # perm[inversion_point]. Since entries in perm are decreasing after
    # inversion_point, if we search in reverse order, the first entry that is
    # greater than perm[inversion_point] is the entry to swap with.
    for i in reversed(range(inversion_point + 1, len(perm))):
        if perm[i] > perm[inversion_point]:
            perm[inversion_point], perm[i] = perm[i], perm[inversion_point]
            break

    # Entries in perm must appear in decreasing order after inversion_point,
    # so we simply reverse these entries to get the smallest dictionary order.
    perm[inversion_point + 1:] = reversed(perm[inversion_point + 1:])
    return perm
# @exclude


def main():
    for _ in range(1000):
        if len(sys.argv) > 2:
            perm = list(map(int, sys.argv[1:]))
        else:
            n = int(
                sys.argv[1]) if len(
                sys.argv) == 2 else random.randint(
                1,
                10)
            perm = [random.randrange(n) for _ in range(n)]

        gold = list(itertools.permutations(sorted(perm)))
        for i, p in reversed(list(enumerate(gold))):
            if list(p) == perm:
                target = i
                break
        ans = next_permutation(perm)
        assert (target == len(gold) - 1 and ans == []
                ) or (list(gold[target + 1]) == ans)


if __name__ == '__main__':
    main()
