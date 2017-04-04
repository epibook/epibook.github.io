import sys
import collections
import random
import string


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


# @include
def find_shortest_prefix(s, D):
    class Trie:

        class TrieNode:

            def __init__(self):
                self.is_string = False
                self.leaves = collections.defaultdict(Trie.TrieNode)

        def __init__(self):
            self._root = self.TrieNode()

        def insert(self, s):
            p = self._root
            for c in s:
                p = p.leaves[c]

            # s already existed in this trie.
            if p.is_string:
                return False
            else:  # p.is_string == False.
                p.is_string = True  # Inserts s into this trie.
                return True

        def get_shortest_unique_prefix(self, s):
            p = self._root
            prefix = []
            for c in s:
                prefix.append(c)
                if c not in p.leaves:
                    return ''.join(prefix)
                p = p.leaves[c]
            return ''

    # Builds a trie according to given dictionary D.
    T = Trie()
    for word in D:
        T.insert(word)
    return T.get_shortest_unique_prefix(s)
# @exclude


def check_ans(s, D):
    length = 0
    for it in D:
        for i in range(min(len(s), len(it))):
            if s[i] != it[i]:
                break
        else:
            i += 1
        if i > length:
            length = i
    if length == len(s):
        return ''
    else:
        return s[:length + 1]


def main():
    for _ in range(100):
        s = sys.argv[1] if len(
            sys.argv) == 2 else rand_string(
            random.randint(
                1, 10))
        n = random.randint(1, 10000)
        D = {rand_string(random.randint(1, 10)) for i in range(n)}
        print(s, 'prefix =', find_shortest_prefix(s, D))
        assert find_shortest_prefix(s, D) == check_ans(s, D)


if __name__ == '__main__':
    main()
