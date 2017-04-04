// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <limits>
#include <random>
#include <stack>
#include <string>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::min;
using std::numeric_limits;
using std::ostream_iterator;
using std::random_device;
using std::stack;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

template <typename Iter>
int TrappingWaterTillEnd(Iter, Iter);

// @include
int CalculateTrappingWater(const vector<int> &heights) {
  // Finds the index with maximum height.
  int max_h =
      distance(begin(heights), max_element(begin(heights), end(heights)));
  return TrappingWaterTillEnd(heights.begin(), heights.begin() + max_h) +
         TrappingWaterTillEnd(
             heights.rbegin(),
             heights.rbegin() + (heights.size() - 1 - max_h));
}

// Assume end is maximum height.
template <typename Iter>
int TrappingWaterTillEnd(Iter begin, Iter end) {
  int sum = 0, highest_level_seen = numeric_limits<int>::min();
  for (Iter iter = begin; iter != end; ++iter) {
    if (*iter >= highest_level_seen) {
      highest_level_seen = *iter;
    } else {
      sum += highest_level_seen - *iter;
    }
  }
  return sum;
}
// @exclude

// Stack approach, O(n) time, O(n) space
int CheckAnswer(const vector<int> &A) {
  struct HeightBound {
    int left_bound, right_bound;
  };

  stack<HeightBound> s;
  int sum = 0;
  for (int i = 0; i < A.size(); ++i) {
    while (!s.empty() && s.top().right_bound <= A[i]) {
      int bottom = s.top().right_bound;
      s.pop();
      if (s.empty()) {
        break;
      }
      int top = min(s.top().right_bound, A[i]);
      sum += (top - bottom) * (i - s.top().left_bound - 1);
    }
    s.emplace(HeightBound{i, A[i]});
  }
  return sum;
}

void SmallTest() {
  vector<int> A = {1, 0, 3, 2, 5, 0, 1};
  assert(CalculateTrappingWater(A) == 3);
  A = {1, 2, 1, 3, 4, 4, 5, 6, 2, 1, 3, 1, 3, 2, 1, 2, 4, 1};
  assert(CalculateTrappingWater(A) == 18);
}

int main(int argc, char *argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    int n;
    if (argc == 2) {
      n = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> n_dis(1, 1000);
      n = n_dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> A_dis(0, 10);
    generate_n(back_inserter(A), n, [&] { return A_dis(gen); });
    copy(A.cbegin(), A.cend(), ostream_iterator<int>(cout, " "));
    cout << endl;
    cout << CheckAnswer(A) << " " << CalculateTrappingWater(A) << endl;
    assert(CheckAnswer(A) == CalculateTrappingWater(A));
  }
  return 0;
}
