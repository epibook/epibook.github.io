// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <functional>
#include <iostream>
#include <queue>
#include <random>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::function;
using std::hash;
using std::priority_queue;
using std::queue;
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
struct ABSqrt2 {
  ABSqrt2(int a, int b) : a(a), b(b), val(a + b * sqrt(2)) {}

  // @exclude
  // Equal function for hash.
  bool operator==(const ABSqrt2& n) const { return a == n.a && b == n.b; }

  bool operator<(const ABSqrt2& that) const { return val > that.val; }
  // @include
  int a, b;
  double val;
};
// @exclude

// Hash function for ABSqrt2.
struct HashABSqrt2 {
  size_t operator()(const ABSqrt2& n) const {
    return hash<int>()(n.a) ^ hash<int>()(n.b);
  }
};
// @include

vector<ABSqrt2> GenerateFirstKABSqrt2(int k) {
  // Will store the first k numbers of the form a + b sqrt(2).
  vector<ABSqrt2> result;
  result.emplace_back(0, 0);
  int i = 0, j = 0;
  for (int n = 1; n < k; ++n) {
    ABSqrt2 result_i_plus_1(result[i].a + 1, result[i].b);
    ABSqrt2 result_j_plus_sqrt2(result[j].a, result[j].b + 1);
    if (result_i_plus_1.val < result_j_plus_sqrt2.val) {
      ++i;
      result.emplace_back(result_i_plus_1);
    } else if (result_i_plus_1.val > result_j_plus_sqrt2.val) {
      ++j;
      result.emplace_back(result_j_plus_sqrt2);
    } else {  // result_i_plus_1 == result_j_plus_sqrt2.
      ++i, ++j;
      result.emplace_back(result_i_plus_1);
    }
  }
  return result;
}
// @exclude

vector<ABSqrt2> Golden(int k) {
  priority_queue<ABSqrt2, vector<ABSqrt2>> min_heap;
  vector<ABSqrt2> smallest;
  unordered_set<ABSqrt2, HashABSqrt2> hash_table;

  // Initial for 0 + 0 * sqrt(2).
  min_heap.emplace(0, 0);
  hash_table.emplace(0, 0);

  while (smallest.size() < k) {
    ABSqrt2 s(min_heap.top());
    smallest.emplace_back(s);
    hash_table.erase(s);
    min_heap.pop();

    // Add the next two numbers derived from s.
    ABSqrt2 c1(s.a + 1, s.b), c2(s.a, s.b + 1);
    if (hash_table.emplace(c1).second) {
      min_heap.emplace(c1);
    }
    if (hash_table.emplace(c2).second) {
      min_heap.emplace(c2);
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
    assert(ans.size() == gold_res.size() &&
           equal(ans.begin(), ans.end(), gold_res.begin()));
  }
  return 0;
}
