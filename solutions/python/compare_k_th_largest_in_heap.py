import sys

# @include
SMALLER, EQUAL, LARGER = range(3)


def compare_kth_largest_heap(max_heap, k, x):
    larger_x = equal_x = 0
    larger_x, equal_x = compare_kth_largest_heap_helper(max_heap, k, x, 0,
                                                        larger_x, equal_x)
    return LARGER if larger_x >= k else (EQUAL if larger_x + equal_x >= k else
                                         SMALLER)


def compare_kth_largest_heap_helper(max_heap, k, x, idx, larger_x, equal_x):
    # Check early termination. Note that we cannot early terminate for
    # equal_x >= k because larger_x (which is currently smaller than k)
    # may still become >= k.
    if larger_x >= k or idx >= len(max_heap) or max_heap[idx] < x:
        return larger_x, equal_x
    elif max_heap[idx] == x:
        equal_x += 1
        if equal_x >= k:
            return larger_x, equal_x
    else:  # max_heap[idx] > x.
        larger_x += 1
    larger_x, equal_x = compare_kth_largest_heap_helper(
        max_heap, k, x, 2 * idx + 1, larger_x, equal_x)
    larger_x, equal_x = compare_kth_largest_heap_helper(
        max_heap, k, x, 2 * idx + 2, larger_x, equal_x)
    return larger_x, equal_x
# @exclude


def small_test():
    max_heap = [10, 2, 9, 2, 2, 8, 8, 2, 2, 2, 2, 7, 7, 7, 7]
    assert LARGER == compare_kth_largest_heap(max_heap, 3, 2)
    assert LARGER == compare_kth_largest_heap(max_heap, 6, 2)


def main():
    small_test()
    #     5
    #   4   5
    #  4 4 4 3
    # 4
    max_heap = [5, 4, 5, 4, 4, 4, 3, 4]
    if len(sys.argv) == 3:
        k = int(sys.argv[1])
        x = int(sys.argv[2])
        res = compare_kth_largest_heap(max_heap, k, x)
        print('smaller' if res == SMALLER else ('equal'
                                                if res == EQUAL else 'larger'))
    else:
        assert SMALLER == compare_kth_largest_heap(max_heap, 1, 6)
        assert EQUAL == compare_kth_largest_heap(max_heap, 1, 5)
        assert EQUAL == compare_kth_largest_heap(max_heap, 6, 4)
        assert EQUAL == compare_kth_largest_heap(max_heap, 3, 4)
        assert SMALLER == compare_kth_largest_heap(max_heap, 8, 4)
        assert LARGER == compare_kth_largest_heap(max_heap, 2, 4)
        assert LARGER == compare_kth_largest_heap(max_heap, 2, 3)


if __name__ == '__main__':
    main()
