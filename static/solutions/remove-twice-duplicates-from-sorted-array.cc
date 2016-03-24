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
size_t remove_twice_duplicates(int* A, size_t n) {
  if (!n) {
    return 0;
  }

  size_t i = 0, j = 1;
  while (j < n) {
    if (A[i] != A[j] || (A[i] == A[j] && (i == 0 || A[i - 1] != A[i]))) {
      ++i;
      A[i] = A[j];
    }
    ++j;
  }
  return i + 1;
}
// @exclude

void check_ans(const vector<int> A, size_t n) {
  for (size_t i = 1; i < n; ++i) {
    assert(A[i - 1] != A[i] || (i == 1 || A[i - 2] != A[i - 1]));
  }
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  size_t n;
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    uniform_int_distribution<size_t> dis_n(0, 10000);
    n = dis_n(gen);
  }
  for (int times = 0; times < 1000; ++times) {
    uniform_int_distribution<int> A_dis(-1000, 1000);
    vector<int> A;
    generate_n(back_inserter(A), n, [&] { return A_dis(gen); });
    sort(A.begin(), A.end());
    auto size = remove_twice_duplicates(A.data(), n);
    check_ans(A, size);
    cout << size << endl;
  }
  return 0;
}
