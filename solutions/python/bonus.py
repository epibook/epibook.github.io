import collections
import sys
import random
import heapq


def check_ans(productivity, C):
    for i in range(len(productivity)):
        if i > 0:
            assert (
                (productivity[i] > productivity[i - 1] and C[i] > C[i - 1]) or
                (productivity[i] < productivity[i - 1] and C[i] < C[i - 1]) or
                productivity[i] == productivity[i - 1])
        if i + 1 < len(productivity):
            assert (
                (productivity[i] > productivity[i + 1] and C[i] > C[i + 1]) or
                (productivity[i] < productivity[i + 1] and C[i] < C[i + 1]) or
                productivity[i] == productivity[i + 1])


# @include
def calculate_bonus(productivity):
    # Stores (productivity, index)-pair in min_heap where ordered by
    # productivity.
    EmployeeData = collections.namedtuple('EmployeeData',
                                          ('productivity', 'index'))
    min_heap = [EmployeeData(p, i) for i, p in enumerate(productivity)]
    heapq.heapify(min_heap)

    # Initially assigns one ticket to everyone.
    tickets = [1] * len(productivity)
    # Fills tickets from lowest rating to highest rating.
    while min_heap:
        next_dev = heapq.heappop(min_heap)[1]
        # Handles the left neighbor.
        if next_dev > 0 and productivity[next_dev] > productivity[next_dev - 1]:
            tickets[next_dev] = tickets[next_dev - 1] + 1
        # Handles the right neighbor.
        if (next_dev + 1 < len(tickets) and
                productivity[next_dev] > productivity[next_dev + 1]):
            tickets[next_dev] = max(tickets[next_dev],
                                    tickets[next_dev + 1] + 1)
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
