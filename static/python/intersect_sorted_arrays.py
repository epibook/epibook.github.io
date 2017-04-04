import sys
import random
import intersect_sorted_arrays1
import intersect_sorted_arrays2
import intersect_sorted_arrays3


def main():
    for _ in range(1000):
        if len(sys.argv) == 3:
            n = int(sys.argv[1])
            m = int(sys.argv[2])
        else:
            n = random.randint(1, 10000)
            m = random.randint(1, 10000)

        A = sorted(random.randrange(n) for _ in range(n))
        B = sorted(random.randrange(m) for _ in range(m))

        res1 = intersect_sorted_arrays1.intersect_two_sorted_arrays(A, B)
        res2 = intersect_sorted_arrays2.intersect_two_sorted_arrays(A, B)
        res3 = intersect_sorted_arrays3.intersect_two_sorted_arrays(A, B)
        assert res1 == res2 == res3


if __name__ == '__main__':
    main()
