# Interconverting_string_integer.cpp 69dab7578339012270872ba200cfff02f59ce894
import sys
import random
import string


def rand_int_string(length):
    if length == 0:
        return '0'
    ret = []
    if random.randint(0, 1) == 1:
        ret.append('-')
    ret.append(chr(random.randint(ord('1'), ord('9'))))
    for _ in range(length-1):
        ret.append(random.choice(string.digits))
    return ''.join(ret)


# @include
def int_to_string(x):
    is_negative = False
    if x < 0:
        x = -x
        is_negative = True

    s = []
    while True:
        s.append(chr(ord('0') + x % 10))
        x //= 10
        if x == 0:
            break

    if is_negative:
        s.append('-')  # Adds the negative sign back.

    return ''.join(reversed(s))


def string_to_int(s):
    is_negative = s[0] == '-'
    result = 0
    for c in s[1 if is_negative else 0:]:
        digit = ord(c) - ord('0')
        result = result * 10 + digit
    return -result if is_negative else result
# @exclude


def main():
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
