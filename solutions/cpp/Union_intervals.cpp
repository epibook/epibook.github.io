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
    bool isClose;
    int val;
  };

 public:
  bool operator<(const Interval& i) const {
    return left.val != i.left.val ? left.val < i.left.val
                                  : (left.isClose && !i.left.isClose);
  }

  Endpoint left, right;
};

vector<Interval> UnionIntervals(vector<Interval> I) {
  // Empty input.
  if (I.empty()) {
    return {};
  }

  // Sorts intervals according to their left endpoints.
  sort(I.begin(), I.end());
  Interval curr(I.front());
  vector<Interval> uni;
  for (int i = 1; i < I.size(); ++i) {
    if (I[i].left.val < curr.right.val ||
        (I[i].left.val == curr.right.val &&
         (I[i].left.isClose || curr.right.isClose))) {
      if (I[i].right.val > curr.right.val ||
          (I[i].right.val == curr.right.val && I[i].right.isClose)) {
        curr.right = I[i].right;
      }
    } else {
      uni.emplace_back(curr);
      curr = I[i];
    }
  }
  uni.emplace_back(curr);
  return uni;
}
// @exclude

void CheckIntervals(const vector<Interval>& A) {
  // Only check the intervals do not overlap with each other.
  for (size_t i = 1; i < A.size(); ++i) {
    assert(A[i - 1].right.val < A[i].left.val ||
           (A[i - 1].right.val == A[i].left.val && !A[i - 1].right.isClose &&
            !A[i].left.isClose));
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
      temp.left.isClose = zero_or_one(gen), temp.left.val = dis1(gen);
      uniform_int_distribution<int> dis2(temp.left.val + 1,
                                         temp.left.val + 100);
      temp.right.isClose = zero_or_one(gen), temp.right.val = dis2(gen);
      A.emplace_back(temp);
    }
    vector<Interval> ret = UnionIntervals(A);
    if (!ret.empty()) {
      CheckIntervals(ret);
    }
  }
  return 0;
}
