import sys


# @include
def is_well_formed(s):
    left_chars, lookup = [], {'(': ')', '{': '}', '[': ']'}
    for c in s:
        if c in lookup:
            left_chars.append(c)
        elif not left_chars or lookup[left_chars.pop()] != c:
            # Unmatched right char or mismatched chars.
            return False
    return not left_chars
# @exclude


def small_test():
    assert is_well_formed('()')
    assert is_well_formed('()[]{}')
    assert is_well_formed('[()[]]{}')
    assert is_well_formed('(()[]{()[]{}{}})')
    assert not is_well_formed('([)]')
    assert not is_well_formed('[')
    assert not is_well_formed('(()[]{()[]{})({}})')


def main():
    small_test()
    if len(sys.argv) == 2:
        print(is_well_formed(sys.argv[1]))


if __name__ == '__main__':
    main()
