# Power_set_alternative.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random
import itertools


# @include
def generate_power_set(S):
    subset = []
    generate_power_set_helper(S, 0, subset)

def generate_power_set_helper(S, m, subset):
    # Print the subset.
    print(*subset, sep=',')

    for i in range(m, len(S)):
        subset.append(S[i])
        generate_power_set_helper(S, i + 1, subset)
        subset.pop()
# @exclude


# Pythonic solution
def generate_power_set_pythonic(S):
    for n in range(len(S)+1):
        for i in itertools.combinations(S, n):
            print(*i, sep=',')


def main():
    if len(sys.argv) >= 2:
        S = [int(i) for i in sys.argv[1:]]
    else:
        S = list(range(random.randint(1, 10)))
    generate_power_set(S)
    generate_power_set_pythonic(S)


if __name__ == '__main__':
    main()
