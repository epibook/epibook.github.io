import sys
import random
import string
import collections


def rand_string(length):
    ret = []
    for i in range(length):
        ch = random.randint(0, 30)
        if ch > 25:
            ret.append(' ')
        else:
            ret.append(string.ascii_lowercase[ch])
    return ''.join(ret)


# @include
def is_letter_constructible_from_magazine(letter_text, magazine_text):
    # Compute the frequencies for all chars in letter_text.
    char_frequency_for_letter = collections.Counter(letter_text)

    # Checks if characters in magazine_text can cover characters in
    # char_frequency_for_letter.
    for c in magazine_text:
        if c in char_frequency_for_letter:
            char_frequency_for_letter[c] -= 1
            if char_frequency_for_letter[c] == 0:
                del char_frequency_for_letter[c]
                if not char_frequency_for_letter:
                    # All characters for letter_text are matched.
                    return True

    # Empty char_frequency_for_letter means every char in letter_text can be
    # covered by a character in magazine_text.
    return not char_frequency_for_letter


# Pythonic solution that exploits collections.Counter. Note that the
# subtraction only keeps keys with positive counts.
def is_letter_constructible_from_magazine_pythonic(letter_text, magazine_text):
    return (not collections.Counter(letter_text) -
            collections.Counter(magazine_text))
# @exclude


def simple_test():
    assert not is_letter_constructible_from_magazine('123', '456')
    assert not is_letter_constructible_from_magazine('123', '12222222')
    assert is_letter_constructible_from_magazine('123', '1123')
    assert is_letter_constructible_from_magazine('123', '123')
    assert is_letter_constructible_from_magazine('GATTACA',
                                                 'A AD FS GA T ACA TTT')
    assert not is_letter_constructible_from_magazine('a', '')
    assert is_letter_constructible_from_magazine('aa', 'aa')
    assert is_letter_constructible_from_magazine('aa', 'aaa')
    assert is_letter_constructible_from_magazine('', '123')
    assert is_letter_constructible_from_magazine('', '')

    assert not is_letter_constructible_from_magazine_pythonic('123', '456')
    assert not is_letter_constructible_from_magazine_pythonic('123', '12222222')
    assert is_letter_constructible_from_magazine_pythonic('123', '1123')
    assert is_letter_constructible_from_magazine_pythonic('123', '123')
    assert is_letter_constructible_from_magazine_pythonic(
        'GATTACA', 'A AD FS GA T ACA TTT')
    assert not is_letter_constructible_from_magazine_pythonic('a', '')
    assert is_letter_constructible_from_magazine_pythonic('aa', 'aa')
    assert is_letter_constructible_from_magazine_pythonic('aa', 'aaa')
    assert is_letter_constructible_from_magazine_pythonic('', '123')
    assert is_letter_constructible_from_magazine_pythonic('', '')


def main():
    simple_test()
    if len(sys.argv) == 3:
        L = sys.argv[1]
        M = sys.argv[2]
    else:
        L = rand_string(random.randint(1, 1000))
        M = rand_string(random.randint(1, 100000))
    print(L)
    print(M)
    print(is_letter_constructible_from_magazine(L, M))
    print(is_letter_constructible_from_magazine_pythonic(L, M))


if __name__ == '__main__':
    main()
