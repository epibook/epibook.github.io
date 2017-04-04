# @include
def is_palindrome(s):
    # i moves forward, and j moves backward.
    i, j = 0, len(s) - 1
    while i < j:
        # i and j both skip non-alphanumeric characters.
        while not s[i].isalnum() and i < j:
            i += 1
        while not s[j].isalnum() and i < j:
            j -= 1
        if s[i].lower() != s[j].lower():
            return False
        i, j = i + 1, j - 1
    return True
# @exclude


# Use O(n) additional space by storing another filtered string.
def is_palindrome_pythonic(s):
    ss = [c.lower() for c in s if c.isalnum()]
    return ss == ss[::-1]


def main():
    assert is_palindrome('A man, a plan, a canal: Panama')
    assert not is_palindrome('race a car')
    assert is_palindrome('Able was I, ere I saw Elba!')
    assert not is_palindrome('Ray a Ray')
    assert is_palindrome('')
    assert is_palindrome_pythonic('A man, a plan, a canal: Panama')
    assert not is_palindrome_pythonic('race a car')
    assert is_palindrome_pythonic('Able was I, ere I saw Elba!')
    assert not is_palindrome_pythonic('Ray a Ray')
    assert is_palindrome_pythonic('')


if __name__ == '__main__':
    main()
