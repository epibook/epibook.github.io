import sys
import random
import string


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


# @include
def decompose_into_dictionary_words(domain, dictionary):
    # When the algorithm finishes, last_length[i] != -1 indicates domain[:i +
    # 1] has a valid decomposition, and the length of the last string in the
    # decomposition is last_length[i].
    last_length = [-1] * len(domain)
    for i in range(len(domain)):
        # If domain[:i + 1] is a dictionary word, set last_length[i] to the
        # length of that word.
        if domain[:i + 1] in dictionary:
            last_length[i] = i + 1

        # If last_length[i] = -1 look for j < i such that domain[: j + 1] has a
        # valid decomposition and domain[j + 1:i + 1] is a dictionary word. If
        # so, record the length of that word in last_length[i].
        if last_length[i] == -1:
            for j in range(i):
                if last_length[j] != -1 and domain[j + 1:i + 1] in dictionary:
                    last_length[i] = i - j
                    break

    decompositions = []
    if last_length[-1] != -1:
        # domain can be assembled by dictionary words.
        idx = len(domain) - 1
        while idx >= 0:
            decompositions.append(domain[idx + 1 - last_length[idx]:idx + 1])
            idx -= last_length[idx]
        decompositions = decompositions[::-1]
    return decompositions
# @exclude


# Verify the strings in ans can be assembled into s.
def check_ans(s, ans):
    print(s)
    print(*ans)
    assert not ans or s == ''.join(ans)


def small_case():
    dictionary = {'bed', 'bath', 'and', 'hand', 'beyond'}
    ans = decompose_into_dictionary_words('bedbathandbeyond', dictionary)
    golden_ans = ['bed', 'bath', 'and', 'beyond']
    check_ans('bedbathandbeyond', ans)
    assert ans == golden_ans

    dictionary = {'aa', 'b', 'ccc'}
    ans = decompose_into_dictionary_words('b', dictionary)
    golden_ans = ['b']
    check_ans('b', ans)
    assert ans == golden_ans

    ans = decompose_into_dictionary_words('ccc', dictionary)
    golden_ans = ['ccc']
    check_ans('ccc', ans)
    assert ans == golden_ans

    ans = decompose_into_dictionary_words('aabccc', dictionary)
    golden_ans = ['aa', 'b', 'ccc']
    check_ans('aabccc', ans)
    assert ans == golden_ans

    ans = decompose_into_dictionary_words('baabccc', dictionary)
    golden_ans = ['b', 'aa', 'b', 'ccc']
    check_ans('baabccc', ans)
    assert ans == golden_ans

    dictionary.add('bb')
    ans = decompose_into_dictionary_words('bbb', dictionary)
    # Note: golden_ans relies on how our algorithm is implemented: our
    # algorithm chooses longest word ending at that index, so the answer
    # is 'b', 'bb', not 'b', 'b', 'b' or 'bb', 'b'.
    golden_ans = ['b', 'bb']
    check_ans('bbb', ans)
    assert ans == golden_ans

    ans = decompose_into_dictionary_words('bbcccb', dictionary)
    golden_ans = ['bb', 'ccc', 'b']
    check_ans('bbcccb', ans)
    assert ans == golden_ans

    ans = decompose_into_dictionary_words('bbcccbabb', dictionary)
    golden_ans = []
    assert ans == golden_ans

    ans = decompose_into_dictionary_words('d', dictionary)
    golden_ans = []
    assert ans == golden_ans


def main():
    small_case()
    for _ in range(1000):
        if len(sys.argv) >= 3:
            target = sys.argv[1]
            dictionary = set(sys.argv[2:])
        elif len(sys.argv) == 2:
            target = sys.argv[1]
            n = random.randint(1, 10000)
            dictionary = {rand_string(random.randint(1, 15)) for i in range(n)}
        else:
            target = rand_string(random.randint(1, 50))
            n = random.randint(1, 10000)
            dictionary = {rand_string(random.randint(1, 15)) for i in range(n)}
        ans = decompose_into_dictionary_words(target, dictionary)
        check_ans(target, ans)
        if len(sys.argv) >= 3:
            break


if __name__ == '__main__':
    main()
