// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

// @include
int three_sum_closest(vector<int> A, int target) {
  sort(A.begin(), A.end());
  int res = A[0] + A[1] + A[2];

  for (size_t i = 0; i < A.size(); ++i) {
    if (i == 0 || A[i] != A[i - 1]) {
      size_t start = i + 1, end = A.size() - 1;
      while (start < end) {
        int sum = A[i] + A[start] + A[end];
        if (sum == target) {  // early return.
          return sum;
        }

        if (abs(sum - target) < abs(res - target)) {
          res = sum;
        }

        if (sum < target) {
          ++start;
        } else {
          --end;
        }
      }
    }
  }
  return res;
}
// @exclude

void small_test() {
  vector<int> A = {-3, -2, -5, 3, -4};
  assert(three_sum_closest(A, -1) == -2);
}

int check_ans(vector<int> A, int target) {
  sort(A.begin(), A.end());
  int res = A[0] + A[1] + A[2];
  for (size_t i = 0; i < A.size(); ++i) {
    for (size_t j = i + 1; j < A.size(); ++j) {
      for (size_t k = j + 1;
           k < A.size() && A[i] + A[j] + A[k] - target <= abs(res - target);
           ++k) {
        // cout << i << " " << j << " " << k << endl;
        int sum = A[i] + A[j] + A[k];
        if (sum == target) {
          return sum;
        }

        if (abs(sum - target) < abs(res - target)) {
          res = sum;
        }
      }
    }
  }
  cout << "res = " << res << endl;
  return res;
}

int main(int argc, char** argv) {
  small_test();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 2000; ++times) {
    int n;
    if (argc == 2) {
      n = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> n_dis(3, 100);
      n = n_dis(gen);
    }
    uniform_int_distribution<int> A_dis(-1000, 1000);
    vector<int> A;
    generate_n(back_inserter(A), n, [&] { return A_dis(gen); });
    /*
    for (size_t i = 0; i < A.size(); ++i) {
      cout << A[i] << " ";
    }
    cout << endl;
    */
    uniform_int_distribution<int> target_dis(-4000, 4000);
    int target = target_dis(gen);
    cout << "target = " << target << endl;
    int res;
    cout << (res = three_sum_closest(A, target)) << endl;
    assert(abs(res - target) == abs(target - check_ans(A, target)));
  }
  return 0;
}
