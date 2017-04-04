import sys
import random
import itertools


# @include
def look_and_say(n):
    def next_number(s):
        result, i = [], 0
        while i < len(s):
            count = 1
            while i + 1 < len(s) and s[i] == s[i + 1]:
                i += 1
                count += 1
            result.append(str(count) + s[i])
            i += 1
        return ''.join(result)

    s = '1'
    for _ in range(1, n):
        s = next_number(s)
    return s


# Pythonic solution uses the power of itertools.groupby().
def look_and_say_pythonic(n):
    s = '1'
    for _ in range(n - 1):
        s = ''.join(
            str(len(list(group))) + key for key, group in itertools.groupby(s))
    return s
# @exclude


def main():
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
    else:
        n = random.randint(1, 50)
    assert look_and_say(1) == '1'
    assert look_and_say(2) == '11'
    assert look_and_say(3) == '21'
    assert look_and_say(5) == '111221'
    assert look_and_say(6) == '312211'
    assert look_and_say(7) == '13112221'
    assert look_and_say(8) == '1113213211'
    print('n =', n)
    print(look_and_say(n))
    assert look_and_say(n) == look_and_say_pythonic(n)


if __name__ == '__main__':
    main()
