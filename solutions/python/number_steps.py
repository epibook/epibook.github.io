import sys
import random


# @include
def number_of_ways_to_top(top, maximum_step):
    def compute_number_of_ways_to_h(h):
        if h <= 1:
            return 1

        if number_of_ways_to_h[h] == 0:
            number_of_ways_to_h[h] = sum(
                compute_number_of_ways_to_h(h - i)
                for i in range(1, min(maximum_step, h) + 1))
        return number_of_ways_to_h[h]

    number_of_ways_to_h = [0] * (top + 1)
    return compute_number_of_ways_to_h(top)
# @exclude


def main():
    assert 5 == number_of_ways_to_top(4, 2)
    assert 1 == number_of_ways_to_top(1, 2)
    assert 1 == number_of_ways_to_top(0, 3)
    if len(sys.argv) == 3:
        n = int(sys.argv[1])
        k = int(sys.argv[2])
    else:
        n = random.randint(1, 20)
        k = random.randint(1, n)
    print('n = %d, k = %d' % (n, k))
    print(number_of_ways_to_top(n, k))


if __name__ == '__main__':
    main()
