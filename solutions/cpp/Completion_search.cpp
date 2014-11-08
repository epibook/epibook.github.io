// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
double CompletionSearch(double budget, vector<double>* A) {
  sort(A->begin(), A->end());
  // Calculates the prefix sum for A.
  vector<double> prefix_sum;
  partial_sum(A->cbegin(), A->cend(), back_inserter(prefix_sum));
  // costs[i] represents the total payroll if the cap is A[i].
  vector<double> costs;
  for (int i = 0; i < prefix_sum.size(); ++i) {
    costs.emplace_back(prefix_sum[i] + (A->size() - i - 1) * (*A)[i]);
  }

  auto lower = lower_bound(costs.cbegin(), costs.cend(), budget);
  if (lower == costs.cend()) {
    return -1.0;  // No solution since budget is too large.
  }

  if (lower == costs.cbegin()) {
    return budget / A->size();
  }
  auto idx = distance(costs.cbegin(), lower);
  return (budget - prefix_sum[idx - 1]) / (A->size() - idx);
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    int n;
    vector<double> A;
    double tar;
    if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> dis(0, 99999);
      tar = dis(gen);
    } else if (argc == 3) {
      n = atoi(argv[1]), tar = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> n_dis(1, 1000);
      n = n_dis(gen);
      uniform_int_distribution<int> tar_dis(0, 99999);
      tar = tar_dis(gen);
    }
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(0, 9999);
      A.emplace_back(dis(gen));
    }
    cout << "A = ";
    copy(A.begin(), A.end(), ostream_iterator<double>(cout, " "));
    cout << endl;
    cout << "tar = " << tar << endl;
    double ret = CompletionSearch(tar, &A);
    if (ret != -1.0) {
      cout << "ret = " << ret << endl;
      double sum = 0.0;
      for (int i = 0; i < n; ++i) {
        if (A[i] > ret) {
          sum += ret;
        } else {
          sum += A[i];
        }
      }
      tar -= sum;
      cout << "sum = " << sum << endl;
      assert(tar < 1.0e-8);
    }
  }
  return 0;
}
