import sys
import random


def zero_one_random():
    return random.randrange(2)


# @include
def uniform_random(lower_bound, upper_bound):
    number_of_outcomes = upper_bound - lower_bound + 1
    while True:
        result, i = 0, 0
        while (1 << i) < number_of_outcomes:
            # zero_one_random() is the provided random number generator.
            result = (result << 1) | zero_one_random()
            i += 1
        if result < number_of_outcomes:
            break
    return result + lower_bound
# @exclude


def main():
    for _ in range(1000):
        if len(sys.argv) == 3:
            a, b = int(sys.argv[1]), int(sys.argv[2])
        else:
            a = random.randint(0, 99)
            b = random.randint(a + 1, a + 100)
        x = uniform_random(a, b)
        print('a =', a, 'b =', b)
        print('random result =', x)
        assert a <= x <= b


if __name__ == '__main__':
    main()
