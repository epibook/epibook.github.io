import sys
import random


# @include
def search_entry_equal_to_its_index(A):
    left, right = 0, len(A) - 1
    while left <= right:
        mid = (left + right) // 2
        difference = A[mid] - mid
        # A[mid] == mid if and only if difference == 0.
        if difference == 0:
            return mid
        elif difference > 0:
            right = mid - 1
        else:  # difference < 0.
            left = mid + 1
    return -1
# @exclude


# O(n) way to find ans.
def check_ans(A):
    for i, a in enumerate(A):
        if a == i:
            return i
    return -1


def simple_test():
    A = [0, 1, 2, 3]
    assert -1 != search_entry_equal_to_its_index(A)
    assert 0 <= search_entry_equal_to_its_index(
        A) and search_entry_equal_to_its_index(A) <= 3
    A[0] = -1
    A[2] = 4
    A[3] = 5
    assert 1 == search_entry_equal_to_its_index(A)
    A = [0]
    assert -1 != search_entry_equal_to_its_index(A)
    A[0] = -1
    assert -1 == search_entry_equal_to_its_index(A)


def main():
    simple_test()
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
        A = random.sample(range(-999, 1000), n)
        A.sort()
        ans = search_entry_equal_to_its_index(A)
        if ans != -1:
            print('A[', ans, '] = ', A[ans], sep='')
            assert ans == A[ans]
        else:
            print('no entry where A[k] = k')
            assert ans == check_ans(A)


if __name__ == '__main__':
    main()
