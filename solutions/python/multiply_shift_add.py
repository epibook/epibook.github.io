import sys
import random


# @include
def multiply(x, y):
    def add(a, b):
        running_sum, carryin, k, temp_a, temp_b = 0, 0, 1, a, b
        while temp_a or temp_b:
            ak, bk = a & k, b & k
            carryout = (ak & bk) | (ak & carryin) | (bk & carryin)
            running_sum |= ak ^ bk ^ carryin
            carryin, k, temp_a, temp_b = (carryout << 1, k << 1, temp_a >> 1,
                                          temp_b >> 1)
        return running_sum | carryin

    running_sum = 0
    while x:  # Examines each bit of x.
        if x & 1:
            running_sum = add(running_sum, y)
        x, y = x >> 1, y << 1
    return running_sum


# @exclude


def main():
    if len(sys.argv) == 3:
        x, y = int(sys.argv[1]), int(sys.argv[2])
        res = multiply(x, y)
        assert res == x * y
        print('x = %d, y = %d, prod = %d' % (x, y, res))
    else:
        for _ in range(100000):
            x, y = random.randint(0, 65534), random.randint(0, 65534)
            prod = multiply(x, y)
            assert prod == x * y
            print('x = %d, y = %d, prod = %d' % (x, y, prod))


if __name__ == '__main__':
    main()
