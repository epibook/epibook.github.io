import sys
import random
import heapq

global_result = []


# @include
def online_median(sequence):
    # min_heap stores the larger half seen so far.
    min_heap = []
    # max_heap stores the smaller half seen so far.
    # values in max_heap are negative
    max_heap = []

    for x in sequence:
        heapq.heappush(max_heap, -heapq.heappushpop(min_heap, x))
        # Ensure min_heap and max_heap have equal number of elements if an even
        # number of elements is read; otherwise, min_heap must have one more
        # element than max_heap.
        if len(max_heap) > len(min_heap):
            heapq.heappush(min_heap, -heapq.heappop(max_heap))

        # @exclude
        global_result.append(0.5 * (min_heap[0] + (-max_heap[0]))
                             if len(min_heap) == len(max_heap) else min_heap[0])
        # @include
        print(0.5 * (min_heap[0] + (-max_heap[0]))
              if len(min_heap) == len(max_heap) else min_heap[0])
# @exclude


def small_test():
    stream = [5, 4, 3, 2, 1]
    online_median(iter(stream))
    assert global_result == [5, 4.5, 4, 3.5, 3]

    global_result.clear()
    stream = [1, 2, 3, 4, 5]
    online_median(iter(stream))
    assert global_result == [1, 1.5, 2, 2.5, 3]

    global_result.clear()
    stream = [1, 0, 3, 5, 2, 0, 1]
    online_median(iter(stream))
    assert global_result == [1, 0.5, 1, 2, 2, 1.5, 1]

    global_result.clear()
    stream = [-1]
    online_median(iter(stream))
    assert global_result == [-1]


def main():
    small_test()
    num = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 100000)
    stream = [random.randint(1, 10000) for _ in range(num)]
    print(*stream, sep='\n')
    print()
    online_median(iter(stream))


if __name__ == '__main__':
    main()
