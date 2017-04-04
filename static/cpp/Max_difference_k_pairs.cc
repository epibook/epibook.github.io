// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::uniform_real_distribution;
using std::vector;

// @include
double MaxKPairsProfits(const vector<double> &A, int k) {
  vector<double> k_sum(2 * k, numeric_limits<double>::lowest());
  for (int i = 0; i < A.size(); ++i) {
    vector<double> pre_k_sum(k_sum);
    for (int j = 0, sign = -1; j < k_sum.size() && j <= i; ++j, sign *= -1) {
      double diff = sign * A[i] + (j == 0 ? 0 : pre_k_sum[j - 1]);
      k_sum[j] = max(diff, pre_k_sum[j]);
    }
  }
  return k_sum.back();  // Returns the last selling profits as the answer.
}
// @exclude

// O(n^k) checking answer.
void CheckAnsHelper(const vector<double> &A, int l, int k, int pre,
                    double ans, double *max_ans) {
  if (l == k) {
    *max_ans = max(*max_ans, ans);
  } else {
    for (int i = pre; i < A.size(); ++i) {
      CheckAnsHelper(A, l + 1, k, i + 1, ans + ((l & 1) ? A[i] : -A[i]),
                     max_ans);
    }
  }
}

double CheckAns(const vector<double> &A, int k) {
  double ans = 0, max_ans = numeric_limits<double>::lowest();

  CheckAnsHelper(A, 0, 2 * k, 0, ans, &max_ans);
  cout << "max_ans = " << max_ans << endl;
  return max_ans;
}

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  int n = 30, k = 4;
  // random tests for n = 30, k = 4 for 100 times/
  for (int times = 0; times < 100; ++times) {
    vector<double> A;
    uniform_real_distribution<double> dis(0, 99);
    for (int i = 0; i < n; ++i) {
      A.emplace_back(dis(gen));
    }
    cout << "n = " << n << ", k = " << k << endl;
    cout << MaxKPairsProfits(A, k) << endl;
    assert(CheckAns(A, k) == MaxKPairsProfits(A, k));
  }

  if (argc == 2) {
    n = atoi(argv[1]);
    uniform_int_distribution<int> dis(1, n / 2);
    k = dis(gen);
  } else if (argc == 3) {
    n = atoi(argv[1]);
    k = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> n_dis(1, 60);
    n = n_dis(gen);
    uniform_int_distribution<int> k_dis(1, n / 10);
    k = (k_dis(gen));
  }
  vector<double> A;
  uniform_real_distribution<double> dis(0, 99);
  for (int i = 0; i < n; ++i) {
    A.emplace_back(dis(gen));
  }
  cout << "n = " << n << ", k = " << k << endl;
  cout << CheckAns(A, k) << ", " << MaxKPairsProfits(A, k) << endl;
  assert(CheckAns(A, k) == MaxKPairsProfits(A, k));
  return 0;
}
