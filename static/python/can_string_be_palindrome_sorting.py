import itertools


# @include
def can_string_be_a_palindrome(s):
    s = ''.join(sorted(s))
    odd_count, num_curr_char = 0, 1

    for i in range(1, len(s)):
        if s[i] != s[i - 1]:
            if num_curr_char & 1:
                odd_count += 1
            num_curr_char = 1
            if odd_count > 1:
                break
        else:
            num_curr_char += 1
    if num_curr_char & 1:
        odd_count += 1

    # A string can be permuted as a palindrome if the number of odd time chars
    # <= 1.
    return odd_count <= 1
# @exclude


def can_string_be_a_palindrome_pythonic(s):
    return sum(len(list(group)) & 1 for _,
               group in itertools.groupby(sorted(s))) <= 1
