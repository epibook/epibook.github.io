import sys
import random


# @include
def gray_code(num_bits):
    def directed_gray_code(history):
        def differs_by_one_bit(x, y):
            bit_difference = x ^ y
            return bit_difference and not (bit_difference &
                                           (bit_difference - 1))

        if len(result) == 1 << num_bits:
            # Check if the first and last codes differ by one bit.
            return differs_by_one_bit(result[0], result[-1])

        for i in range(num_bits):
            previous_code = result[-1]
            candidate_next_code = previous_code ^ (1 << i)
            if candidate_next_code not in history:
                history.add(candidate_next_code)
                result.append(candidate_next_code)
                if directed_gray_code(history):
                    return True
                history.remove(candidate_next_code)
                del result[-1]
        return False

    result = [0]
    directed_gray_code(set([0]))
    return result
# @exclude


def small_test():
    assert gray_code(3) == [0, 1, 3, 2, 6, 7, 5, 4]


def check_ans(A):
    for i in range(len(A)):
        num_differ_bits = 0
        prev = A[i]
        now = A[(i + 1) % len(A)]
        prev_s = '{:010b}'.format(prev)
        now_s = '{:010b}'.format(now)
        for i in range(10):
            if prev_s[i] != now_s[i]:
                num_differ_bits += 1
        assert num_differ_bits == 1


def main():
    small_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 9)
    print('n =', n)
    vec = gray_code(n)
    print(*vec)
    check_ans(vec)


if __name__ == '__main__':
    main()
