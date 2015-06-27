# Offline_sampling.cc
import random
import numpy
import count_inversions

# @include
def offline_sampling(A, k):
    for i in range(k):
        rand = random.randint(i, len(A) - 1)
        A[i], A[rand] = A[rand], A[i]
    return A[:k]

# sample from A 5 times and get counts of duplicates among sampled results.
def test_sampling(A, k, duplicate_counts):
    duplicate_count = 0
    samples = set()
    for __ in range(5):
        sample = tuple(sorted(offline_sampling(A, k)))
        print("Sampling:", sample)
        if sample in samples:
            duplicate_count += 1
        samples.add(sample)
    duplicate_counts.append(duplicate_count)

# permutation is offline_sampling for k = len(A) case.
# generate a random permuatation and get counts of inversions within it.
# since input A is sorted, initially there is no inversion in A.
def test_permutation(A, inversion_counts):
    offline_sampling(A, len(A))
    print("Permutation:", A)
    inversion_count = count_inversions.count_inversions(A)
    inversion_counts.append(inversion_count)

# @exclude
def main():
    duplicate_counts = []
    inversion_counts = []
    for _ in range(30):
        A = [i for i in range(random.randint(10, 30))]
        print(A)
        k = random.randint(5, int(len(A) / 2))
        test_sampling(A, k, duplicate_counts)
        test_permutation(A, inversion_counts)

    assert numpy.mean(inversion_counts) / len(A) > 1
    assert numpy.mean(duplicate_counts) < 0.3

if __name__ == '__main__':
    main()
