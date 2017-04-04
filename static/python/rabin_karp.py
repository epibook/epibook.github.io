import sys
import random
import string
import functools


# @include
def rabin_karp(t, s):
    if len(s) > len(t):
        return -1  # s is not a substring of t.

    BASE = 26
    # Hash codes for the substring of t and s.
    t_hash = functools.reduce(lambda h, c: h * BASE + ord(c), t[:len(s)], 0)
    s_hash = functools.reduce(lambda h, c: h * BASE + ord(c), s, 0)
    power_s = BASE ** max(len(s) - 1, 0)  # The modulo result of BASE^|s-1|.

    for i in range(len(s), len(t)):
        # Checks the two substrings are actually equal or not, to protect
        # against hash collision.
        if t_hash == s_hash and t[i - len(s):i] == s:
            return i - len(s)  # Found a match.

        # Uses rolling hash to compute the hash code.
        t_hash -= ord(t[i - len(s)]) * power_s
        t_hash = t_hash * BASE + ord(t[i])

    # Tries to match s and t[-len(s):].
    if t_hash == s_hash and t[-len(s):] == s:
        return len(t) - len(s)
    return -1  # s is not a substring of t.
# @exclude


def check_answer(t, s):
    i = 0
    while i + len(s) - 1 < len(t):
        find = True
        for j in range(len(s)):
            if t[i + j] != s[j]:
                find = False
                break
        if find:
            return i
        i += 1
    return -1  # find no matching.


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def simple_test():
    assert rabin_karp('GACGCCA', 'CGC') == 2
    assert rabin_karp('GATACCCATCGAGTCGGATCGAGT', 'GAG') == 10
    assert rabin_karp('FOOBARWIDGET', 'WIDGETS') == -1
    assert rabin_karp('A', 'A') == 0
    assert rabin_karp('A', 'B') == -1
    assert rabin_karp('A', '') == 0
    assert rabin_karp('ADSADA', '') == 0
    assert rabin_karp('', 'A') == -1
    assert rabin_karp('', 'AAA') == -1
    assert rabin_karp('A', 'AAA') == -1
    assert rabin_karp('AA', 'AAA') == -1
    assert rabin_karp('AAA', 'AAA') == 0
    assert rabin_karp('BAAAA', 'AAA') == 1
    assert rabin_karp('BAAABAAAA', 'AAA') == 1
    assert rabin_karp('BAABBAABAAABS', 'AAA') == 8
    assert rabin_karp('BAABBAABAAABS', 'AAAA') == -1
    assert rabin_karp('FOOBAR', 'BAR') > 0


def main():
    simple_test()
    if len(sys.argv) == 3:
        t = sys.argv[1]
        s = sys.argv[2]
        print('t =', t)
        print('s =', s)
        assert rabin_karp(t, s) == check_answer(t, s)
    else:
        for _ in range(10000):
            t = rand_string(random.randint(1, 1000))
            s = rand_string(random.randint(1, 20))
            print('t =', t)
            print('s =', s)
            ret1 = rabin_karp(t, s)
            ret2 = check_answer(t, s)
            print(ret1, ret2)
            assert rabin_karp(t, s) == check_answer(t, s)


if __name__ == '__main__':
    main()
