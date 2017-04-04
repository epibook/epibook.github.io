import sys
import random


# @include
def max_subarray_sum_in_circular(A):
    # Calculates the non-circular solution.
    def find_max_subarray():
        maximum_till = maximum = 0
        for a in A:
            maximum_till = max(a, a + maximum_till)
            maximum = max(maximum, maximum_till)
        return maximum

    # Calculates the solution which is circular.
    def find_circular_max_subarray():
        def compute_running_maximum(A):
            partial_sum = A[0]
            running_maximum = [partial_sum]
            for a in A[1:]:
                partial_sum += a
                running_maximum.append(max(running_maximum[-1], partial_sum))
            return running_maximum

        # Maximum subarray sum starts at index 0 and ends at or before index i.
        maximum_begin = compute_running_maximum(A)
        # Maximum subarray sum starts at index i + 1 and ends at the last
        # element.
        maximum_end = compute_running_maximum(A[::-1])[::-1]

        # Calculates the maximum subarray which is circular.
        return max(begin + end
                   for begin, end in zip(maximum_begin, maximum_end))

    return max(find_max_subarray(), find_circular_max_subarray())
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
