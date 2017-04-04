import sys
import random


# @include
def buy_and_sell_stock_once(prices):
    min_price_so_far, max_profit = float('inf'), 0.0
    for price in prices:
        max_profit_sell_today = price - min_price_so_far
        max_profit = max(max_profit, max_profit_sell_today)
        min_price_so_far = min(min_price_so_far, price)
    return max_profit
# @exclude


def check_ans(h):
    """O(n^2) checking answer."""
    return max([h[i] - h[j] for i in range(1, len(h)) for j in range(i)],
               default=0)


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 1000)
        a = [random.uniform(0, n) for _ in range(n)]
        print(buy_and_sell_stock_once(a))
        assert check_ans(a) == buy_and_sell_stock_once(a)


if __name__ == '__main__':
    main()
