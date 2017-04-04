import sys
import itertools
import random
import string

char_to_count = {}


# @include
def count_occurrences(S):
    S = sorted(S)
    current_character_count = 1
    for i in range(1, len(S)):
        if S[i] == S[i - 1]:
            current_character_count += 1
        else:
            print('(', S[i - 1], ',', current_character_count, '),', sep='')
            # @exclude
            char_to_count[S[i - 1]] = current_character_count
            # @include
            current_character_count = 1
    print('(', S[-1], ',', current_character_count, ')', sep='')
# @exclude


def count_occurrences_pythonic(S):
    S = sorted(S)
    for key, group in itertools.groupby(S):
        current_character_count = sum(1 for _ in group)
        print('(', key, ',', current_character_count, '),', sep='')
        # @exclude
        char_to_count[key] = current_character_count
        # @include


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def simple_test():
    count_occurrences('foo bar! ABA A')
    assert char_to_count['f'] == 1
    assert 'F' not in char_to_count
    assert char_to_count['o'] == 2
    assert 'x' not in char_to_count
    assert char_to_count[' '] == 3
    assert char_to_count['!'] == 1
    assert char_to_count['A'] == 3
    assert char_to_count['B'] == 1


def main():
    simple_test()
    S = sys.argv[1] if len(
        sys.argv) == 2 else rand_string(
        random.randint(
            1,
            1000))
    print(S)
    count_occurrences(S)
    count_occurrences_pythonic(S)


if __name__ == '__main__':
    main()
