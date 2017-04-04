import sys
import random
import string
import can_string_be_palindrome_hash
import can_string_be_palindrome_sorting


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def main():
    for _ in range(1000):
        s = sys.argv[1] if len(sys.argv) == 2 else rand_string(
            random.randint(1, 10))
        print(s)
        assert (can_string_be_palindrome_hash.can_string_be_a_palindrome(s) ==
                can_string_be_palindrome_sorting.can_string_be_a_palindrome(s)
                == can_string_be_palindrome_sorting.
                can_string_be_a_palindrome_pythonic(s))


if __name__ == '__main__':
    main()
