import random
import sys
from longest_valid_parentheses import longest_valid_parentheses as check_answer
from longest_valid_parentheses import small_test


# @include
def longest_valid_parentheses(s):
    def parse_from_side(s, paren):
        max_length = num_parens_so_far = length = 0
        for c in s:
            if c == paren:
                num_parens_so_far, length = num_parens_so_far + 1, length + 1
            else:  # c != paren
                if num_parens_so_far <= 0:
                    num_parens_so_far = length = 0
                else:
                    num_parens_so_far -= 1
                    length += 1
                    if num_parens_so_far == 0:
                        max_length = max(max_length, length)
        return max_length

    return max(parse_from_side(s, '('), parse_from_side(reversed(s), ')'))
# @exclude


def rang_string(length):
    return ''.join('(' if random.randint(0, 1) == 0 else ')'
                   for _ in range(length))


def main():
    small_test()
    if len(sys.argv) == 2:
        s = sys.argv[1]
        print('s =', s)
        print(longest_valid_parentheses(s))
    else:
        s = rang_string(random.randint(0, 100000))
        assert check_answer(s) == longest_valid_parentheses(s)


if __name__ == '__main__':
    main()
