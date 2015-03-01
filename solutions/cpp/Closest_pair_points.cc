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
tuple<Point, Point, double> FindClosestPairPointsHelper(const vector<Point>& P,
                                                        int s, int e);
tuple<Point, Point, double> BruteForce(const vector<Point>& P, int s, int e);
tuple<Point, Point, double> FindClosestPairInRemain(vector<Point>* P, double d);
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

pair<Point, Point> FindClosestPairPoints(vector<Point> P) {
  sort(P.begin(), P.end(),
       [](const Point& a, const Point& b) -> bool { return a.x < b.x; });
  auto ret = FindClosestPairPointsHelper(P, 0, P.size());
  return {get<0>(ret), get<1>(ret)};
}

// Returns the closest two points and its distance as a tuple.
tuple<Point, Point, double> FindClosestPairPointsHelper(
    const vector<Point>& P, int s, int e) {
  if (e - s <= 3) {  // Brute-force to find answer if there are <= 3 points.
    return BruteForce(P, s, e);
  }

  int mid = (e + s) / 2;
  auto l_ret = FindClosestPairPointsHelper(P, s, mid);
  auto r_ret = FindClosestPairPointsHelper(P, mid, e);
  auto min_l_r = get<2>(l_ret) < get<2>(r_ret) ? l_ret : r_ret;
  vector<Point> remain;  // Stores the points whose x-dis < min_d.
  for (const Point& p : P) {
    if (abs(p.x - P[mid].x) < get<2>(min_l_r)) {
      remain.emplace_back(p);
    }
  }

  auto mid_ret = FindClosestPairInRemain(&remain, get<2>(min_l_r));
  return get<2>(mid_ret) < get<2>(min_l_r) ? mid_ret : min_l_r;
}

// Returns the closest two points and the distance between them.
tuple<Point, Point, double> BruteForce(const vector<Point>& P, int s, int e) {
  tuple<Point, Point, double> ret;
  get<2>(ret) = numeric_limits<double>::max();
  for (int i = s; i < e; ++i) {
    for (int j = i + 1; j < e; ++j) {
      double dis = Distance(P[i], P[j]);
      if (dis < get<2>(ret)) {
        ret = {P[i], P[j], dis};
      }
    }
  }
  return ret;
}

// Returns the closest two points and its distance as a tuple.
tuple<Point, Point, double> FindClosestPairInRemain(vector<Point>* P,
                                                    double d) {
  sort(P->begin(), P->end(),
       [](const Point& a, const Point& b) -> bool { return a.y < b.y; });

  // At most six points in P.
  tuple<Point, Point, double> ret;
  get<2>(ret) = numeric_limits<double>::max();
  for (int i = 0; i < P->size(); ++i) {
    for (int j = i + 1; j < P->size() && (*P)[j].y - (*P)[i].y < d; ++j) {
      double dis = Distance((*P)[i], (*P)[j]);
      if (dis < get<2>(ret)) {
        ret = {(*P)[i], (*P)[j], dis};
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
    auto q = BruteForce(points, 0, points.size());
    cout << "p = " << p.first << " " << p.second
         << ", dis = " << Distance(p.first, p.second) << endl;
    cout << "q = " << get<0>(q) << " " << get<1>(q)
         << ", dis = " << Distance(get<0>(q), get<1>(q)) << endl;
    assert(Distance(p.first, p.second) == get<2>(q));
  }
  return 0;
}
