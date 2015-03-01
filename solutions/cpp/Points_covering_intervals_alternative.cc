// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

struct EndPoint;
vector<int> FindMinimumVisitsHelper(const vector<EndPoint>& endpoints);

// @include
struct Interval {
  int left, right;
};

struct EndPoint {
  bool operator<(const EndPoint& that) const {
    const int a = is_left ? ptr->left : ptr->right,
              b = that.is_left ? that.ptr->left : that.ptr->right;
    return a < b;
  }

  const Interval* ptr;
  bool is_left;
};

vector<int> FindMinimumVisits(const vector<Interval>& I) {
  vector<EndPoint> endpoints;
  for (size_t i = 0; i < I.size(); ++i) {
    endpoints.emplace_back(EndPoint{&I[i], true});
    endpoints.emplace_back(EndPoint{&I[i], false});
  }
  sort(endpoints.begin(), endpoints.end());

  return FindMinimumVisitsHelper(endpoints);
}

vector<int> FindMinimumVisitsHelper(const vector<EndPoint>& endpoints) {
  vector<int> S;  // A minimum set of visit times.
  unordered_set<const Interval*> covered;
  vector<const Interval*> covering;
  for (const auto& e : endpoints) {
    if (e.is_left) {
      covering.emplace_back(e.ptr);
    } else if (covered.find(e.ptr) == covered.end()) {
      // e's interval has not been covered.
      S.emplace_back(e.ptr->right);
      // Adds all intervals in covering to covered.
      covered.insert(covering.cbegin(), covering.cend());
      covering.clear();  // e is contained in all intervals in covering.
    }
  }
  return S;
}
// @exclude

// O(n^2) checking solution.
void CheckAns(const vector<Interval>& I, const vector<int>& ans) {
  deque<bool> is_visited(I.size(), false);
  for (const int& a : ans) {
    for (size_t i = 0; i < I.size(); ++i) {
      if (a >= I[i].left && a <= I[i].right) {
        is_visited[i] = true;
      }
    }
  }

  for (const bool& b : is_visited) {
    assert(b == true);
  }
}

void SimpleTest() {
  vector<Interval> I;
  I.emplace_back(Interval{1, 4});
  I.emplace_back(Interval{2, 8});
  I.emplace_back(Interval{3, 6});
  I.emplace_back(Interval{3, 5});
  I.emplace_back(Interval{7, 10});
  I.emplace_back(Interval{9, 11});
  vector<int> ans = FindMinimumVisits(I);
  assert(ans.size() == 2 && ans[0] == 4 && ans[1] == 10);
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
