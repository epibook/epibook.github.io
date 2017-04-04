import sys
import random
import heapq


# @include
def merge_sorted_arrays(sorted_arrays):
    min_heap = []
    # Builds a list of iterators for each array in sorted_arrays.
    sorted_arrays_iters = [iter(x) for x in sorted_arrays]

    # Puts first element from each iterator in min_heap.
    for i, it in enumerate(sorted_arrays_iters):
        first_element = next(it, None)
        if first_element is not None:
            heapq.heappush(min_heap, (first_element, i))

    result = []
    while min_heap:
        smallest_entry, smallest_array_i = heapq.heappop(min_heap)
        smallest_array_iter = sorted_arrays_iters[smallest_array_i]
        result.append(smallest_entry)
        next_element = next(smallest_array_iter, None)
        if next_element is not None:
            heapq.heappush(min_heap, (next_element, smallest_array_i))
    return result


# Pythonic solution, uses the heapq.merge() method which takes multiple inputs.
def merge_sorted_arrays_pythonic(sorted_arrays):
    return list(heapq.merge(*sorted_arrays))
# @exclude


def simple_test():
    S = [[1, 5, 10], [2, 3, 100], [2, 12, 2**64 - 1]]
    assert merge_sorted_arrays(S) == merge_sorted_arrays_pythonic(
        S) == [1, 2, 2, 3, 5, 10, 12, 100, 2**64 - 1]

    S = [[1]]
    assert merge_sorted_arrays(S) == merge_sorted_arrays_pythonic(S) == [1]

    S = [[], [1], [2]]
    assert merge_sorted_arrays(S) == merge_sorted_arrays_pythonic(S) == [1, 2]


def main():
    simple_test()
    for _ in range(100):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 100)

        S = []
        for i in range(n):
            S.append(
                sorted(
                    random.randint(-9999, 9999)
                    for j in range(random.randint(1, 500))))
        ans = merge_sorted_arrays(S)
        for i in range(1, len(ans)):
            assert ans[i - 1] <= ans[i]
        assert ans == merge_sorted_arrays_pythonic(S)


if __name__ == '__main__':
    main()
