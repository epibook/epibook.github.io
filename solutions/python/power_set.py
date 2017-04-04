import sys
import math
import random


# @include
def generate_power_set(S):
    power_set = []
    for int_for_subset in range(1 << len(S)):
        bit_array = int_for_subset
        subset = []
        while bit_array:
            subset.append(int(math.log2(bit_array & ~(bit_array - 1))))
            bit_array &= bit_array - 1
        power_set.append(subset)
    return power_set
# @exclude


def simple_test():
    assert generate_power_set(
        [0, 1, 2]) == [[], [0], [1], [0, 1], [2], [0, 2], [1, 2], [0, 1, 2]]


def main():
    simple_test()
    S = [
        int(i) for i in sys.argv[
            1:]] if len(
        sys.argv) >= 2 else list(
                range(
                    random.randint(
                        1,
                        10)))
    print(generate_power_set(S))


if __name__ == '__main__':
    main()
