# @include
def get_height(cases, drops):
    def get_height_helper(cases, drops):
        if cases == 0 or drops == 0:
            return 0
        elif cases == 1:
            return drops
        if F[cases][drops] == -1:
            F[cases][drops] = (get_height_helper(cases, drops - 1) +
                               get_height_helper(cases - 1, drops - 1) + 1)
        return F[cases][drops]

    F = [[-1] * (drops + 1) for i in range(cases + 1)]
    return get_height_helper(cases, drops)
# @exclude


def check_answer(cases, drops):
    if cases <= 0 or drops <= 0:
        return 0
    row = [0] * cases
    for d in range(drops):
        for c in reversed(range(cases)):
            row[c] += (0 if c == 0 else row[c - 1]) + 1
    return row[-1]


def main():
    assert get_height(1, 10) == 10
    assert get_height(2, 1) == 1
    assert get_height(2, 2) == 3
    assert get_height(2, 3) == 6
    assert get_height(2, 4) == 10
    assert get_height(2, 5) == 15
    assert get_height(3, 2) == 3
    assert get_height(100, 2) == 3
    assert get_height(3, 5) == 25
    assert get_height(8, 11) == 1980
    assert get_height(3, 0) == 0
    assert get_height(3, 1) == 1
    assert get_height(3, 3) == 7
    assert get_height(0, 10) == 0
    assert get_height(0, 0) == 0

    assert check_answer(1, 10) == 10
    assert check_answer(2, 1) == 1
    assert check_answer(2, 2) == 3
    assert check_answer(2, 3) == 6
    assert check_answer(2, 4) == 10
    assert check_answer(2, 5) == 15
    assert check_answer(3, 2) == 3
    assert check_answer(100, 2) == 3
    assert check_answer(3, 5) == 25
    assert check_answer(8, 11) == 1980
    assert check_answer(3, 0) == 0
    assert check_answer(3, 1) == 1
    assert check_answer(3, 3) == 7
    assert check_answer(0, 10) == 0
    assert check_answer(0, 0) == 0


if __name__ == '__main__':
    main()
