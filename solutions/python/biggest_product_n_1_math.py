import functools
import sys
import random


# @include
def find_biggest_n_minus_one_product(A):
    number_of_negatives = 0
    least_nonnegative_idx = least_negative_idx = greatest_negative_idx = None

    # Identify the least negative, greatest negative, and least nonnegative
    # entries.
    for i, a in enumerate(A):
        if a < 0:
            number_of_negatives += 1
            if least_negative_idx is None or A[least_negative_idx] < a:
                least_negative_idx = i
            if greatest_negative_idx is None or a < A[greatest_negative_idx]:
                greatest_negative_idx = i
        else:  # a >= 0.
            if least_nonnegative_idx is None or a < A[least_nonnegative_idx]:
                least_nonnegative_idx = i

    idx_to_skip = (least_negative_idx
                   if number_of_negatives % 2 else least_nonnegative_idx if
                   least_nonnegative_idx is not None else greatest_negative_idx)
    return functools.reduce(lambda product, a: product * a,
                            # Use a generator rather than list comprehension to
                            # avoid extra space.
                            (a for i, a in enumerate(A) if i != idx_to_skip), 1)
# @exclude


# n^2 checking.
def check_ans(A):
    max_product = float('-inf')
    for i in range(len(A)):
        product = 1
        for j in range(i):
            product *= A[j]
        for j in range(i + 1, len(A)):
            product *= A[j]
        if product > max_product:
            max_product = product
    print(max_product)
    return max_product


def main():
    for _ in range(10000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(2, 11)
        A = [random.randint(-9, 9) for _ in range(n)]
        print(*A)
        res = find_biggest_n_minus_one_product(A)
        print(res)
        assert res == check_ans(A)


if __name__ == '__main__':
    main()
