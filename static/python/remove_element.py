import sys
import random


# @include
# Returns the number of valid entries after deletion.
def delete_key(k, A):
    write_idx = 0
    for a in A:
        if a != k:
            A[write_idx] = a
            write_idx += 1
    return write_idx
# @exclude


def delete_key_pythonic(k, A):
    A[:] = [a for a in A if a != k]
    return len(A)


def check_ans(A, n, k):
    for i in range(n):
        assert A[i] != k


def main():
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(0, 10000)
    for _ in range(1000):
        A = [random.randint(-1000, 1000) for _ in range(n)]
        copy_A = A.copy()
        target = random.randint(-1000, 1000)
        size = delete_key(target, A)
        print('size =', size, 'key =', target)
        check_ans(A, size, target)
        delete_key_pythonic(target, copy_A)
        print(len(copy_A))
        assert size == len(copy_A)


if __name__ == '__main__':
    main()
