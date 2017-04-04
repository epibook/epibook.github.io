// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <random>
#include <vector>

using std::default_random_engine;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
struct Interval {
 private:
  struct Endpoint {
    bool is_closed;
    int val;
  };

 public:
  bool operator<(const Interval& i) const {
    if (left.val != i.left.val) {
      return left.val < i.left.val;
    }
    // Left endpoints are equal, so now see if one is closed and the other
    // open - closed intervals should appear first.
    return left.is_closed && !i.left.is_closed;
  }

  Endpoint left, right;
};

vector<Interval> UnionOfIntervals(vector<Interval> intervals) {
  // Empty input.
  if (intervals.empty()) {
    return {};
  }

  // Sort intervals according to left endpoints of intervals.
  sort(intervals.begin(), intervals.end());
  vector<Interval> result;
  for (Interval i : intervals) {
    if (!result.empty() &&
        (i.left.val < result.back().right.val ||
         (i.left.val == result.back().right.val &&
          (i.left.is_closed || result.back().right.is_closed)))) {
      if (i.right.val > result.back().right.val ||
          (i.right.val == result.back().right.val && i.right.is_closed)) {
        result.back().right = i.right;
      }
    } else {
      result.emplace_back(i);
    }
  }
  return result;
}
// @exclude

void CheckIntervals(const vector<Interval>& A) {
  // Only check the intervals do not overlap with each other.
  for (size_t i = 1; i < A.size(); ++i) {
    assert(A[i - 1].right.val < A[i].left.val ||
           (A[i - 1].right.val == A[i].left.val &&
            !A[i - 1].right.is_closed && !A[i].left.is_closed));
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 1000);
      n = dis(gen);
    }
    vector<Interval> A;
    for (int i = 0; i < n; ++i) {
      Interval temp;
      uniform_int_distribution<int> zero_or_one(0, 1);
      uniform_int_distribution<int> dis1(0, 9999);
      temp.left.is_closed = zero_or_one(gen), temp.left.val = dis1(gen);
      uniform_int_distribution<int> dis2(temp.left.val + 1,
                                         temp.left.val + 100);
      temp.right.is_closed = zero_or_one(gen), temp.right.val = dis2(gen);
      A.emplace_back(temp);
    }
    vector<Interval> ret = UnionOfIntervals(A);
    if (!ret.empty()) {
      CheckIntervals(ret);
    }
  }
  return 0;
}
