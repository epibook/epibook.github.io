// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

// @include
void MergeTwoSortedArrays(int A[], int m, int B[], int n) {
  int a = m - 1, b = n - 1, write_idx = m + n - 1;
  while (a >= 0 && b >= 0) {
    A[write_idx--] = A[a] > B[b] ? A[a--] : B[b--];
  }
  while (b >= 0) {
    A[write_idx--] = B[b--];
  }
}
// @exclude

void CheckAns(const vector<int>& A) {
  for (size_t i = 1; i < A.size(); ++i) {
    assert(A[i - 1] <= A[i]);
  }
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    size_t m, n;
    if (argc == 3) {
      m = stoul(argv[1]), n = stoul(argv[2]);
    } else {
      uniform_int_distribution<size_t> size_dis(0, 10000);
      m = size_dis(gen), n = size_dis(gen);
    }
    uniform_int_distribution<int> dis(-(m + n), m + n);
    vector<int> A(m + n), B;
    for (size_t i = 0; i < m; ++i) {
      A[i] = dis(gen);
    }
    generate_n(back_inserter(B), n, [&] { return dis(gen); });
    sort(A.begin(), A.begin() + m), sort(B.begin(), B.end());
    MergeTwoSortedArrays(A.data(), m, B.data(), n);
    CheckAns(A);
  }
  return 0;
}
