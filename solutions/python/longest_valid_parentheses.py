import random
import sys


# @include
def longest_valid_parentheses(s):
    max_length, end, left_parentheses_indices = 0, -1, []
    for i, c in enumerate(s):
        if c == '(':
            left_parentheses_indices.append(i)
        elif not left_parentheses_indices:
            end = i
        else:
            left_parentheses_indices.pop()
            start = (left_parentheses_indices[-1]
                     if left_parentheses_indices else end)
            max_length = max(max_length, i - start)
    return max_length
# @exclude


def longest_valid_parentheses_constant_space(s):
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


def small_test():
    assert longest_valid_parentheses(')(((())()(()(') == 6
    assert longest_valid_parentheses('((())()(()(') == 6
    assert longest_valid_parentheses(')(') == 0
    assert longest_valid_parentheses('()') == 2
    assert longest_valid_parentheses('') == 0
    assert longest_valid_parentheses('()()())') == 6
    assert longest_valid_parentheses_constant_space(')(((())()(()(') == 6
    assert longest_valid_parentheses_constant_space('((())()(()(') == 6
    assert longest_valid_parentheses_constant_space(')(') == 0
    assert longest_valid_parentheses_constant_space('()') == 2
    assert longest_valid_parentheses_constant_space('') == 0
    assert longest_valid_parentheses_constant_space('()()())') == 6


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
        for _ in range(1000):
            s = rang_string(random.randint(0, 100000))
            assert longest_valid_parentheses_constant_space(
                s) == longest_valid_parentheses(s)


if __name__ == '__main__':
    main()
