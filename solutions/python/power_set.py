# Power_set.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import math
import random


# @include
def generate_power_set(S):
    for i in range(1 << len(S)):
        x = i
        while x:
            tar = int(math.log2(x & ~(x - 1)))
            print(S[tar], end='')
            x &= x - 1
            if x:
                print(',', end='')
        print()
# @exclude


def main():
    if len(sys.argv) >= 2:
        S = [int(i) for i in sys.argv[1:]]
    else:
        S = list(range(random.randint(1, 10)))
    generate_power_set(S)


if __name__ == '__main__':
    main()
