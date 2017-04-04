// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <functional>
#include <iostream>
#include <random>
#include <set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::min;
using std::random_device;
using std::set;
using std::uniform_int_distribution;
using std::vector;

// @include
struct ABSqrt2 {
  ABSqrt2(int a, int b) : a(a), b(b), val(a + b * sqrt(2)) {}

  bool operator<(const ABSqrt2& that) const { return val < that.val; }

  int a, b;
  double val;
};

vector<ABSqrt2> GenerateFirstKABSqrt2(int k) {
  // Will store the first k numbers of the form a + b sqrt(2).
  vector<ABSqrt2> result;
  result.emplace_back(0, 0);
  int i = 0, j = 0;
  for (int n = 1; n < k; ++n) {
    ABSqrt2 result_i_plus_1(result[i].a + 1, result[i].b);
    ABSqrt2 result_j_plus_sqrt2(result[j].a, result[j].b + 1);
    result.emplace_back(min(result_i_plus_1, result_j_plus_sqrt2));
    if (result_i_plus_1.val == result.back().val) {
      ++i;
    }
    if (result_j_plus_sqrt2.val == result.back().val) {
      ++j;
    }
  }
  return result;
}
// @exclude

vector<ABSqrt2> Golden(int k) {
  set<ABSqrt2> candidates;
  // Initial for 0 + 0 * sqrt(2).
  candidates.emplace(0, 0);

  vector<ABSqrt2> result;
  while (result.size() < k) {
    auto next_smallest = candidates.cbegin();
    result.emplace_back(*next_smallest);

    // Adds the next two numbers derived from next_smallest.
    candidates.emplace(next_smallest->a + 1, next_smallest->b);
    candidates.emplace(next_smallest->a, next_smallest->b + 1);
    candidates.erase(next_smallest);
  }
  return result;
}

static void SimpleTest() {
  vector<ABSqrt2> ans = GenerateFirstKABSqrt2(8);
  assert(0.0 == ans[0].val);
  assert(1.0 == ans[1].val);
  assert(sqrt(2.0) == ans[2].val);
  assert(2.0 == ans[3].val);
  assert(1.0 + sqrt(2.0) == ans[4].val);
  assert(2.0 * sqrt(2.0) == ans[5].val);
  assert(3.0 == ans[6].val);
  assert(2.0 + sqrt(2.0) == ans[7].val);
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  SimpleTest();
  for (int times = 0; times < 1000; ++times) {
    int k;
    if (argc == 2) {
      k = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      k = dis(gen);
    }
    vector<ABSqrt2> ans(GenerateFirstKABSqrt2(k));
    assert(ans.size() == k);
    for (size_t i = 0; i < ans.size(); ++i) {
      cout << ans[i].a << ' ' << ans[i].b << ' ' << ans[i].val << endl;
      if (i > 0) {
        assert(ans[i].val >= ans[i - 1].val);
      }
    }
    auto gold_res = Golden(k);
    assert(equal(
        ans.begin(), ans.end(), gold_res.begin(), gold_res.end(),
        [](const ABSqrt2& a, const ABSqrt2& b) { return a.val == b.val; }));
  }
  return 0;
}
