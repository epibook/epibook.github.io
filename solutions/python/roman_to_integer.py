import functools
import sys


# @include
def roman_to_integer(s):
    T = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}

    return functools.reduce(
        lambda val, i: val + (-T[s[i]] if T[s[i]] < T[s[i + 1]] else T[s[i]]),
        reversed(range(len(s) - 1)), T[s[-1]])
# @exclude


def main():
    assert 7 == roman_to_integer('VII')
    assert 184 == roman_to_integer('CLXXXIV')
    assert 9 == roman_to_integer('IX')
    assert 40 == roman_to_integer('XL')
    assert 60 == roman_to_integer('LX')
    assert 1500 == roman_to_integer('MD')
    assert 400 == roman_to_integer('CD')
    assert 1900 == roman_to_integer('MCM')
    assert 9919 == roman_to_integer('MMMMMMMMMCMXIX')
    if len(sys.argv) == 2:
        print(sys.argv[1], 'equals to', roman_to_integer(sys.argv[1]))


if __name__ == '__main__':
    main()
