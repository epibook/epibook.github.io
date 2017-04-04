// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
struct DuplicateAndMissing {
  int duplicate, missing;
};

DuplicateAndMissing FindDuplicateMissing(const vector<int>& A) {
  int sum = 0, square_sum = 0;
  for (int i = 0; i < A.size(); ++i) {
    sum += i - A[i], square_sum += i * i - A[i] * A[i];
  }
  return {(square_sum / sum - sum) / 2, (square_sum / sum + sum) / 2};
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(2, 10000);
      n = dis(gen);
    }
    vector<int> A(n);
    iota(A.begin(), A.end(), 0);
    uniform_int_distribution<int> n_dis(0, n - 1);
    int missing_idx = n_dis(gen);
    int missing = A[missing_idx];
    int dup_idx = n_dis(gen);
    while (dup_idx == missing_idx) {
      dup_idx = n_dis(gen);
    }
    int dup = A[dup_idx];
    A[missing_idx] = dup;
    DuplicateAndMissing ans = FindDuplicateMissing(A);
    cout << "times = " << times << endl;
    cout << dup << ' ' << missing << endl;
    cout << ans.duplicate << ' ' << ans.missing << endl;
    assert(ans.duplicate == dup && ans.missing == missing);
  }
  return 0;
}
