import sys
import random
import string
import collections


def rand_string(length):
    return ''.join(
        random.choice(string.ascii_lowercase) for _ in range(length))


# @include
# Uses BFS to find the least steps of transformation.
def transform_string(D, s, t):
    StringWithDistance = collections.namedtuple(
        'StringWithDistance', ('candidate_string', 'distance'))
    q = collections.deque([StringWithDistance(s, 0)])
    D.remove(s)  # Marks s as visited by erasing it in D.

    while q:
        f = q.popleft()
        # Returns if we find a match.
        if f.candidate_string == t:
            return f.distance  # Number of steps reaches t.

        # Tries all possible transformations of f.candidate_string.
        for i in range(len(f.candidate_string)):
            for c in string.ascii_lowercase:  # Iterates through 'a' ~ 'z'.
                cand = f.candidate_string[:i] + c + f.candidate_string[i + 1:]
                if cand in D:
                    D.remove(cand)
                    q.append(StringWithDistance(cand, f.distance + 1))
    return -1  # Cannot find a possible transformations.
# @exclude


def transform_string_pythonic(D, s, t):
    if s == t:
        return 0
    length = 1
    running = set([s])
    while running:
        running = D & set(cand[:i] + c + cand[i + 1:]
                          for cand in running for i in range(len(cand))
                          for c in string.ascii_lowercase)
        if t in running:
            return length
        length += 1
        D -= running
    return 0


def main():
    length = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10)
    s = rand_string(length)
    t = rand_string(length)
    n = random.randint(1, 1000000)
    D = {s, t} | {rand_string(length) for _ in range(n)}
    another_D = D.copy()
    print(s, t, len(D))
    res1 = transform_string(D, s, t)
    res2 = transform_string_pythonic(another_D, s, t)
    print(res1, res2)
    assert res1 == res2


if __name__ == '__main__':
    main()
