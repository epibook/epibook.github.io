import sys
import random
import collections

# @include
MPG = 20


# gallons[i] is the amount of gas in city i, and distances[i] is the
# distance city i to the next city.
def find_ample_city(gallons, distances):
    remaining_gallons = 0
    CityAndRemainingGas = collections.namedtuple('CityAndRemainingGas',
                                                 ('city', 'remaining_gallons'))
    city_remaining_gallons_pair = CityAndRemainingGas(0, 0)
    num_cities = len(gallons)
    for i in range(1, num_cities):
        remaining_gallons += gallons[i - 1] - distances[i - 1] // MPG
        if remaining_gallons < city_remaining_gallons_pair.remaining_gallons:
            city_remaining_gallons_pair = CityAndRemainingGas(i,
                                                              remaining_gallons)
    return city_remaining_gallons_pair.city
# @exclude


def check_ans(gallons, distances, c):
    s = c
    gas = 0
    while True:
        gas += gallons[s] - distances[s] // MPG
        assert gas >= 0
        s = (s + 1) % len(gallons)
        if s == c:
            break


def small_test():
    # Example in the book.
    gallons = (20, 15, 15, 15, 35, 25, 30, 15, 65, 45, 10, 45, 25)
    distances = (15 * MPG, 20 * MPG, 50 * MPG, 15 * MPG, 15 * MPG, 30 * MPG,
                 20 * MPG, 55 * MPG, 20 * MPG, 50 * MPG, 10 * MPG, 15 * MPG,
                 15 * MPG)
    ans = find_ample_city(gallons, distances)
    assert ans == 8
    check_ans(gallons, distances, ans)


def main():
    small_test()
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        gallons = []
        distances = []
        sum_ = 0
        for i in range(n):
            x = random.randint(1, 200)
            sum_ += x
            gallons.append(x)
        sum_ -= n
        for i in range(n):
            x = 0
            if sum_:
                x = random.randint(1, sum_)
            distances.append(x + 1)
            sum_ -= x
        distances[-1] += sum_
        c = find_ample_city(gallons, distances)
        print('start city =', c)
        check_ans(gallons, distances, c)


if __name__ == '__main__':
    main()
