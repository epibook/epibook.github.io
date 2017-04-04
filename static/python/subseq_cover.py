import collections
import sys
import random
import string


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


# @include
Subarray = collections.namedtuple('Subarray', ('start', 'end'))


def find_smallest_sequentially_covering_subset(paragraph, keywords):
    # Maps each keyword to its index in the keywords array.
    keyword_to_idx = {k: i for i, k in enumerate(keywords)}

    # Since keywords are uniquely identified by their indices in keywords
    # array, we can use those indices as keys to lookup in an array.
    latest_occurrence = [-1] * len(keywords)
    # For each keyword (identified by its index in keywords array), the length
    # of the shortest subarray ending at the most recent occurrence of that
    # keyword that sequentially cover all keywords up to that keyword.
    shortest_subarray_length = [float('inf')] * len(keywords)

    shortest_distance = float('inf')
    result = Subarray(-1, -1)
    for i, p in enumerate(paragraph):
        if p in keyword_to_idx:
            keyword_idx = keyword_to_idx[p]
            if keyword_idx == 0:  # First keyword.
                shortest_subarray_length[keyword_idx] = 1
            elif shortest_subarray_length[keyword_idx - 1] != float('inf'):
                distance_to_previous_keyword = (
                    i - latest_occurrence[keyword_idx - 1])
                shortest_subarray_length[keyword_idx] = (
                    distance_to_previous_keyword +
                    shortest_subarray_length[keyword_idx - 1])
            latest_occurrence[keyword_idx] = i

            # Last keyword, for improved subarray.
            if (keyword_idx == len(keywords) - 1 and
                    shortest_subarray_length[-1] < shortest_distance):
                shortest_distance = shortest_subarray_length[-1]
                result = Subarray(i - shortest_distance + 1, i)
    return result
# @exclude


def small_test():
    A3 = list('01234567892461010103210')
    subseq4 = list('02946')
    rr = find_smallest_sequentially_covering_subset(A3, subseq4)
    assert (rr.start, rr.end) == (0, 12)


def main():
    small_test()
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        A = [rand_string(random.randint(1, 5)) for _ in range(n)]
        d = set(A)
        print('A = ', end='')
        print(*A, sep=',')
        m = random.randint(1, min(len(d), 10))
        Q = random.sample(d, m)
        print('Q = ', end='')
        print(*Q, sep=',')
        res = find_smallest_sequentially_covering_subset(A, Q)
        print(res.start, res.end, sep=', ')
        if res.start != -1 and res.end != len(Q):
            if res.start != res.end:
                print(res.start, res.end, sep=', ')
            d = set(Q)
            assert not d.difference(A[res.start:res.end + 1])


if __name__ == '__main__':
    main()
