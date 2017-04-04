import sys
import random
import math
from generating_a_b_sqrt2 import generate_first_k_a_b_sqrt2 as golden
from generating_a_b_sqrt2 import ABSqrt2


# @include
def generate_first_k_a_b_sqrt2(k):
    # Will store the first k numbers of the form a + b sqrt(2).
    result = [ABSqrt2(0, 0)]
    i = j = 0
    for _ in range(1, k):
        result_i_plus_1 = ABSqrt2(result[i].a + 1, result[i].b)
        result_j_plus_sqrt2 = ABSqrt2(result[j].a, result[j].b + 1)
        result.append(min(result_i_plus_1, result_j_plus_sqrt2))
        if result_i_plus_1.val == result[-1].val:
            i += 1
        if result_j_plus_sqrt2.val == result[-1].val:
            j += 1
    return result
# @exclude


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
    for _ in range(1000):
        k = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        ans = generate_first_k_a_b_sqrt2(k)
        assert len(ans) == k
        for i, a in enumerate(ans):
            print(a.a, a.b, a.val)
            if i > 0:
                assert a.val >= ans[i - 1].val
        gold_res = golden(k)
        assert all(ans[i].val == gold_res[i].val for i in range(k))


if __name__ == '__main__':
    main()
