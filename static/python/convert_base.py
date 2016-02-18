# Convert_base.cpp b4b3a70d8ab942579f85b4416f980d05831af969
import sys
import random
import string


# @include
def convert_base(s, b1, b2):
    is_negative = s[0] == '-'
    x = 0
    for c in s[1 if is_negative else 0:]:
        x *= b1
        x += ord(c) - ord('0') if c.isdigit() else ord(c) - ord('A') + 10

    result = []
    while True:
        x, reminder = divmod(x, b2)
        result.append(chr(ord('A') + reminder - 10) if reminder >= 10 else chr(ord('0') + reminder))
        if x == 0:
            break

    if is_negative:  # s is a negative number.
        result.append('-')

    return ''.join(reversed(result))
# @exclude


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


def main():
    if len(sys.argv) == 4:
        input_str = sys.argv[1]
        print(convert_base(input_str, int(sys.argv[2]), int(sys.argv[3])))
        assert input_str == convert_base(
            convert_base(input_str, int(sys.argv[2]), int(sys.argv[3])),
            int(sys.argv[3]), int(sys.argv[2]))
    else:
        for _ in range(1000):
            input_str = rand_int_string(random.randint(1, 9))
            base = random.randint(2, 16)
            print('input is %s, base1 = 10, base2 = %d, result = %s' %
                  (input_str, base, convert_base(input_str, 10, base)))
            assert input_str == convert_base(convert_base(input_str, 10, base), base, 10)


if __name__ == '__main__':
    main()
