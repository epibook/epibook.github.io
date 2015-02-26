# Merge_sorted_arrays.h b4b3a70d8ab942579f85b4416f980d05831af969
# Merge_sorted_arrays.cpp b4b3a70d8ab942579f85b4416f980d05831af969
import sys
import random
import heapq


# @include
def merge_sorted_arrays(sorted_arrays):
    min_heap = []
    # Builds a list of iterators for each array in sorted_arrays.
    iters = [iter(x) for x in sorted_arrays]

    # Puts first element from each iterator in min_heap.
    for i, it in enumerate(iters):
        first_element = next(it, None)
        if first_element is not None:
            heapq.heappush(min_heap, (first_element, i))

    result = []
    while min_heap:
        smallest_entry, smallest_array_i = heapq.heappop(min_heap)
        smallest_array_iter = iters[smallest_array_i]
        result.append(smallest_entry)
        next_element = next(smallest_array_iter, None)
        if next_element is not None:
            # Add the next entry of smallest_array into min_heap.
            heapq.heappush(min_heap, (next_element, smallest_array_i))

    return result
# @exclude


# Pythonic solution
def merge_sorted_arrays_pythonic(sorted_arrays):
    return list(heapq.merge(*sorted_arrays))


def main():
    for _ in range(100):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 10000)

        S = []
        print('n =', n)
        for i in range(n):
            S.append(sorted(random.randint(-9999, 9999)
                            for j in range(random.randint(1, 500))))

##        for i in S:
##            print(*i, sep=' ')

        ans = merge_sorted_arrays(S)
        for i in range(1, len(ans)):
            assert ans[i - 1] <= ans[i]

        assert ans == merge_sorted_arrays_pythonic(S)

if __name__ == '__main__':
    main()
