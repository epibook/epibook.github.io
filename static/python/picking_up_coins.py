import itertools
import sys
import random


# @include
def maximum_revenue(coins):
    def compute_maximum_revenue_for_range(a, b):
        if a > b:
            # No coins left.
            return 0

        if maximum_revenue_for_range[a][b] == 0:
            max_revenue_a = coins[a] + min(
                compute_maximum_revenue_for_range(a + 2, b),
                compute_maximum_revenue_for_range(a + 1, b - 1))
            max_revenue_b = coins[b] + min(
                compute_maximum_revenue_for_range(a + 1, b - 1),
                compute_maximum_revenue_for_range(a, b - 2))
            maximum_revenue_for_range[a][b] = max(max_revenue_a, max_revenue_b)
        return maximum_revenue_for_range[a][b]

    maximum_revenue_for_range = [[0] * len(coins) for _ in coins]
    return compute_maximum_revenue_for_range(0, len(coins) - 1)
# @exclude


def maximum_revenue_alternative(coins):
    def maximum_revenue_alternative_helper(a, b):
        if a > b:
            return 0
        elif a == b:
            return coins[a]

        if maximum_revenue_for_range[a][b] == -1:
            maximum_revenue_for_range[a][b] = max(
                coins[a] + prefix_sum[b] -
                (prefix_sum[a]
                 if a + 1 > 0 else 0) - maximum_revenue_alternative_helper(
                     a + 1, b), coins[b] + prefix_sum[b - 1] -
                (prefix_sum[a - 1] if a > 0 else 0
                 ) - maximum_revenue_alternative_helper(a, b - 1))
        return maximum_revenue_for_range[a][b]

    prefix_sum = list(itertools.accumulate(coins))
    maximum_revenue_for_range = [[-1] * len(coins) for _ in coins]
    return maximum_revenue_alternative_helper(0, len(coins) - 1)


def greedy(coins):
    def greedy_helper(start, end):
        if start > end:
            return 0

        if coins[start] > coins[end]:
            gain = coins[start]
            if coins[start + 1] > coins[end]:
                gain += greedy_helper(start + 2, end)
            else:
                gain += greedy_helper(start + 1, end - 1)
        else:
            gain = coins[end]
            if coins[start] > coins[end - 1]:
                gain += greedy_helper(start + 1, end - 1)
            else:
                gain += greedy_helper(start, end - 2)
        return gain

    return greedy_helper(0, len(coins) - 1)


def simple_test():
    coins = [25, 5, 10, 5, 10, 5, 10, 25, 1, 25, 1, 25, 1, 25, 5, 10]
    assert 140 == maximum_revenue(coins)
    assert maximum_revenue_alternative(coins) == maximum_revenue(coins)
    assert 120 == greedy(coins)


def main():
    simple_test()
    if len(sys.argv) >= 2:
        coins = [int(i) for i in sys.argv[1:]]
    else:
        coins = list(range(random.randint(0, 99)))
    print(coins)
    print(maximum_revenue(coins))
    assert maximum_revenue(coins) == maximum_revenue_alternative(coins)


if __name__ == '__main__':
    main()
