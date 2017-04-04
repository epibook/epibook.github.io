import sys
import random
import collections


def rand_vector(length):
    return [random.randrange(100) for _ in range(length)]


# @include
Item = collections.namedtuple('Item', ('weight', 'value'))


def optimum_subjec_to_capacity(items, capacity):
    # Returns the optimum value when we choose from items[:k + 1] and have a
    # capacity of available_capacity.
    def optimum_subject_to_item_and_capacity(k, available_capacity):
        if k < 0:
            # No items can be chosen.
            return 0

        if V[k][available_capacity] == -1:
            without_curr_item = optimum_subject_to_item_and_capacity(
                k - 1, available_capacity)
            with_curr_item = (0 if available_capacity < items[k].weight else (
                items[k].value + optimum_subject_to_item_and_capacity(
                    k - 1, available_capacity - items[k].weight)))
            V[k][available_capacity] = max(without_curr_item, with_curr_item)
        return V[k][available_capacity]

    # V[i][j] holds the optimum value when we choose from items[:i + 1] and have
    # a capacity of j.
    V = [[-1] * (capacity + 1) for _ in items]
    return optimum_subject_to_item_and_capacity(len(items) - 1, capacity)
# @exclude


def small_test():
    # The example in the book.
    items = [
        Item(w, v)
        for w, v in ((20, 65), (8, 35), (60, 245), (55, 195), (40, 65), (
            70, 150), (85, 275), (25, 155), (30, 120), (65, 320), (75, 75), (
                10, 40), (95, 200), (50, 100), (40, 220), (10, 99))
    ]
    assert 695 == optimum_subjec_to_capacity(items, 130)

    items = (Item(5, 60), Item(3, 50), Item(4, 70), Item(2, 30))
    assert 80 == optimum_subjec_to_capacity(items, 5)


def main():
    small_test()
    if len(sys.argv) == 1:
        n = random.randint(1, 100)
        W = random.randint(1, 1000)
        weight = rand_vector(n)
        value = rand_vector(n)
    elif len(sys.argv) == 2:
        n = int(sys.argv[1])
        W = random.randint(1, 1000)
        weight = rand_vector(n)
        value = rand_vector(n)
    else:
        n = int(sys.argv[1])
        W = int(sys.argv[2])
        weight = [int(sys.argv[3 + i]) for i in range(n)]
        value = [int(sys.argv[3 + i + n]) for i in range(n)]
    print('weight:', *weight)
    print('value:', *value)
    items = [Item(w, v) for w, v in zip(weight, value)]
    print('knapsack size =', W)
    print('all value =', optimum_subjec_to_capacity(items, W))


if __name__ == '__main__':
    main()
