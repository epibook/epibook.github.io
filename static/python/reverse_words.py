import sys
import random
import string


def rand_string(length):
    ret = bytearray(length)
    for i in range(length):
        ch = random.randint(0, 62)
        if ch > 51:
            ret[i] = ord(' ')
        else:
            ret[i] = ord(string.ascii_letters[ch])
    return ret


# @include
# Assume s is a string encoded as bytearray.
def reverse_words(s):
    # First, reverse the whole string.
    s.reverse()

    def reverse_range(s, start, end):
        while start < end:
            s[start], s[end] = s[end], s[start]
            start, end = start + 1, end - 1

    start = 0
    while True:
        end = s.find(b' ', start)
        if end < 0:
            break
        # Reverses each word in the string.
        reverse_range(s, start, end - 1)
        start = end + 1
    # Reverses the last word.
    reverse_range(s, start, len(s) - 1)
# @exclude


# Pythonic solution, doesn't reverse in-place, may be used with strings
def reverse_words_pythonic(s):
    return ' '.join(reversed(s.split(' ')))


def check_answer(ori, s):
    reverse_words(s)
    assert ori == s


def main():
    for _ in range(1000):
        s = bytearray(' '.join(sys.argv[1:]), 'utf-8') if len(
            sys.argv) >= 2 else rand_string(random.randint(0, 100))
        original_str = s.copy()
        print(s.decode())
        reverse_words(s)
        print(s.decode())
        assert s.decode() == reverse_words_pythonic(original_str.decode())
        check_answer(original_str, s)


if __name__ == '__main__':
    main()
