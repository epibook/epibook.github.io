// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <set>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::max;
using std::min;
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

struct Interval;

// @include
struct Interval {
  int left, right;
};

vector<int> FindMinimumVisits(vector<Interval> intervals) {
  if (intervals.empty()) {
    return {};
  }

  // Sort intervals based on the right endpoints.
  sort(
      intervals.begin(), intervals.end(),
      [](const Interval& a, const Interval& b) { return a.right < b.right; });
  vector<int> visits;
  int last_visit_time = intervals.front().right;
  visits.emplace_back(last_visit_time);
  for (const Interval& interval : intervals) {
    if (interval.left > last_visit_time) {
      // The current right endpoint, last_visit_time, will not cover any more
      // intervals.
      last_visit_time = interval.right;
      visits.emplace_back(last_visit_time);
    }
  }
  return visits;
}
// @exclude

// O(n^2) checking solution.
void CheckAns(const vector<Interval>& intervals, const vector<int>& ans) {
  deque<bool> is_visited(intervals.size(), false);
  for (int a : ans) {
    for (size_t i = 0; i < intervals.size(); ++i) {
      if (a >= intervals[i].left && a <= intervals[i].right) {
        is_visited[i] = true;
      }
    }
  }

  for (bool b : is_visited) {
    assert(b == true);
  }
}

void SimpleTest() {
  vector<Interval> intervals = {Interval{1, 4},  Interval{2, 8},
                                Interval{3, 6},  Interval{3, 5},
                                Interval{7, 10}, Interval{9, 11}};
  vector<int> ans = FindMinimumVisits(intervals);
  vector<int> golden = {4, 10};
  assert(equal(ans.begin(), ans.end(), golden.begin(), golden.end()));

  intervals = {Interval{1, 2}, Interval{2, 3}, Interval{3, 4},
               Interval{4, 5}, Interval{5, 6}, Interval{6, 7}};
  ans = FindMinimumVisits(intervals);
  golden = {2, 4, 6};
  assert(equal(ans.begin(), ans.end(), golden.begin(), golden.end()));

  intervals = {Interval{1, 5}, Interval{2, 3}, Interval{3, 4}};
  ans = FindMinimumVisits(intervals);
  golden = {3};
  assert(equal(ans.begin(), ans.end(), golden.begin(), golden.end()));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    cout << "Test " << times << endl;
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<Interval> A;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> left_dis(0, 9999);
      int left = left_dis(gen);
      uniform_int_distribution<int> right_dis(left, left + 100);
      int right = right_dis(gen);
      A.emplace_back(Interval{left, right});
    }
    vector<int> ans(FindMinimumVisits(A));
    CheckAns(A, ans);
  }
  return 0;
}
