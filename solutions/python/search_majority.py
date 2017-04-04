import sys
import random
import string


# @include
def majority_search(input_stream):
    candidate, candidate_count = None, 0
    for it in input_stream:
        if candidate_count == 0:
            candidate, candidate_count = it, candidate_count + 1
        elif candidate == it:
            candidate_count += 1
        else:
            candidate_count -= 1
    return candidate
# @exclude


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def check_ans(stream, ans):
    stream.sort()
    candidate_count = 1
    find = False
    for i in range(1, len(stream)):
        if stream[i] != stream[i - 1]:
            if candidate_count * 2 >= len(stream):
                assert ans == stream[i - 1]
                find = True
            candidate_count = 1
        else:
            candidate_count += 1
    if candidate_count * 2 >= len(stream):
        assert ans == stream[-1]
        find = True
    assert find


def main():
    for _ in range(10000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
        stream = [rand_string(random.randint(1, 5)) for _ in range(n)]
        # generate the majority
        stream += [stream[-1]] * n
        input_stream = iter(stream)
        ret = majority_search(input_stream)
        print(ret)
        check_ans(stream, ret)


if __name__ == '__main__':
    main()
