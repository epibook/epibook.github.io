import math
import random


# @include
def house_majority(prob, n):
    # prob is the probability that each Republican wins.
    # r is the number of Republicans wins, and n is the number of elections.
    def house_majority_helper(prob, r, n, P):
        if r > n:
            return 0.0  # Base case: not enough Republicans.
        elif r == 0 and n == 0:
            return 1.0  # Base case.
        elif r < 0:
            return 0.0

        if P[r][n] == -1.0:
            P[r][n] = (house_majority_helper(prob,
                                             r - 1,
                                             n - 1,
                                             P) * prob[n - 1] + house_majority_helper(prob,
                                                                                      r,
                                                                                      n - 1,
                                                                                      P) * (1.0 - prob[n - 1]))
        return P[r][n]
    # Initializes DP table.
    P = [[-1.0] * (n + 1) for i in range(n + 1)]

    # Accumulates the probabilities of majority cases.
    prob_sum = 0.0
    for r in range(math.ceil(0.5 * n), n + 1):
        prob_sum += house_majority_helper(prob, r, n, P)
    return prob_sum
# @exclude


def print_vector(prob):
    prob.sort()
    for i, p in enumerate(prob):
        print('%d:%d' % (i, 100 * p), end='\n' if (i + 1) % 10 == 0 else ' ')
    print()


def test():
    n = 1
    prob = [random.random() for _ in range(n)]
    ans = house_majority(prob, n)
    assert ans == prob[0]


def main():
    test()
    n = 435
    prob = [random.random() for _ in range(n)]
    print_vector(prob)
    ans = house_majority(prob, n)
    assert 0.0 <= ans <= 1.0
    print(ans)


if __name__ == '__main__':
    main()
