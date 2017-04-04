import sys
import random
from offline_sampling import random_sampling


# @include
def compute_random_permutation(n):
    permutation = list(range(n))
    random_sampling(n, permutation)
    return permutation
# @exclude


def main():
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000000)
    result = sorted(compute_random_permutation(n))
    assert all(result[i] == i for i in range(len(result)))


if __name__ == '__main__':
    main()
