import sys
import random
import math


# @include
def is_door_open(i):
    return math.sqrt(i).is_integer()
# @exclude


def check_answer(n):
    doors = [False] * (n + 1)  # False means closed door.
    for i in range(1, n + 1):
        start = 0
        while start + i <= n:
            start += i
            doors[start] = not doors[start]

    for i in range(1, n + 1):
        assert doors[i] == is_door_open(i)


def main():
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
    check_answer(n)


if __name__ == '__main__':
    main()
