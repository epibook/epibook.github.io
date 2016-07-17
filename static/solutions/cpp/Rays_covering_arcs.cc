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
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
template <typename TimeType>
struct Interval {
  TimeType left, right;
};

template <typename TimeType>
struct EndPoint {
  const bool operator<(const EndPoint<TimeType>& that) const {
    const TimeType a = is_left ? ptr->left : ptr->right,
                   b = that.is_left ? that.ptr->left : that.ptr->right;
    return a < b;
  }

  const Interval<TimeType>* ptr;
  bool is_left;
};

template <typename TimeType>
vector<TimeType> find_minimum_visits_helper(
    const vector<EndPoint<TimeType>>& endpoints) {
  vector<TimeType> S;  // a minimum set of visit times
  unordered_set<const Interval<TimeType>*> covered;
  vector<const Interval<TimeType>*> covering;
  for (const EndPoint<TimeType>& e : endpoints) {
    if (e.is_left) {
      covering.emplace_back(e.ptr);
    } else if (covered.find(e.ptr) == covered.end()) {
      // e's interval has not been covered
      S.emplace_back(e.ptr->right);
      // Add all intervals in covering to covered
      covered.insert(covering.cbegin(), covering.cend());
      covering.clear();  // e is contained in all intervals in covering
    }
  }
  return S;
}

template <typename TimeType>
vector<TimeType> find_minimum_visits(const vector<Interval<TimeType>>& I) {
  vector<EndPoint<TimeType>> endpoints;
  for (const Interval<TimeType>& i : I) {
    endpoints.emplace_back(EndPoint<TimeType>{&i, true});
    endpoints.emplace_back(EndPoint<TimeType>{&i, false});
  }
  sort(endpoints.begin(), endpoints.end());

  return find_minimum_visits_helper(endpoints);
}
// @exclude

// O(n^2) checking solution
template <typename TimeType>
void check_ans(const vector<Interval<TimeType>>& I,
               const vector<TimeType>& ans) {
  deque<bool> is_visited(I.size(), false);
  for (const TimeType& a : ans) {
    for (int i = 0; i < I.size(); ++i) {
      if (a >= I[i].left && a <= I[i].right) {
        is_visited[i] = true;
      }
    }
  }

  for (bool b : is_visited) {
    assert(b == true);
  }
}

void simple_test(void) {
  vector<Interval<int>> I;
  I.emplace_back(Interval<int>{1, 4});
  I.emplace_back(Interval<int>{2, 8});
  I.emplace_back(Interval<int>{3, 6});
  I.emplace_back(Interval<int>{3, 5});
  I.emplace_back(Interval<int>{7, 10});
  I.emplace_back(Interval<int>{9, 11});
  vector<int> ans = find_minimum_visits(I);
  assert(ans.size() == 2 && ans[0] == 4 && ans[1] == 10);
}

int main(int argc, char* argv[]) {
  simple_test();
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
    vector<Interval<int>> A;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis1(0, 9999);
      int left = dis1(gen);
      uniform_int_distribution<int> dis2(left + 1, left + 100);
      int right = dis2(gen);
      A.emplace_back(Interval<int>{left, right});
    }
    vector<int> ans(find_minimum_visits(A));
    check_ans(A, ans);
  }
  return 0;
}
