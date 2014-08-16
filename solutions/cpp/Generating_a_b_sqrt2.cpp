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

// @include
struct Num {
  Num(int a, int b) : a(a), b(b), val(a + b * sqrt(2)) {}

  bool operator<(const Num& that) const {
    return val < that.val;
  }

  int a, b;
  double val;
};

vector<Num> GenerateFirstK(int k) {
  set<Num> T;
  vector<Num> res;
  T.emplace(0, 0);

  while (res.size() < k) {
    auto it = T.cbegin();
    res.emplace_back(*it);

    // Adds the next two numbers derived from s.
    T.emplace(it->a + 1, it->b), T.emplace(it->a, it->b + 1);
    T.erase(it);
  }
  return res;
}
// @exclude

vector<Num> Golden(int k) {
  vector<Num> smallest;
  smallest.emplace_back(0, 0);
  queue<Num> q1, q2;
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
      assert(ans[i].val == gold_res[i].val);
      // cout << "first " << ans[i].a << " " << ans[i].b << " " << ans[i].val <<
      // endl;
      // cout << "second " << gold_res[i].a << " " << gold_res[i].b << " " <<
      // gold_res[i].val << endl;
    }
  }
  return 0;
}
