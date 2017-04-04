import sys
import random
import longest_nondecreasing_subsequence_n2 as LNS_nlogn
import longest_nondecreasing_subsequence_nlogn as LNS_n2


def main():
    for times in range(1000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 10000)
        A = [random.randrange(100000000) for i in range(n)]
        print('n =', n)
        ret_length = LNS_nlogn.longest_nondecreasing_subsequence_length(A)
        another_length = LNS_n2.longest_nondecreasing_subsequence_length(A)
        assert ret_length == another_length


if __name__ == '__main__':
    main()
