import sys
import random


# @include
def count_permutations(k, score_ways):
    permutations = [0] * (k + 1)
    permutations[0] = 1  # One way to reach 0.
    for i in range(k + 1):
        for score in score_ways:
            if i >= score:
                permutations[i] += permutations[i - score]
    return permutations[k]
# @exclude


def simple_test():
    k = 12
    score_ways = [2, 3, 7]
    assert 18 == count_permutations(k, score_ways)


def main():
    simple_test()
    if len(sys.argv) == 1:
        k = random.randrange(1000)
        score_ways = [
            random.randint(1, 1000) for i in range(random.randint(1, 50))
        ]
    elif len(sys.argv) == 2:
        k = int(sys.argv[1])
        score_ways = [
            random.randint(1, 1000) for i in range(random.randint(1, 50))
        ]
    else:
        k = int(sys.argv[1])
        score_ways = [int(i) for i in sys.argv[2:]]
    print(count_permutations(k, score_ways))


if __name__ == '__main__':
    main()
