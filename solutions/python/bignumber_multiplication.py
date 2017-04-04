import copy
import random
import sys


# @include
def multiply(num1, num2):
    sign = -1 if (num1[0] < 0) ^ (num2[0] < 0) else 1
    num1[0], num2[0] = abs(num1[0]), abs(num2[0])

    result = [0] * (len(num1) + len(num2))
    for i in reversed(range(len(num1))):
        for j in reversed(range(len(num2))):
            result[i + j + 1] += num1[i] * num2[j]
            result[i + j] += result[i + j + 1] // 10
            result[i + j + 1] %= 10

    # Remove the leading zeroes.
    result = result[next((i for i, x in enumerate(result) if x != 0),
                         len(result)):] or [0]
    return [sign * result[0]] + result[1:]
# @exclude


def rand_list(length):
    if length == 0:
        return [0]
    ret = [random.randint(1, 9)] + [random.randint(0, 9)
                                    for _ in range(length - 1)]
    if random.randint(0, 1) == 1:
        ret[0] *= -1
    return ret


def simple_test():
    assert multiply([0], [-1, 0, 0, 0]) == [0]
    assert multiply([0], [1, 0, 0, 0]) == [0]
    assert multiply([9], [9]) == [8, 1]
    assert multiply([9], [9, 9, 9, 9]) == [8, 9, 9, 9, 1]
    assert multiply(
        [1, 3, 1, 4, 1, 2],
        [-1, 3, 1, 3, 3, 3, 2]) == [-1, 7, 2, 5, 8, 7, 5, 8, 4, 7, 8, 4]
    assert multiply([7, 3], [-3]) == [-2, 1, 9]


def main():
    simple_test()
    for _ in range(1000):
        if len(sys.argv) == 3:
            s1, s2 = sys.argv[1], sys.argv[2]
        else:
            s1, s2 = rand_list(random.randint(0, 19)), rand_list(
                random.randint(0, 19))
        temp1, temp2 = copy.deepcopy(s1), copy.deepcopy(s2)
        res = multiply(temp1, temp2)
        print(s1, '*', s2, '=', res)
        result = int(''.join(map(str, s1))) * int(''.join(map(str, s2)))
        print('answer =', result)
        assert result == int(''.join(map(str, res)))


if __name__ == '__main__':
    main()
