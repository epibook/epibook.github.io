// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::set;
using std::uniform_int_distribution;
using std::vector;

// @include
struct Interval {
  int left, right;
};

struct LeftComp {
  bool operator()(const Interval* a, const Interval* b) const {
    return a->left != b->left ? a->left < b->left : a->right < b->right;
  }
};

struct RightComp {
  bool operator()(const Interval* a, const Interval* b) const {
    return a->right != b->right ? a->right < b->right : a->left < b->left;
  }
};

vector<int> FindMinimumVisits(const vector<Interval>& intervals) {
  set<const Interval*, LeftComp> L;
  set<const Interval*, RightComp> R;
  for (const Interval& i : intervals) {
    L.emplace(&i), R.emplace(&i);
  }

  vector<int> S;
  while (!L.empty() && !R.empty()) {
    int b = (*R.cbegin())->right;
    S.emplace_back(b);

    // Removes the intervals which intersect with R.cbegin().
    auto it = L.cbegin();
    while (it != L.cend() && (*it)->left <= b) {
      R.erase(*it);
      L.erase(it++);
    }
  }
  return S;
}
// @exclude

// O(n^2) checking solution
void CheckAns(const vector<Interval>& intervals, const vector<int>& ans) {
  deque<bool> is_visited(intervals.size(), false);
  for (int a : ans) {
    for (int i = 0; i < intervals.size(); ++i) {
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
  vector<Interval> intervals;
  intervals.emplace_back(Interval{1, 4});
  intervals.emplace_back(Interval{2, 8});
  intervals.emplace_back(Interval{3, 6});
  intervals.emplace_back(Interval{3, 5});
  intervals.emplace_back(Interval{7, 10});
  intervals.emplace_back(Interval{9, 11});
  vector<int> ans = FindMinimumVisits(intervals);
  assert(ans.size() == 2 && ans[0] == 4 && ans[1] == 10);

  intervals.clear();
  intervals.emplace_back(Interval{1, 2});
  intervals.emplace_back(Interval{2, 3});
  intervals.emplace_back(Interval{3, 4});
  intervals.emplace_back(Interval{4, 5});
  intervals.emplace_back(Interval{5, 6});
  intervals.emplace_back(Interval{6, 7});
  ans = FindMinimumVisits(intervals);
  assert(ans.size() == 3 && ans[0] == 2 && ans[1] == 4 && ans[2] == 6);
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
      uniform_int_distribution<int> dis1(0, 9999);
      int left = dis1(gen);
      uniform_int_distribution<int> dis2(left + 1, left + 100);
      int right = dis2(gen);
      A.emplace_back(Interval{left, right});
    }
    vector<int> ans(FindMinimumVisits(A));
    CheckAns(A, ans);
  }
  return 0;
}
