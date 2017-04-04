import sys
import random
import string


# @include
def is_interleaving_string(s1, s2, s3):
    # Early return if |s1| + |s2| != |s3|.
    if len(s1) + len(s2) != len(s3):
        return False

    T = [[False] * (len(s2) + 1) for i in range(len(s1) + 1)]
    T[0][0] = True  # Base case.
    # Uses chars from s1 only to match s3.
    for i in range(len(s1)):
        if s1[i] == s3[i]:
            T[i + 1][0] = True
        else:
            break
    # Uses chars from s2 only to match s3.
    for j in range(len(s2)):
        if s2[j] == s3[j]:
            T[0][j + 1] = True
        else:
            break

    for i in range(len(s1)):
        for j in range(len(s2)):
            T[i + 1][j + 1] = ((T[i][j + 1] and s1[i] == s3[i + j + 1]) or
                               (T[i + 1][j] and s2[j] == s3[i + j + 1]))
    return T[len(s1)][len(s2)]
# @exclude


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def small_test():
    assert is_interleaving_string('aabcc', 'dbbca', 'aadbbcbcac')
    assert not is_interleaving_string('aabcc', 'dbbca', 'aadbbbaccc')
    assert is_interleaving_string('aabaac', 'aadaaeaaf', 'aadaaeaabaafaac')
    assert is_interleaving_string('bbc', 'acaab', 'abcbcaab')


def main():
    small_test()
    if len(sys.argv) == 4:
        s1, s2, s3 = sys.argv[1:]
        print(s1, s2, s3)
        print(is_interleaving_string(s1, s2, s3))
    else:
        s1 = rand_string(random.randint(1, 100))
        s2 = rand_string(random.randint(1, 100))
        s3 = rand_string(len(s1) + len(s2))
        print(s1, s2, s3)
        print(is_interleaving_string(s1, s2, s3))


if __name__ == '__main__':
    main()
