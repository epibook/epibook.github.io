# Reverse_words.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
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
def reverse_words(s):
    # As python strings are immutable, we'll use bytearray instead of a string.
    # Reverses the whole string first.
    s.reverse()

    start = 0
    while True:
        end = s.find(b' ', start)
        if end < 0:
            break
        # Reverses each word in the string.
        s[start:end] = reversed(s[start:end])
        start = end + 1
    # Reverses the last word.
    s[start:] = reversed(s[start:])
# @exclude


# Pythonic solution, doesn't reverse in-place, may be used with strings
def reverse_words_pythonic(s):
    return ' '.join(reversed(s.split(' ')))


def check_answer(ori, s):
    reverse_words(s)
    assert ori == s


def main():
    for _ in range(1000):
        if len(sys.argv) >= 2:
            s = bytearray(' '.join(sys.argv[1:]), 'utf-8')
        else:
            s = rand_string(random.randint(0, 100))
        original_str = s.copy()
        print(s.decode())
        reverse_words(s)
        print(s.decode())
        assert s.decode() == reverse_words_pythonic(original_str.decode())
        check_answer(original_str, s)


if __name__ == '__main__':
    main()
