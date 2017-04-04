// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <queue>
#include <random>
#include <set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::hash;
using std::queue;
using std::random_device;
using std::set;
using std::uniform_int_distribution;
using std::vector;

// These numbers have very interesting property, and people called it ugly
// numbers. It is also called Quadratic integer rings.
// @include
struct ABSqrt2 {
  ABSqrt2(int a, int b) : a(a), b(b), val(a + b * sqrt(2)) {}

  bool operator<(const ABSqrt2& that) const { return val < that.val; }

  int a, b;
  double val;
};

vector<ABSqrt2> GenerateFirstKABSqrt2(int k) {
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
// @exclude

vector<ABSqrt2> Golden(int k) {
  vector<ABSqrt2> smallest;
  smallest.emplace_back(0, 0);
  queue<ABSqrt2> q1, q2;
  q1.emplace(1, 0);
  q2.emplace(0, 1);
  for (int i = 1; i < k; ++i) {
    auto q1_f = q1.front(), q2_f = q2.front();
    if (q1_f.val < q2_f.val) {
      smallest.emplace_back(q1_f);
      q1.pop();
      q1.emplace(q1_f.a + 1, q1_f.b);
      q2.emplace(q1_f.a, q1_f.b + 1);
    } else {
      smallest.emplace_back(q2_f);
      q2.pop();
      q2.emplace(q2_f.a, q2_f.b + 1);
    }
  }
  return smallest;
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
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int k;
    if (argc == 2) {
      k = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      k = dis(gen);
    }
    vector<ABSqrt2> ans(GenerateFirstKABSqrt2(k));
    for (size_t i = 0; i < ans.size(); ++i) {
      cout << ans[i].a << ' ' << ans[i].b << ' ' << ans[i].val << endl;
      if (i > 0) {
        assert(ans[i].val >= ans[i - 1].val);
      }
    }
    auto gold_res = Golden(k);
    for (size_t i = 0; i < k; ++i) {
      assert(ans[i].val == gold_res[i].val);
    }
  }
  return 0;
}
