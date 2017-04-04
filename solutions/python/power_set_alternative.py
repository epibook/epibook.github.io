import sys
import random


# @include
def generate_power_set(input_set):
    # Generate all subsets whose intersection with input_set[0], ...,
    # input_set[to_be_selected - 1] is exactly selected_so_far.
    def directed_power_set(to_be_selected, selected_so_far):
        if to_be_selected == len(input_set):
            power_set.append(list(selected_so_far))
            return

        directed_power_set(to_be_selected + 1, selected_so_far)
        # Generate all subsets that contain input_set[to_be_selected].
        directed_power_set(to_be_selected + 1,
                           selected_so_far + [input_set[to_be_selected]])

    power_set = []
    directed_power_set(0, [])
    return power_set
# @exclude


# Pythonic solution
def generate_power_set_pythonic(S):
    power_set = [[]]
    for a in S:
        power_set += [s + [a] for s in power_set]
    return power_set


def small_test():
    assert sorted(generate_power_set([0, 1, 2])) == [
        [], [0], [0, 1], [0, 1, 2], [0, 2], [1], [1, 2], [2]
    ] == sorted(generate_power_set_pythonic([0, 1, 2]))


def main():
    small_test()
    S = [
        int(i) for i in sys.argv[
            1:]] if len(
        sys.argv) >= 2 else list(
                range(
                    random.randint(
                        1,
                        10)))
    assert sorted(generate_power_set(S)) == sorted(
        generate_power_set_pythonic(S))


if __name__ == '__main__':
    main()
