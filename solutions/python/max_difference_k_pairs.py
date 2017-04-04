import sys
import random


# @include
def max_k_pairs_profits(prices, k):
    if not k:
        return 0.0
    elif 2 * k >= len(prices):
        return sum(max(0, b - a) for a, b in zip(prices[:-1], prices[1:]))
    min_prices, max_profits = [float('inf')] * k, [0] * k
    for price in prices:
        for i in reversed(list(range(k))):
            max_profits[i] = max(max_profits[i], price - min_prices[i])
            min_prices[i] = min(min_prices[i], price -
                                (0 if i == 0 else max_profits[i - 1]))
    return max_profits[-1]
# @exclude


# O(n^k) checking answer.
def check_ans_helper(A, l, k, pre, ans, max_ans):
    if l == k:
        return max(max_ans, ans)
    else:
        for i in range(pre, len(A)):
            max_ans = check_ans_helper(A, l + 1, k, i + 1,
                                       ans + (A[i]
                                              if l & 1 else -A[i]), max_ans)
        return max_ans


def check_ans(A, k):
    ans = 0
    max_ans = float('-inf')

    max_ans = check_ans_helper(A, 0, 2 * k, 0, ans, max_ans)
    print('max_ans =', max_ans)
    return max_ans


def main():
    n = 30
    k = 4
    # random tests n = 30, k = 4 for 100 times
    for _ in range(100):
        A = [random.randint(0, 99) for _ in range(n)]
        print('n = %d, k = %d' % (n, k))
        print(max_k_pairs_profits(A, k))
        assert check_ans(A, k) == max_k_pairs_profits(A, k)

    if len(sys.argv) == 2:
        n = int(sys.argv[1])
        k = random.randint(1, n // 2)
    elif len(sys.argv) == 3:
        n = int(sys.argv[1])
        k = int(sys.argv[2])
    else:
        n = random.randint(1, 10000)
        k = random.randint(1, n // 2)
    A = [random.randint(0, 99) for _ in range(n)]
    print('n = %d, k = %d' % (n, k))
    print(max_k_pairs_profits(A, k))


if __name__ == '__main__':
    main()
