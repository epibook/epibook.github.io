import sys
import random


# @include
def max_subarray_sum_in_circular(A):
    def find_optimum_subarray_using_comp(comp):
        till = overall = 0
        for a in A:
            till = comp(a, a + till)
            overall = comp(overall, till)
        return overall

    # Finds the max in non-circular case and circular case.
    return max(
        find_optimum_subarray_using_comp(max),  # Non-circular case.
        sum(A) - find_optimum_subarray_using_comp(min))  # Circular case.
# @exclude


# O(n^2) solution.
def check_ans(A):
    ans = 0
    for i in range(len(A)):
        sum_ = 0
        for j in range(len(A)):
            sum_ += A[(i + j) % len(A)]
            ans = max(ans, sum_)
    print('correct answer =', ans)
    return ans


def main():
    for times in range(1000):
        if len(sys.argv) > 2:
            A = [int(i) for i in sys.argv[1:]]
        else:
            n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(
                1, 10000)
            A = [random.randint(-10000, 10000) for _ in range(n)]
        ans = max_subarray_sum_in_circular(A)
        print('\nmaximum sum =', ans)
        assert ans == check_ans(A)


if __name__ == '__main__':
    main()
