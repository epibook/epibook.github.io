import bintrees
import sys
import itertools
import random


# @include
def find_closest_elements_in_sorted_arrays(sorted_arrays):
    min_distance_so_far = float('inf')
    # Stores array iterators in each entry.
    iters = bintrees.RBTree()
    for idx, sorted_array in enumerate(sorted_arrays):
        it = iter(sorted_array)
        first_min = next(it, None)
        if first_min is not None:
            iters.insert((first_min, idx), it)

    while True:
        min_value, min_idx = iters.min_key()
        max_value = iters.max_key()[0]
        min_distance_so_far = min(max_value - min_value, min_distance_so_far)
        it = iters.pop_min()[1]
        next_min = next(it, None)
        # Return if some array has no remaining elements.
        if next_min is None:
            return min_distance_so_far
        iters.insert((next_min, min_idx), it)
# @exclude


def brute_force_gen_answer(sorted_arrays):
    ans = min(
        max(values) - min(values)
        for values in itertools.product(*sorted_arrays))
    print(ans)
    return ans


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 5)
        sorted_arrays = []
        for _ in range(n):
            size = random.randint(1, 40)
            sorted_arrays.append(
                sorted(random.randrange(10000) for _ in range(size)))
        ans = find_closest_elements_in_sorted_arrays(sorted_arrays)
        print(ans)
        assert brute_force_gen_answer(sorted_arrays) == ans


if __name__ == '__main__':
    main()
