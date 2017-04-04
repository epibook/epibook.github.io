import collections
import sys
import random


# @include
def even_odd(A):
    next_even, next_odd = 0, len(A) - 1
    while next_even < next_odd:
        if A[next_even] % 2 == 0:
            next_even += 1
        else:
            A[next_even], A[next_odd] = A[next_odd], A[next_even]
            next_odd -= 1
# @exclude


def test(A):
    even = collections.Counter(a for a in A if a % 2 == 0)
    odd = collections.Counter(a for a in A if a % 2 == 1)
    even_odd(A)
    in_odd = False
    for a in A:
        if a % 2 == 0:
            even[a] -= 1
            assert not in_odd
        else:
            odd[a] -= 1
            in_odd = True
    assert len(list(even.elements())) == 0 and len(list(odd.elements())) == 0


def main():
    for _ in range(10000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 20)
        A = [random.randint(0, 20) for _ in range(n)]
        test(A)


if __name__ == '__main__':
    main()
