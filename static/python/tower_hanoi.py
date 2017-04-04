import sys
import random

num_steps = 0


# @include
def compute_tower_hanoi(num_rings):
    def compute_tower_hanoi_steps(num_rings_to_move, from_peg, to_peg, use_peg):
        # @exclude
        global num_steps
        # @include
        if num_rings_to_move > 0:
            compute_tower_hanoi_steps(
                num_rings_to_move - 1, from_peg, use_peg, to_peg)
            pegs[to_peg].append(pegs[from_peg].pop())
            print('Move from peg', from_peg, 'to peg', to_peg)
            # @exclude
            num_steps += 1
            # @include
            compute_tower_hanoi_steps(
                num_rings_to_move - 1, use_peg, to_peg, from_peg)

    # Initialize pegs.
    NUM_PEGS = 3
    pegs = [list(reversed(range(1, num_rings + 1)))] + [[]
                                                        for _ in range(1, NUM_PEGS)]
    compute_tower_hanoi_steps(num_rings, 0, 1, 2)
# @exclude


def main():
    global num_steps
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10)
    print('n =', n)
    compute_tower_hanoi(n)

    num_steps = 0
    compute_tower_hanoi(4)
    assert 15 == num_steps

    num_steps = 0
    compute_tower_hanoi(1)
    assert 1 == num_steps

    num_steps = 0
    compute_tower_hanoi(0)
    assert 0 == num_steps

    num_steps = 0
    compute_tower_hanoi(10)
    assert 1023 == num_steps


if __name__ == '__main__':
    main()
