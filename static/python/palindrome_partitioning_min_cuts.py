import sys
import random
import string


# @include
def min_cuts(s):
    is_palindrome = [[False] * len(s) for i in s]
    T = list(reversed(range(-1, len(s))))
    for i in reversed(range(len(s))):
        for j in range(i, len(s)):
            if s[i] == s[j] and (j - i < 2 or is_palindrome[i + 1][j - 1]):
                is_palindrome[i][j] = True
                T[i] = min(T[i], 1 + T[j + 1])
    return T[0]
# @exclude


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def small_test():
    assert 1 == min_cuts('aab')
    assert 0 == min_cuts('bb')
    assert 3 == min_cuts('cabababcbc')
    assert 42 == min_cuts('eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj')


def main():
    small_test()
    s = sys.argv[1] if len(
        sys.argv) == 2 else rand_string(
        random.randint(
            0, 10))
    print('times =', min_cuts(s))


if __name__ == '__main__':
    main()
