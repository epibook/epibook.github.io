import sys
import random
import itertools
import operator


# @include
def find_biggest_n_minus_one_product(A):
    # Builds suffix products.
    suffix_products = list(
        reversed(list(itertools.accumulate(reversed(A), operator.mul))))

    # Finds the biggest product of (n - 1) numbers.
    prefix_product, max_product = 1, float('-inf')
    for i in range(len(A)):
        suffix_product = suffix_products[i + 1] if i + 1 < len(A) else 1
        max_product = max(max_product, prefix_product * suffix_product)
        prefix_product *= A[i]
    return max_product
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
    return max_product


def main():
    for _ in range(10000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(2, 11)
        A = [random.randint(-9, 9) for _ in range(n)]
        print(*A)
        res = find_biggest_n_minus_one_product(A)
        assert res == check_ans(A)
        print(res)


if __name__ == '__main__':
    main()
