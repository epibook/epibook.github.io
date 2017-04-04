// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <iostream>
#include <limits>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::get;
using std::numeric_limits;
using std::ostream;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

struct Point;
struct PairOfPoints;
struct PairOfPointsWithDistance;
PairOfPointsWithDistance FindClosestPairPointsInSubarray(
    const vector<Point>& points, int s, int e);
PairOfPointsWithDistance SolveByEnumerateAllPairs(const vector<Point>& points,
                                                  int s, int e);
PairOfPointsWithDistance FindClosestPairInRemain(vector<Point>* points,
                                                 double d);
double Distance(const Point& a, const Point& b);

// @include
struct Point {
  int x, y;
  // @exclude
  friend ostream& operator<<(ostream& os, const Point& p) {
    os << "(" << p.x << ", " << p.y << ")";
    return os;
  }
  // @include
};
const int kBruteForceThreshold = 50;

struct PairOfPoints {
  Point p1, p2;
};

struct PairOfPointsWithDistance {
  Point p1, p2;
  double distance;
};

PairOfPoints FindClosestPairPoints(vector<Point> points) {
  sort(begin(points), end(points),
       [](const Point& a, const Point& b) { return a.x < b.x; });
  auto closest_two_points_with_distance =
      FindClosestPairPointsInSubarray(points, 0, points.size());
  return {closest_two_points_with_distance.p1,
          closest_two_points_with_distance.p2};
}

// Returns the closest two points and their distance as a tuple in
// points[begin : end - 1].
PairOfPointsWithDistance FindClosestPairPointsInSubarray(
    const vector<Point>& points, int begin, int end) {
  if (end - begin <= kBruteForceThreshold) {  // Switch to brute-force.
    return SolveByEnumerateAllPairs(points, begin, end);
  }

  int mid = begin + (end - begin) / 2;
  auto result0 = FindClosestPairPointsInSubarray(points, begin, mid);
  auto result1 = FindClosestPairPointsInSubarray(points, mid, end);
  auto best_result_in_subsets =
      result0.distance < result1.distance ? result0 : result1;
  // Stores the points whose separation along the X-axis is less than min_d.
  vector<Point> remain;
  for (const Point& p : points) {
    if (abs(p.x - points[mid].x) < best_result_in_subsets.distance) {
      remain.emplace_back(p);
    }
  }

  auto mid_ret =
      FindClosestPairInRemain(&remain, best_result_in_subsets.distance);
  return mid_ret.distance < best_result_in_subsets.distance
             ? mid_ret
             : best_result_in_subsets;
}

// Returns the closest two points and the distance between them.
PairOfPointsWithDistance SolveByEnumerateAllPairs(const vector<Point>& points,
                                                  int begin, int end) {
  PairOfPointsWithDistance ret;
  ret.distance = numeric_limits<double>::max();
  for (int i = begin; i < end; ++i) {
    for (int j = i + 1; j < end; ++j) {
      double dis = Distance(points[i], points[j]);
      if (dis < ret.distance) {
        ret = {points[i], points[j], dis};
      }
    }
  }
  return ret;
}

// Returns the closest two points and its distance as a tuple.
PairOfPointsWithDistance FindClosestPairInRemain(vector<Point>* remain,
                                                 double d) {
  sort(remain->begin(), remain->end(),
       [](const Point& a, const Point& b) { return a.y < b.y; });

  // At most six points in remain.
  PairOfPointsWithDistance ret;
  ret.distance = numeric_limits<double>::max();
  for (int i = 0; i < remain->size(); ++i) {
    for (int j = i + 1;
         j < remain->size() && (*remain)[j].y - (*remain)[i].y < d; ++j) {
      double dis = Distance((*remain)[i], (*remain)[j]);
      if (dis < ret.distance) {
        ret = {(*remain)[i], (*remain)[j], dis};
      }
    }
  }
  return ret;
}

double Distance(const Point& a, const Point& b) {
  return sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 50; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 5000);
      n = dis(gen);
    }
    cout << "num of points = " << n << endl;
    vector<Point> points;
    uniform_int_distribution<int> dis(0, 9999);
    for (int i = 0; i < n; ++i) {
      points.emplace_back(Point{dis(gen), dis(gen)});
    }
    auto p = FindClosestPairPoints(points);
    auto q = SolveByEnumerateAllPairs(points, 0, points.size());
    cout << "p = " << p.p1 << " " << p.p2
         << ", dis = " << Distance(p.p1, p.p2) << endl;
    cout << "q = " << q.p1 << " " << q.p2
         << ", dis = " << Distance(q.p1, q.p2) << endl;
    assert(Distance(p.p1, p.p2) == q.distance);
  }
  return 0;
}
