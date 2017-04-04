import random
import string


# @include
def is_palindromic(s):
    # Note that s[~i] for i in [0, len(s) - 1] is s[-(i + 1)].
    return all(s[i] == s[~i] for i in range(len(s) // 2))
# @exclude


def check_answer(s):
    return s[::-1] == s


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def main():
    for _ in range(10000):
        s = rand_string(random.randint(1, 10))
        assert is_palindromic(s) == check_answer(s)


if __name__ == '__main__':
    main()
