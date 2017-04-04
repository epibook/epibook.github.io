import collections
import itertools
import sys
import random
import heapq


# @include
def top_k(k, stream):
    # Entries are compared by their lengths.
    min_heap = [(len(s), s) for s in itertools.islice(stream, k)]
    heapq.heapify(min_heap)
    for next_string in stream:
        # Push next_string and pop the shortest string in min_heap.
        heapq.heappushpop(min_heap, (len(next_string), next_string))
    return [p[1] for p in heapq.nsmallest(k, min_heap)]
# @exclude


def main():
    for _ in range(1000):
        num = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10)
        A = [str(random.randint(1, 101)) for _ in range(num)]
        k = random.randint(1, num)
        stream = iter(A)
        result = top_k(k, stream)
        A = sorted(A, key=lambda s: len(s))
        assert collections.Counter(
            [len(a) for a in result]) == collections.Counter(
                [len(a) for a in A[-k:]])


if __name__ == '__main__':
    main()
