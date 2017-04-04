import csv
import heapq
import io
import math
import random
import sys


# @include
class Star:

    def __init__(self, x, y, z):
        self.x, self.y, self.z = x, y, z

    @property
    def distance(self):
        return self.x**2 + self.y**2 + self.z**2

    def __lt__(self, rhs):
        return self.distance < rhs.distance
    # @exclude

    def __str__(self):
        return ' '.join(map(str, (self.x, self.y, self.z)))

    def __eq__(self, rhs):
        return math.isclose(self.distance, rhs.distance)

    # @include


def find_closest_k_stars(k, stars):
    # max_heap to store the closest k stars seen so far.
    max_heap = []
    reader = csv.reader(stars)
    for line in reader:
        star = Star(*map(float, line))
        # Add each star to the max-heap. If the max-heap size exceeds k, remove
        # the maximum element from the max-heap.
        # As python has only min-heap, insert tuple (negative of distance, star)
        # to sort in reversed distance order.
        heapq.heappush(max_heap, (-star.distance, star))
        if len(max_heap) == k + 1:
            heapq.heappop(max_heap)

    # Iteratively extract from the max-heap, which yields the stars sorted
    # according from furthest to closest.
    return [s[1] for s in heapq.nlargest(k, max_heap)]
# @exclude


def simple_test():
    stars = [
        Star(1, 2, 3), Star(5, 5, 5), Star(0, 2, 1), Star(9, 2, 1),
        Star(1, 2, 1), Star(2, 2, 1)
    ]
    s = io.StringIO(newline='')
    ss = csv.writer(s)
    for star in stars:
        ss.writerow((star.x, star.y, star.z))
    s.seek(0)
    closest_stars = find_closest_k_stars(3, s)
    assert len(closest_stars) == 3
    assert closest_stars[0].distance == Star(0, 2, 1).distance
    assert closest_stars[0].distance == Star(2, 0, 1).distance
    assert closest_stars[1].distance == Star(1, 2, 1).distance
    assert closest_stars[1].distance == Star(1, 1, 2).distance

    stars = [
        Star(1, 2, 3), Star(5, 5, 5), Star(4, 4, 4), Star(3, 2, 1),
        Star(5, 5, 5), Star(3, 2, 3), Star(3, 2, 3), Star(3, 2, 1)
    ]
    s = io.StringIO(newline='')
    ss = csv.writer(s)
    for star in stars:
        ss.writerow((star.x, star.y, star.z))
    s.seek(0)
    closest_stars = find_closest_k_stars(2, s)
    assert len(closest_stars) == 2
    assert closest_stars[0].distance == Star(1, 2, 3).distance
    assert closest_stars[1].distance == Star(3, 2, 1).distance


def main():
    simple_test()
    for _ in range(1000):
        if len(sys.argv) == 2:
            num = int(sys.argv[1])
            k = random.randint(1, num)
        elif len(sys.argv) == 3:
            num = int(sys.argv[1])
            k = int(sys.argv[2])
        else:
            num = random.randint(1, 10000)
            k = random.randint(1, min(num, 10))
        # Randomly generate num of stars.
        stars = [
            Star(
                random.uniform(0, 10000),
                random.uniform(0, 10000), random.uniform(0, 10000))
            for i in range(num)
        ]
        s = io.StringIO(newline='')
        ss = csv.writer(s)
        for star in stars:
            ss.writerow((star.x, star.y, star.z))
        s.seek(0)
        closest_stars = sorted(find_closest_k_stars(k, s))
        stars.sort()
        print('k =', k)
        print(stars[k - 1])
        print(closest_stars[-1])
        assert stars[k - 1] == closest_stars[-1]


if __name__ == '__main__':
    main()
