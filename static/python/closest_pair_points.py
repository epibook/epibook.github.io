import sys
import math
import operator
import collections
import random

# @include
Point = collections.namedtuple('Point', ('x', 'y'))

PairOfPoints = collections.namedtuple('PairOfPoints', ('p1', 'p2'))

PairOfPointsWithDistance = collections.namedtuple('PairOfPointsWithDistance',
                                                  ('p1', 'p2', 'distance'))

k_brute_force_threshold = 50


def find_closest_pair_points(points):
    points.sort(key=operator.itemgetter(0))
    closest_two_points_with_distance = find_closest_pair_points_in_subarray(
        points, 0, len(points))
    return PairOfPoints(closest_two_points_with_distance.p1,
                        closest_two_points_with_distance.p2)


# Returns the closest two points and their distance as a tuple in points[begin:end].
def find_closest_pair_points_in_subarray(points, begin, end):
    if end - begin <= k_brute_force_threshold:  # Switch to brute-force.
        return solve_by_enumerate_all_pairs(points, begin, end)

    mid = begin + (end - begin) // 2
    result0 = find_closest_pair_points_in_subarray(points, begin, mid)
    result1 = find_closest_pair_points_in_subarray(points, mid, end)
    best_result_in_subsets = result0 if result0.distance < result1.distance else result1
    # Stores the points whose separation along the X-axis is less than min_d.
    remain = []
    for p in points:
        if abs(p.x - points[mid].x) < best_result_in_subsets.distance:
            remain.append(p)

    mid_ret = find_closest_pair_in_remain(remain,
                                          best_result_in_subsets.distance)
    return mid_ret if mid_ret.distance < best_result_in_subsets.distance else best_result_in_subsets


# Returns the closest two points and the distance between them.
def solve_by_enumerate_all_pairs(points, begin, end):
    ret = PairOfPointsWithDistance(None, None, float('inf'))
    for i in range(begin, end):
        for j in range(i + 1, end):
            dis = distance(points[i], points[j])
            if dis < ret.distance:
                ret = PairOfPointsWithDistance(points[i], points[j], dis)
    return ret


# Returns the closest two points and its distance as a tuple.
def find_closest_pair_in_remain(remain, d):
    remain.sort(key=operator.itemgetter(1))
    # At most six points in remain.
    ret = PairOfPointsWithDistance(None, None, float('inf'))
    for i in range(len(remain)):
        j = i + 1
        while j < len(remain) and remain[j].y - remain[i].y < d:
            dis = distance(remain[i], remain[j])
            if dis < ret.distance:
                ret = PairOfPointsWithDistance(remain[i], remain[j], dis)
            j += 1
    return ret


def distance(a, b):
    return math.hypot(a.x - b.x, a.y - b.y)
# @exclude


def main():
    for _ in range(50):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 5000)
        print('num points =', n)
        points = [
            Point(random.randrange(10000), random.randrange(1000))
            for _ in range(n)
        ]
        p = find_closest_pair_points(points)
        q = solve_by_enumerate_all_pairs(points, 0, len(points))
        print('p = ', p.p1, ' ', p.p2, ', dis = ', distance(p.p1, p.p2), sep='')
        print('q = ', q.p1, ' ', q.p2, ', dis = ', distance(q.p1, q.p2), sep='')
        assert distance(p.p1, p.p2) == q.distance


if __name__ == '__main__':
    main()
