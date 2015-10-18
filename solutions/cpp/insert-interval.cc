// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

// @include
struct Interval {
  int left, right;
};

vector<Interval> AddInterval(const vector<Interval>& disjoint_intervals,
                             Interval new_interval) {
  size_t i = 0;
  vector<Interval> result;

  // Processes intervals in disjoint_intervals which come before new_interval.
  while (i < disjoint_intervals.size() &&
         new_interval.left > disjoint_intervals[i].right) {
    result.emplace_back(disjoint_intervals[i++]);
  }

  // Processes intervals in disjoint_intervals which overlap with
  // new_interval.
  while (i < disjoint_intervals.size() &&
         new_interval.right >= disjoint_intervals[i].left) {
    // If [a, b] and [c, d] overlap, their union is [min(a, c),max(b, d)].
    new_interval = {min(new_interval.left, disjoint_intervals[i].left),
                    max(new_interval.right, disjoint_intervals[i].right)};
    ++i;
  }
  result.emplace_back(new_interval);

  // Processes intervals in disjoint_intervals which come after new_interval.
  result.insert(result.end(), disjoint_intervals.begin() + i,
                disjoint_intervals.end());
  return result;
}
// @exclude

void CheckIntervals(const vector<Interval>& result) {
  // Only check the intervals do not overlap with each other.
  for (size_t i = 1; i < result.size(); ++i) {
    assert(result[i - 1].right < result[i].left);
  }
}

void SmallTest() {
  vector<Interval> A = {{1, 5}};
  Interval new_one = {0, 3};
  auto result = AddInterval(A, new_one);
  assert(result.size() == 1 && result.front().left == 0 &&
         result.front().right == 5);
  new_one = {0, 0};
  result = AddInterval(A, new_one);
  assert(result.size() == 2 && result.front().left == 0 &&
         result.front().right == 0 && result.back().left == 1 &&
         result.back().right == 5);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    size_t n;
    if (argc == 2) {
      n = stoul(argv[1]);
    } else {
      uniform_int_distribution<size_t> dis(1, 10000);
      n = dis(gen);
    }
    vector<Interval> A;
    int pre = 0;
    uniform_int_distribution<int> dis(1, 10);
    for (size_t i = 0; i < n; ++i) {
      Interval temp;
      temp.left = pre + dis(gen);
      temp.right = temp.left + dis(gen);
      pre = temp.right;
      A.emplace_back(temp);
    }
    uniform_int_distribution<int> tar_dis(0, 100);
    Interval target;
    target.left = tar_dis(gen);
    target.right = target.left + tar_dis(gen);
    auto result = AddInterval(A, target);
    CheckIntervals(result);
  }
  return 0;
}
