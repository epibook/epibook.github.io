# Computing_x^n.cpp b4b3a70d8ab942579f85b4416f980d05831af969
import sys
import random
import collections


# @include
def get_shortest_straight_line_program(n):
    if n == 1:
        return [1]

    SLPs = collections.deque()  # SLP is acronym for straight line program.
    # Constructs the initial SLP with one node whose value is 1.
    SLPs.append([1])
    while SLPs:
        candidate_SLP = SLPs.popleft()
        # Tries all possible combinations in candidate_SLP.
        for a in candidate_SLP:
            power = a + candidate_SLP[-1]
            if power > n:
                break  # No possible solution for candidate_SLP.
            new_SLP = candidate_SLP.copy()
            new_SLP.append(power)
            if power == n:
                return new_SLP
            SLPs.append(new_SLP)

# @exclude
    raise ValueError('unknown error')  # This line should never be called.


def small_test():
    res = get_shortest_straight_line_program(88)
    golden_res = [1, 2, 3, 4, 7, 11, 22, 44, 88]
    assert res == golden_res
    res = get_shortest_straight_line_program(67)
    golden_res = [1, 2, 3, 4, 8, 16, 32, 35, 67]
    assert res == golden_res


def main():
    small_test()
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
    else:
        n = random.randint(1, 100)
    print('n =', n)
    min_exp = get_shortest_straight_line_program(n)
    print(*min_exp)
    print('size =', len(min_exp))


if __name__ == '__main__':
    main()
