import sys
import random


def divide_bsearch(x, y):
    if x < y:
        return 0

    power_left, power_right, power_mid = 0, x.bit_length(), -1
    while power_left < power_right:
        tmp = power_mid
        power_mid = power_left + (power_right - power_left) // 2
        if tmp == power_mid:
            break

        yshift = y << power_mid
        if yshift > x:
            power_right = power_mid
        elif yshift < x:
            power_left = power_mid
        else:
            return 1 << power_mid
    part = 1 << power_left
    return part | divide_bsearch(x - (y << power_left), y)


# @include
def divide(x, y):
    result, power = 0, 32
    y_power = y << power
    while x >= y:
        while y_power > x:
            y_power >>= 1
            power -= 1

        result += 1 << power
        x -= y_power
    return result
# @exclude


def simple_test():
    assert divide(64, 1) == 64
    assert divide(64, 2) == 32
    assert divide(64, 3) == 21
    assert divide(64, 4) == 16
    assert divide(64, 5) == 12
    assert divide(65, 2) == 32
    assert divide(2600540749, 2590366779) == 1
    assert divide_bsearch(4, 2)
    assert divide_bsearch(64, 1) == 64
    assert divide_bsearch(64, 2) == 32
    assert divide_bsearch(64, 3) == 21
    assert divide_bsearch(64, 4) == 16
    assert divide_bsearch(64, 5) == 12
    assert divide_bsearch(65, 2) == 32
    assert divide_bsearch(9444, 4714) == 2
    assert divide_bsearch(8186, 19) == 430
    assert divide_bsearch(8186, 19) == 430


def main():
    simple_test()
    if len(sys.argv) == 3:
        x, y = int(sys.argv[1]), int(sys.argv[2])
        assert x // y == divide(x, y) == divide_bsearch(x, y)
    else:
        for times in range(100000):
            x = random.randint(0, sys.maxsize)
            y = random.randint(1, sys.maxsize)  # ensure no divide by 0.
            print('times = %d, x = %d, y = %d' % (times, x, y))
            print('first = %d, second = %d' % (x // y, divide(x, y)))
            assert x // y == divide(x, y) == divide_bsearch(x, y)


if __name__ == '__main__':
    main()
