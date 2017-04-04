import sys
import random


# @include
def generate_balanced_parentheses(num_pairs):
    def directed_generate_balanced_parentheses(num_left_parens_needed,
                                               num_right_parens_needed,
                                               valid_prefix,
                                               result=[]):
        if num_left_parens_needed > 0:  # Able to insert '('.
            directed_generate_balanced_parentheses(num_left_parens_needed - 1,
                                                   num_right_parens_needed,
                                                   valid_prefix + '(')
        if num_left_parens_needed < num_right_parens_needed:
            # Able to insert ')'.
            directed_generate_balanced_parentheses(num_left_parens_needed,
                                                   num_right_parens_needed - 1,
                                                   valid_prefix + ')')
        if not num_right_parens_needed:
            result.append(valid_prefix)
        return result

    return directed_generate_balanced_parentheses(num_pairs, num_pairs, '')
# @exclude


def generate_balanced_parentheses_pythonic(num_pairs, num_left_open=0):
    if not num_pairs:
        return [')' * num_left_open]
    if not num_left_open:
        return [
            '(' + p
            for p in generate_balanced_parentheses_pythonic(num_pairs - 1,
                                                            num_left_open + 1)
        ]
    else:
        return ([
            '(' + p
            for p in generate_balanced_parentheses_pythonic(num_pairs - 1,
                                                            num_left_open + 1)
        ] + [
            ')' + p
            for p in generate_balanced_parentheses_pythonic(num_pairs - 1,
                                                            num_left_open - 1)
        ])


def small_test():
    assert generate_balanced_parentheses(1) == ['()']
    result = generate_balanced_parentheses(2)
    assert result == ['(())', '()()'] or result == ['()()', '(())']


def main():
    small_test()
    num_pairs = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(0,
                                                                           10)
    print('num_pairs =', num_pairs)
    result = generate_balanced_parentheses(num_pairs)
    print(*result)


if __name__ == '__main__':
    main()
