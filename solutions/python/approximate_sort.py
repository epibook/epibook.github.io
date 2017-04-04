import sys
import random
import itertools
import heapq

result = []


# @include
def sort_approximately_sorted_array(sequence, k):
    min_heap = []
    # Adds the first k elements into min_heap. Stop if there are fewer than k
    # elements.
    for x in itertools.islice(sequence, k):
        heapq.heappush(min_heap, x)

    # For every new element, add it to min_heap and extract the smallest.
    for x in sequence:
        smallest = heapq.heappushpop(min_heap, x)
        print(smallest)
        # @exclude
        result.append(smallest)
        # @include

    # sequence is exhausted, iteratively extracts the remaining elements.
    while min_heap:
        smallest = heapq.heappop(min_heap)
        print(smallest)
        # @exclude
        result.append(smallest)


def simple_test():
    A = [2, 1, 5, 4, 3, 9, 8, 7, 6]
    # It should print 1, 2, 3, 4, 5, 6, 7, 8, 9.
    sort_approximately_sorted_array(iter(A), 3)
    assert result == [1, 2, 3, 4, 5, 6, 7, 8, 9]


def main():
    simple_test()
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
        k = random.randint(1, n)
    elif len(sys.argv) == 3:
        n = int(sys.argv[1])
        k = int(sys.argv[2])
    else:
        n = random.randint(1, 100000)
        k = random.randint(1, n)

    print('n =', n, 'k =', k)
    A = [random.randint(1, 999999) for _ in range(n)]
    sort_approximately_sorted_array(iter(A), n - 1)


if __name__ == '__main__':
    main()
