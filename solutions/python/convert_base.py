import functools
import sys
import random
import string


# @include
def convert_base(num_as_string, b1, b2):
    def construct_from_base(num_as_int, base):
        return ('' if num_as_int == 0 else
                construct_from_base(num_as_int // base, base) +
                string.hexdigits[num_as_int % base].upper())

    is_negative = num_as_string[0] == '-'
    num_as_int = functools.reduce(
        lambda x, c: x * b1 + string.hexdigits.index(c.lower()),
        num_as_string[is_negative:], 0)
    return ('-' if is_negative else '') + ('0' if num_as_int == 0 else
                                           construct_from_base(num_as_int, b2))
# @exclude


def rand_int_string(length):
    if length == 0:
        return '0'
    ret = []
    if random.randint(0, 1) == 1:
        ret.append('-')
    ret += [chr(random.randint(ord('1'), ord('9')))] + \
        [random.choice(string.digits) for _ in range(length - 1)]
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
            assert input_str == convert_base(
                convert_base(input_str, 10, base), base, 10)


if __name__ == '__main__':
    main()
