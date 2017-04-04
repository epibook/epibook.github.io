// Copyright (c) 2016 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <random>
#include <vector>

#include "./generating-a-b-sqrt2-improved.h"
#include "./generating-a-b-sqrt2.h"

using std::default_random_engine;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;
using GeneratingABSqrt2::ABSqrt2;

static void SimpleTest(const vector<ABSqrt2>& ans) {
  assert(0.0 == ans[0].val);
  assert(1.0 == ans[1].val);
  assert(sqrt(2.0) == ans[2].val);
  assert(2.0 == ans[3].val);
  assert(1.0 + sqrt(2.0) == ans[4].val);
  assert(2.0 * sqrt(2.0) == ans[5].val);
  assert(3.0 == ans[6].val);
  assert(2.0 + sqrt(2.0) == ans[7].val);
}

static void Check(const vector<ABSqrt2>& ans, int k) {
  assert(ans.size() == k);
  for (size_t i = 1; i < ans.size(); ++i) {
    assert(ans[i].val >= ans[i - 1].val);
  }
}

int main(int argc, char* argv[]) {
  SimpleTest(GeneratingABSqrt2::GenerateFirstKABSqrt2(8));
  SimpleTest(GeneratingABSqrt2Improved::GenerateFirstKABSqrt2(8));
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int k;
    if (argc == 2) {
      k = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      k = dis(gen);
    }
    vector<ABSqrt2> ans1(GeneratingABSqrt2::GenerateFirstKABSqrt2(k));
    Check(ans1, k);
    vector<ABSqrt2> ans2(GeneratingABSqrt2Improved::GenerateFirstKABSqrt2(k));
    Check(ans2, k);
    assert(equal(
        ans1.begin(), ans1.end(), ans2.begin(), ans2.end(),
        [](const ABSqrt2& a, const ABSqrt2& b) { return a.val == b.val; }));
  }
  return 0;
}
