# Reservoir_sampling.cpp b4b3a70d8ab942579f85b4416f980d05831af969
import sys
import random
import itertools


# @include
# Assumption: there are at least k elements in the stream.
def online_random_sample(it, k):
    # Stores the first k elements.
    sampling_results = list(itertools.islice(it, k))

    # Have read the first k elements.
    num_seen_so_far = k
    for x in it:
        num_seen_so_far += 1
        # Generate a random number in [0, num_seen_so_far - 1], and if this
        # number is in [0, k - 1], we replace that element from the sample with
        # x.
        idx_to_replace = random.randrange(num_seen_so_far)
        if idx_to_replace < k:
            sampling_results[idx_to_replace] = x
    return sampling_results
# @exclude


def main():
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
        k = random.randint(1, n)
    elif len(sys.argv) == 3:
        n = int(sys.argv[1])
        k = int(sys.argv[2])
    else:
        n = random.randint(1, 99999)
        k = random.randint(1, n)
    print(n, k)
    A = range(n)
    ans = online_random_sample(iter(A), k)
    assert len(ans) == k
    # print(*ans)


if __name__ == '__main__':
    main()
