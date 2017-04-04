import sys
import random
import fractions
import gcd1
import gcd2


def check(a, b):
    assert gcd1.gcd(a, b) == gcd2.gcd(a, b)


def main():
    check(2, 2)
    check(2, 3)
    check(17, 289)
    check(13, 17)
    check(17, 289)
    check(18, 24)
    check(1024 * 1023, 1023 * 1025)
    check(317 * 1024 * 1023, 317 * 1023 * 1025)
    check(sys.maxsize, sys.maxsize - 1)
    check(sys.maxsize - 1, (sys.maxsize - 1) // 2)
    check(0, 0)
    check(0, 1)
    check(10, 100)
    x = 18
    y = 12
    assert gcd1.gcd(x, y) == 6
    if len(sys.argv) == 3:
        x = int(sys.argv[1])
        y = int(sys.argv[2])
        print(gcd1.gcd(x, y))
        assert gcd1.gcd(x, y) == fractions.gcd(x, y) == gcd2.gcd(x, y)
    else:
        for _ in range(1000):
            x = random.randint(1, sys.maxsize)
            y = random.randint(1, sys.maxsize)
            assert gcd1.gcd(x, y) == fractions.gcd(x, y) == gcd2.gcd(x, y)


if __name__ == '__main__':
    main()
