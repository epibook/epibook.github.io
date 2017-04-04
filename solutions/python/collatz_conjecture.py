import sys
import random


# @include
def test_collatz_conjecture(n):
    # Stores odd numbers already tested to converge to 1.
    verified_numbers = set()

    # Starts from 3, hypothesis holds trivially for 1.
    for i in range(3, n + 1):
        sequence = set()
        test_i = i
        while test_i >= i:
            if test_i in sequence:
                # We previously encountered test_i, so the Collatz sequence has
                # fallen into a loop. This disproves the hypothesis, so we
                # short-circuit, returning False.
                return False
            sequence.add(test_i)

            if test_i % 2:  # Odd number.
                if test_i in verified_numbers:
                    break  # test_i has already been verified to converge to 1.
                verified_numbers.add(test_i)
                test_i = 3 * test_i + 1  # Multiply by 3 and add 1.
            else:
                test_i //= 2  # Even number, halve it.
    return True
# @exclude


# Slow check without any pruning.
def check(n):
    for i in range(2, n + 1):
        test_i = i
        while test_i != 1 and test_i >= i:
            if test_i % 2:
                test_i = test_i * 3 + 1
            else:
                test_i >>= 1
    return True


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1,
                                                                       100000)

        print('n =', n)
        res = test_collatz_conjecture(n)
        print(res)
        assert res == check(n)


if __name__ == '__main__':
    main()
