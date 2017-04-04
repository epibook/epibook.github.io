import functools
import string
import sys
import random


def rand_int_string(length):
    if length == 0:
        return '0'
    ret = []
    if random.randint(0, 1) == 1:
        ret.append('-')
    ret += [chr(random.randint(ord('1'), ord('9')))] + \
        [random.choice(string.digits) for _ in range(length - 1)]
    return ''.join(ret)


# @include
def int_to_string(x):
    is_negative = False
    if x < 0:
        x, is_negative = -x, True

    s = []
    while True:
        s.append(chr(ord('0') + x % 10))
        x //= 10
        if x == 0:
            break

    # Adds the negative sign back if is_negative
    return ('-' if is_negative else '') + ''.join(reversed(s))


def string_to_int(s):
    return functools.reduce(lambda running_sum,
                            c: running_sum * 10 + string.digits.index(c),
                            s[s[0] == '-':],
                            0) * (-1 if s[0] == '-' else 1)
# @exclude


def directed_tests():
    assert '0' == int_to_string(0)
    assert '-1' == int_to_string(-1)
    assert '1' == int_to_string(1)
    assert '2' == int_to_string(2)
    assert '-2' == int_to_string(-2)
    assert '9' == int_to_string(9)
    assert '10' == int_to_string(10)
    assert '123' == int_to_string(123)
    assert '2147483646' == int_to_string(2147483646)
    assert '2147483647' == int_to_string(2147483647)
    assert '-2147483647' == int_to_string(-2147483647)
    assert '-2147483648' == int_to_string(-2147483648)

    assert 0 == string_to_int('0')
    assert -1 == string_to_int('-1')
    assert 1 == string_to_int('1')
    assert 2 == string_to_int('2')
    assert -2 == string_to_int('-2')
    assert 9 == string_to_int('9')
    assert 10 == string_to_int('10')
    assert 123 == string_to_int('123')
    assert 2147483646 == string_to_int('2147483646')
    assert 2147483647 == string_to_int('2147483647')
    assert -2147483648 == string_to_int('-2147483648')
    assert -2147483647 == string_to_int('-2147483647')


def main():
    directed_tests()
    if len(sys.argv) == 2:
        print(string_to_int(sys.argv[1]))
    else:
        for _ in range(10000):
            x = random.randint(-sys.maxsize, sys.maxsize)
            s = int_to_string(x)
            print(x, s)
            assert x == int(s)
            s = rand_int_string(random.randint(0, 9))
            x = string_to_int(s)
            print(s, x)
            assert x == int(s)


if __name__ == '__main__':
    main()
