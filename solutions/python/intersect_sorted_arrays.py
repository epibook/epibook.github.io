# Intersect_sorted_arrays.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random
import intersect_sorted_arrays1
import intersect_sorted_arrays2
import intersect_sorted_arrays3


def check_ans(a, b, c):
    print(len(a), len(b), len(c))
    assert a == b == c


def main():
    for _ in range(1000):
        if len(sys.argv) == 3:
            n = int(sys.argv[1])
            m = int(sys.argv[2])
        else:
            n = random.randint(1, 10000)
            m = random.randint(1, 10000)

        A = sorted(random.randrange(n) for i in range(n))
        B = sorted(random.randrange(m) for i in range(m))

        res1 = intersect_sorted_arrays1.intersect_two_sorted_arrays(A, B)
        res2 = intersect_sorted_arrays2.intersect_two_sorted_arrays(A, B)
        res3 = intersect_sorted_arrays3.intersect_two_sorted_arrays(A, B)
        check_ans(res1, res2, res3)


if __name__ == '__main__':
    main()
