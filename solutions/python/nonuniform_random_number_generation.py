import sys
import random
import collections
import itertools
import bisect


# @include
def nonuniform_random_number_generation(values, probabilities):
    prefix_sum_of_probabilities = (
        [0.0] + list(itertools.accumulate(probabilities)))
    interval_idx = bisect.bisect(prefix_sum_of_probabilities,
                                 random.random()) - 1
    return values[interval_idx]
# @exclude


def main():
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 50)
    T = [float(i) for i in range(n)]
    P = []
    full_prob = 1.0
    for i in range(n - 1):
        pi = random.uniform(0.0, full_prob)
        P.append(pi)
        full_prob -= pi
    P.append(full_prob)
    print(*T)
    print(*P)
    print(nonuniform_random_number_generation(T, P))
    # Test. Perform the nonuniform random number generation for n * k_times
    # times and calculate the distribution of each bucket.
    k_times = 100000
    counts = collections.Counter(
        int(nonuniform_random_number_generation(T, P))
        for _ in range(n * k_times))
    for i in range(n):
        print(counts[i] / (n * k_times), P[i])
        assert abs(counts[i] / (n * k_times) - P[i]) < 0.01


if __name__ == '__main__':
    main()
