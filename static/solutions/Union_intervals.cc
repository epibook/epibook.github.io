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
    bool isClosed;
    int val;
  };

 public:
  bool operator<(const Interval& i) const {
    if (left.val != i.left.val) {
      return left.val < i.left.val;
    }
    // Left endpoints are equal, so now see if one is closed and the other
    // open
    // - closed intervals should appear first.
    return left.isClosed && !i.left.isClosed;
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
  Interval curr(intervals.front());
  vector<Interval> result;
  for (int i = 1; i < intervals.size(); ++i) {
    if (intervals[i].left.val < curr.right.val ||
        (intervals[i].left.val == curr.right.val &&
         (intervals[i].left.isClosed || curr.right.isClosed))) {
      if (intervals[i].right.val > curr.right.val ||
          (intervals[i].right.val == curr.right.val &&
           intervals[i].right.isClosed)) {
        curr.right = intervals[i].right;
      }
    } else {
      result.emplace_back(curr);
      curr = intervals[i];
    }
  }
  result.emplace_back(curr);
  return result;
}
// @exclude

void CheckIntervals(const vector<Interval>& A) {
  // Only check the intervals do not overlap with each other.
  for (size_t i = 1; i < A.size(); ++i) {
    assert(A[i - 1].right.val < A[i].left.val ||
           (A[i - 1].right.val == A[i].left.val && !A[i - 1].right.isClosed &&
            !A[i].left.isClosed));
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
      temp.left.isClosed = zero_or_one(gen), temp.left.val = dis1(gen);
      uniform_int_distribution<int> dis2(temp.left.val + 1,
                                         temp.left.val + 100);
      temp.right.isClosed = zero_or_one(gen), temp.right.val = dis2(gen);
      A.emplace_back(temp);
    }
    vector<Interval> ret = UnionOfIntervals(A);
    if (!ret.empty()) {
      CheckIntervals(ret);
    }
  }
  return 0;
}
