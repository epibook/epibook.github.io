# @include
# V contains the number of votes for each state.
# This function returns the total number of ways to tie.


def ties_election(V):
    total_votes = sum(V)

    # No way to tie if the total number of votes is odd.
    if total_votes % 2:
        return 0

    table = [0] * (total_votes + 1)
    table[0] = 1  # Base condition: One way to reach 0.
    for v in V:
        for j in reversed(range(v, total_votes + 1)):
            table[j] += table[j - v]
    return table[total_votes // 2]
# @exclude


def simple_test():
    votes = (4, 5, 2, 7)
    assert 2 == ties_election(votes)


def main():
    simple_test()
    votes = (9, 3, 11, 6, 55, 9, 7, 3, 29, 16, 4, 4, 20, 11, 6, 6, 8, 8, 4, 10,
             11, 16, 10, 6, 10, 3, 5, 6, 4, 14, 5, 29, 15, 3, 18, 7, 7, 20, 4,
             9, 3, 11, 38, 6, 3, 13, 12, 5, 10, 3, 3)
    assert 16976480564070 == ties_election(votes)
    print(ties_election(votes))


if __name__ == '__main__':
    main()
