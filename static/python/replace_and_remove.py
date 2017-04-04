import copy
import sys
import random


# @include
def replace_and_remove(size, s):
    # Forward iteration: remove 'b's and count the number of 'a's.
    write_idx, a_count = 0, 0
    for i in range(size):
        if s[i] != 'b':
            s[write_idx] = s[i]
            write_idx += 1
        if s[i] == 'a':
            a_count += 1

    # Backward iteration: replace 'a's with 'dd's starting from the end.
    cur_idx = write_idx - 1
    write_idx += a_count - 1
    final_size = write_idx + 1
    while cur_idx >= 0:
        if s[cur_idx] == 'a':
            s[write_idx - 1:write_idx + 1] = 'dd'
            write_idx -= 2
        else:
            s[write_idx] = s[cur_idx]
            write_idx -= 1
        cur_idx -= 1
    return final_size
# @exclude


def rand_string(length):
    return [random.choice('abcd') for _ in range(length)]


def check_ans(s, ans):
    temp = []
    for i in s:
        if i == 'a':
            temp += ['d', 'd']
        elif i != 'b':
            temp.append(i)
    assert ans == temp


def main():
    for _ in range(1000):
        if len(sys.argv) == 2:
            s = list(sys.argv[1])
        else:
            s = rand_string(random.randint(1, 1000))
        print(''.join(s))
        s_copy = copy.deepcopy(s)
        s_copy += [0] * len(s)
        final_size = replace_and_remove(len(s), s_copy)
        check_ans(s, s_copy[:final_size])


if __name__ == '__main__':
    main()
