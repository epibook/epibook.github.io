# @include
def is_match(regex, s):
    def is_match_here(regex, s):
        if not regex:
            # Case (1.): Empty regex matches all strings.
            return True

        if regex == '$':
            # Case (2.): Reach the end of regex, and last char is '$'.
            return not s

        if len(regex) >= 2 and regex[1] == '*':
            # Case (3.): A '*' match.
            # Iterate through s, checking '*' condition, if '*' condition holds,
            # performs the remaining checks.
            i = 1
            while i < len(s) and regex[0] in ('.', s[i - 1]):
                if is_match_here(regex[2:], s[i:]):
                    return True
                i += 1
            # See '*' matches zero character in s[:len(s)].
            return is_match_here(regex[2:], s)

        # Case (4.): regex begins with single character match.
        return s and regex[0] in ('.', s[0]) and is_match_here(regex[1:], s[1:])

    # Case (2.): regex starts with '^'.
    if regex[0] == '^':
        return is_match_here(regex[1:], s)
    return any(is_match_here(regex, s[i:]) for i in range(len(s) + 1))
# @exclude


def main():
    assert is_match('.', 'a')
    assert is_match('a', 'a')
    assert not is_match('a', 'b')
    assert is_match('a.9', 'aW9')
    assert not is_match('a.9', 'aW19')
    assert is_match('^a.9', 'aW9')
    assert not is_match('^a.9', 'baW19')
    assert is_match('.*', 'a')
    assert is_match('.*', '')
    assert is_match('c*', 'c')
    assert not is_match('aa*', 'c')
    assert is_match('ca*', 'c')
    assert is_match('.*', 'asdsdsa')
    assert is_match('9$', 'xxxxW19')

    assert is_match('.*a', 'ba')

    assert is_match('.*a', 'ba')
    assert is_match('^a.*9$', 'axxxxW19')

    assert is_match('^a.*W19$', 'axxxxW19')
    assert is_match('.*a.*W19', 'axxxxW19123')
    assert not is_match('.*b.*W19', 'axxxxW19123')
    assert is_match('n.*', 'n')
    assert is_match('a*n.*', 'an')
    assert is_match('a*n.*', 'ana')
    assert is_match('a*n.*W19', 'anaxxxxW19123')
    assert is_match('.*a*n.*W19', 'asdaaadnanaxxxxW19123')
    assert is_match('.*.*.a*n.*W19', 'asdaaadnanaxxxxW19123')


if __name__ == '__main__':
    main()
