// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <queue>
#include <random>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::hash;
using std::priority_queue;
using std::queue;
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
struct Num {
  Num(int a, int b) : a(a), b(b), val(a + b * sqrt(2)) {}

  // @exclude
  // Equal function for hash.
  bool operator==(const Num& n) const { return a == n.a && b == n.b; }

  // @include
  int a, b;
  double val;
};
// @exclude
struct CompareNum {
  bool operator()(Num const& a, Num const& b) { return a.val > b.val; }
};

// Hash function for Num.
struct HashNum {
  size_t operator()(const Num& n) const {
    return hash<int>()(n.a) ^ hash<int>()(n.b);
  }
};
// @include

vector<Num> GenerateFirstK(int k) {
  vector<Num> result;  // Stores the first-k Num.
  result.emplace_back(0, 0);
  size_t i = 0, j = 0;
  for (int n = 0; n < k; ++n) {
    Num x(result[i].a + 1, result[i].b), y(result[j].a, result[j].b + 1);
    if (x.val < y.val) {
      ++i;
      result.emplace_back(x);
    } else if (x.val > y.val) {
      ++j;
      result.emplace_back(y);
    } else {  // x == y.
      ++i, ++j;
      result.emplace_back(x);
    }
  }
  return result;
}
// @exclude

vector<Num> Golden(int k) {
  priority_queue<Num, vector<Num>, CompareNum> min_heap;
  vector<Num> smallest;
  unordered_set<Num, HashNum> hash;

  // Initial for 0 + 0 * sqrt(2).
  min_heap.emplace(0, 0);
  hash.emplace(0, 0);

  while (smallest.size() < k) {
    Num s(min_heap.top());
    smallest.emplace_back(s);
    hash.erase(s);
    min_heap.pop();

    // Add the next two numbers derived from s.
    Num c1(s.a + 1, s.b), c2(s.a, s.b + 1);
    if (hash.emplace(c1).second) {
      min_heap.emplace(c1);
    }
    if (hash.emplace(c2).second) {
      min_heap.emplace(c2);
    }
  }
  return smallest;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int k;
    if (argc == 2) {
      k = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      k = dis(gen);
    }
    vector<Num> ans(GenerateFirstK(k));
    for (size_t i = 0; i < ans.size(); ++i) {
      cout << ans[i].a << ' ' << ans[i].b << ' ' << ans[i].val << endl;
      if (i > 0) {
        assert(ans[i].val >= ans[i - 1].val);
      }
    }
    auto gold_res = Golden(k);
    for (size_t i = 0; i < k; ++i) {
      assert(ans[i] == gold_res[i]);
      //cout << "first " << ans[i].a << " " << ans[i].b << " " << ans[i].val << endl;
      //cout << "second " << gold_res[i].a << " " << gold_res[i].b << " " << gold_res[i].val << endl;
    }
  }
  return 0;
}
