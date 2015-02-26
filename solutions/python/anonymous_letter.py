# Anonymous_letter.cpp 4a4c5f91493e5e482eaa79892816c1ccefa084f4
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
    char_frequency_for_letter = collections.defaultdict(int)
    # Inserts all chars in letter_text into a hash table.
    for c in letter_text:
        char_frequency_for_letter[c] += 1


    # Checks if characters in magazine_text can cover characters
    # in char_frequency_for_letter.
    for c in magazine_text:
        if c in char_frequency_for_letter:
            char_frequency_for_letter[c] -= 1
            if char_frequency_for_letter[c] == 0:
                del char_frequency_for_letter[c]
            if not char_frequency_for_letter:
                return True

    # Empty char_frequency_for_letter means every char in letter_text can be
    # covered by a character in magazine_text.
    return len(char_frequency_for_letter) == 0
# @exclude


# Pythonic solution
def is_letter_constructible_from_magazine_pythonic(letter_text, magazine_text):
    return len(collections.Counter(letter_text) - collections.Counter(magazine_text)) == 0


def main():
    if len(sys.argv) == 3:
        L = sys.argv[1]
        M = sys.argv[2]
    else:
        L = rand_string(random.randint(1, 1000))
        M = rand_string(random.randint(1, 100000))
    print(L)
    print(M)
    assert not is_letter_constructible_from_magazine('123', '456')
    assert not is_letter_constructible_from_magazine('123', '12222222')
    assert is_letter_constructible_from_magazine('123', '1123')
    assert is_letter_constructible_from_magazine('123', '123')
    assert not is_letter_constructible_from_magazine_pythonic('123', '456')
    assert not is_letter_constructible_from_magazine_pythonic('123', '12222222')
    assert is_letter_constructible_from_magazine_pythonic('123', '1123')
    assert is_letter_constructible_from_magazine_pythonic('123', '123')
    print(is_letter_constructible_from_magazine(L, M))
    print(is_letter_constructible_from_magazine_pythonic(L, M))


if __name__ == '__main__':
    main()
