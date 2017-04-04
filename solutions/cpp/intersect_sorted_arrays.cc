// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./Intersect_sorted_arrays1.h"
#include "./Intersect_sorted_arrays2.h"
#include "./Intersect_sorted_arrays3.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

void CheckAns(const vector<int> &a, const vector<int> &b,
              const vector<int> &c) {
  assert(equal(a.begin(), a.end(), b.begin(), b.end()));
  assert(equal(b.begin(), b.end(), c.begin(), c.end()));
}

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n, m;
    vector<int> A, B;
    if (argc == 3) {
      n = atoi(argv[1]), m = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen), m = dis(gen);
    }
    uniform_int_distribution<int> n_dis(0, n - 1);
    for (int i = 0; i < n; ++i) {
      A.emplace_back(n_dis(gen));
    }
    uniform_int_distribution<int> m_dis(0, m - 1);
    for (int j = 0; j < m; ++j) {
      B.emplace_back(m_dis(gen));
    }
    sort(A.begin(), A.end());
    sort(B.begin(), B.end());
    vector<int> res1 =
        IntersectTwoSortedArrays1::IntersectTwoSortedArrays(A, B);
    vector<int> res2 =
        IntersectTwoSortedArrays2::IntersectTwoSortedArrays(A, B);
    vector<int> res3 =
        IntersectTwoSortedArrays3::IntersectTwoSortedArrays(A, B);
    CheckAns(res1, res2, res3);
  }
  return 0;
}
