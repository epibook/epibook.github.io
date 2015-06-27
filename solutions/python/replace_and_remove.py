# Replace_and_remove.cc c49e1253eabe0a3ae66bcdfda69fd5c045b18077
import random
import string

# @include
def replace_and_remove(s):
    write_idx, a_count = 0, 0
    for c in s:
        if c != 'b':
            s[write_idx] = c
            write_idx += 1
        if c == 'a':
            a_count += 1
    # resize
    num_required_padding = a_count - (len(s) - write_idx)
    if num_required_padding < 0:
        s = s[:(write_idx + a_count)]
    else:
        s.extend([' ', ] * (num_required_padding))
    cur_idx = write_idx - 1
    write_idx = len(s) - 1
    while cur_idx >= 0:
        if s[cur_idx] == 'a':
            s[write_idx - 1:write_idx + 1] = ['d', ] * 2
            write_idx -= 2
        else:
            s[write_idx] = s[cur_idx]
            write_idx -= 1
        cur_idx -= 1
    return ''.join(s)

# @exclude
def main():
    for _ in range(10000):
        string_length = random.randint(1, 100)
        random_char_list = [random.choice(string.ascii_lowercase)\
                           for _ in range(string_length)]
        random_string = ''.join(random_char_list)
        assert replace_and_remove(random_char_list) ==\
               random_string.replace('a', 'dd').replace('b', '')

if __name__ == '__main__':
    main()
