import sys
import random
from bonus import check_ans


# @include
def calculate_bonus(productivity):
    # Initially assigns one ticket to everyone.
    tickets = [1] * len(productivity)
    # From left to right.
    for i in range(1, len(productivity)):
        if productivity[i] > productivity[i - 1]:
            tickets[i] = tickets[i - 1] + 1
    # From right to left.
    for i in reversed(range(len(productivity) - 1)):
        if productivity[i] > productivity[i + 1]:
            tickets[i] = max(tickets[i], tickets[i + 1] + 1)
    return tickets
# @exclude


def small_test():
    A = [1, 2, 2]
    golden_A = [1, 2, 1]
    assert calculate_bonus(A) == golden_A
    A = [1, 2, 3, 2, 1]
    golden_A = [1, 2, 3, 2, 1]
    assert calculate_bonus(A) == golden_A
    A = [300, 400, 500, 200]
    golden_A = [1, 2, 3, 1]
    assert calculate_bonus(A) == golden_A


def main():
    small_test()
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
        ratings = [random.randint(1, 10000) for _ in range(n)]
        T = calculate_bonus(ratings)
        check_ans(ratings, T)


if __name__ == '__main__':
    main()
