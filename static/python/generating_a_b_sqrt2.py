import bintrees
import sys
import math
import random
import collections


# These numbers have very interesting property, and people called it ugly numbers. It is also called quadratic integer rings.
# @include
class ABSqrt2:

    def __init__(self, a, b):
        self.a, self.b = a, b
        self.val = a + b * math.sqrt(2)

    def __lt__(self, other):
        return self.val < other.val

    def __eq__(self, other):
        return self.val == other.val
# @exclude

    def __hash__(self):
        return self.a ^ self.b

    def __repr__(self):
        return r'%d + %d \/2' % (self.a, self.b)


# @include


def generate_first_k_a_b_sqrt2(k):
    # Initial for 0 + 0 * sqrt(2).
    candidates = bintrees.RBTree([(ABSqrt2(0, 0), None)])

    result = []
    while len(result) < k:
        next_smallest = candidates.pop_min()[0]
        result.append(next_smallest)
        # Adds the next two numbers derived from next_smallest.
        candidates[ABSqrt2(next_smallest.a + 1, next_smallest.b)] = None
        candidates[ABSqrt2(next_smallest.a, next_smallest.b + 1)] = None
    return result
# @exclude


def golden(k):
    smallest = [ABSqrt2(0, 0)]
    q1 = collections.deque()
    q2 = collections.deque()
    q1.append(ABSqrt2(1, 0))
    q2.append(ABSqrt2(0, 1))
    for i in range(1, k):
        q1_f = q1[0]
        q2_f = q2[0]
        if q1_f.val < q2_f.val:
            smallest.append(q1_f)
            q1.popleft()
            q1.append(ABSqrt2(q1_f.a + 1, q1_f.b))
            q2.append(ABSqrt2(q1_f.a, q1_f.b + 1))
        else:
            smallest.append(q2_f)
            q2.popleft()
            q2.append(ABSqrt2(q2_f.a, q2_f.b + 1))
    return smallest


def simple_test():
    ans = generate_first_k_a_b_sqrt2(8)
    assert 0.0 == ans[0].val
    assert 1.0 == ans[1].val
    assert math.sqrt(2.0) == ans[2].val
    assert 2.0 == ans[3].val
    assert 1.0 + math.sqrt(2.0) == ans[4].val
    assert 2.0 * math.sqrt(2.0) == ans[5].val
    assert 3.0 == ans[6].val
    assert 2.0 + math.sqrt(2.0) == ans[7].val


def main():
    simple_test()
    return
    for _ in range(1000):
        k = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        ans = generate_first_k_a_b_sqrt2(k)
        for i, a in enumerate(ans):
            print(a.a, a.b, a.val)
            if i > 0:
                assert a.val >= ans[i - 1].val
        gold_res = golden(k)
        assert all(ans[i].val == gold_res[i].val for i in range(k))


if __name__ == '__main__':
    main()
