import sys
import random
import string
import collections


# @include
def highest_affinity_pair(ifs):
    # Creates a mapping from pages to distinct users.
    page_users_map = collections.defaultdict(set)
    for line in ifs:
        page, user = line.split()
        page_users_map[page].add(user)

    result = None, None
    max_count = 0
    page_users_map = list(page_users_map.items())
    # Compares all pairs of pages to users maps.
    for a in range(len(page_users_map)):
        for b in range(a + 1, len(page_users_map)):
            intersect_users = page_users_map[a][1] & page_users_map[b][1]

            # Updates result if we find larger intersection.
            if len(intersect_users) > max_count:
                max_count = len(intersect_users)
                result = page_users_map[a][0], page_users_map[b][0]
    return result
# @exclude


def simple_test():
    with open('logs.txt', 'w') as ofs:
        ofs.write('''a A
b B
c A
''')
    with open('logs.txt') as ifs:
        result = highest_affinity_pair(ifs)
    assert result == ('a', 'c') or result == ('c', 'a')


def rand_string(length):
    ret = (random.choice(string.ascii_lowercase) for i in range(length))
    return ''.join(ret)


def main():
    simple_test()
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
    else:
        n = random.randint(1, 10000)
    with open('logs.txt', 'w') as ofs:
        for i in range(n):
            name = rand_string(5).upper()
            print(name, rand_string(5), file=ofs)
    with open('logs.txt') as ifs:
        result = highest_affinity_pair(ifs)
        print(result[0], result[1])


if __name__ == '__main__':
    main()
