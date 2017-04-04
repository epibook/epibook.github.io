import heapq


# @include
def k_largest_in_binary_heap(A, k):
    if k <= 0:
        return []

    # Stores the (-value, index)-pair in candidate_max_heap. This heap is
    # ordered by value field. Uses the negative of value to get the effect of
    # a max heap.
    candidate_max_heap = []
    # The largest element in A is at index 0.
    candidate_max_heap.append((-A[0], 0))
    result = []
    for _ in range(k):
        candidate_idx = candidate_max_heap[0][1]
        result.append(-heapq.heappop(candidate_max_heap)[0])

        left_child_idx = 2 * candidate_idx + 1
        if left_child_idx < len(A):
            heapq.heappush(candidate_max_heap,
                           (-A[left_child_idx], left_child_idx))
        right_child_idx = 2 * candidate_idx + 2
        if right_child_idx < len(A):
            heapq.heappush(candidate_max_heap,
                           (-A[right_child_idx], right_child_idx))
    return result
# @exclude


def main():
    max_heap = [10, 2, 9, 2, 2, 8, 8, 2, 2, 2, 2, 7, 7, 7, 7]
    result = k_largest_in_binary_heap(max_heap, 3)
    expected_result = [10, 9, 8]
    assert result == expected_result
    result = k_largest_in_binary_heap(max_heap, 5)
    expected_result = [10, 9, 8, 8, 7]
    assert result == expected_result

    max_heap = [97, 84, 93, 83, 81, 90, 79, 83, 55, 42, 21, 73]
    result = k_largest_in_binary_heap(max_heap, 3)
    expected_result = [97, 93, 90]
    assert result == expected_result

    max_heap = [100, 1, 5, 0, 0]
    result = k_largest_in_binary_heap(max_heap, 1)
    expected_result = [100]
    assert result == expected_result


if __name__ == '__main__':
    main()
