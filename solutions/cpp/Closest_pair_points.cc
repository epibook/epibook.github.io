// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <iostream>
#include <limits>
#include <random>
#include <tuple>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::get;
using std::numeric_limits;
using std::ostream;
using std::pair;
using std::random_device;
using std::tuple;
using std::uniform_int_distribution;
using std::vector;

struct Point;
tuple<Point, Point, double> FindClosestPairPointsInSubarray(
    const vector<Point>& points, int s, int e);
tuple<Point, Point, double> SolveByEnumerateAllPairs(
    const vector<Point>& points, int s, int e);
tuple<Point, Point, double> FindClosestPairInRemain(vector<Point>* points,
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

pair<Point, Point> FindClosestPairPoints(vector<Point> points) {
  sort(points.begin(), points.end(),
       [](const Point& a, const Point& b) -> bool { return a.x < b.x; });
  auto closest_two_points_with_distance =
      FindClosestPairPointsInSubarray(points, 0, points.size());
  return {get<0>(closest_two_points_with_distance),
          get<1>(closest_two_points_with_distance)};
}

// Returns the closest two points and their distance as a tuple in
// points[begin : end - 1].
tuple<Point, Point, double> FindClosestPairPointsInSubarray(
    const vector<Point>& points, int begin, int end) {
  if (end - begin <= kBruteForceThreshold) {  // Switch to brute-force.
    return SolveByEnumerateAllPairs(points, begin, end);
  }

  int mid = (end + begin) / 2;
  auto result0 = FindClosestPairPointsInSubarray(points, begin, mid);
  auto result1 = FindClosestPairPointsInSubarray(points, mid, end);
  auto best_result_in_subsets =
      get<2>(result0) < get<2>(result1) ? result0 : result1;
  // Stores the points whose separation along the X-axis is less than min_d.
  vector<Point> remain;
  for (const Point& p : points) {
    if (abs(p.x - points[mid].x) < get<2>(best_result_in_subsets)) {
      remain.emplace_back(p);
    }
  }

  auto mid_ret =
      FindClosestPairInRemain(&remain, get<2>(best_result_in_subsets));
  return get<2>(mid_ret) < get<2>(best_result_in_subsets)
             ? mid_ret
             : best_result_in_subsets;
}

// Returns the closest two points and the distance between them.
tuple<Point, Point, double> SolveByEnumerateAllPairs(
    const vector<Point>& points, int begin, int end) {
  tuple<Point, Point, double> ret;
  get<2>(ret) = numeric_limits<double>::max();
  for (int i = begin; i < end; ++i) {
    for (int j = i + 1; j < end; ++j) {
      double dis = Distance(points[i], points[j]);
      if (dis < get<2>(ret)) {
        ret = {points[i], points[j], dis};
      }
    }
  }
  return ret;
}

// Returns the closest two points and its distance as a tuple.
tuple<Point, Point, double> FindClosestPairInRemain(vector<Point>* remain,
                                                    double d) {
  sort(remain->begin(), remain->end(),
       [](const Point& a, const Point& b) -> bool { return a.y < b.y; });

  // At most six points in remain.
  tuple<Point, Point, double> ret;
  get<2>(ret) = numeric_limits<double>::max();
  for (int i = 0; i < remain->size(); ++i) {
    for (int j = i + 1;
         j < remain->size() && (*remain)[j].y - (*remain)[i].y < d; ++j) {
      double dis = Distance((*remain)[i], (*remain)[j]);
      if (dis < get<2>(ret)) {
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
    cout << "p = " << p.first << " " << p.second
         << ", dis = " << Distance(p.first, p.second) << endl;
    cout << "q = " << get<0>(q) << " " << get<1>(q)
         << ", dis = " << Distance(get<0>(q), get<1>(q)) << endl;
    assert(Distance(p.first, p.second) == get<2>(q));
  }
  return 0;
}
