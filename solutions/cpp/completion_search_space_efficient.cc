// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <iterator>
#include <limits>
#include <numeric>
#include <vector>

using namespace std;

// @include
double CompletionSearch(vector<double> &A, double budget) {
  sort(A.begin(), A.end());
  if (budget / A.size() < A.front()) {
    return budget / A.size();
  }

  double reminder = budget;
  for (int i = 0; i < A.size() - 1; ++i) {
    reminder -= A[i];
    double cutoff = reminder / (A.size() - i - 1);
    if (cutoff <= A[i + 1]) {
      return cutoff;
    }
  }
  return -1.0;
}
// @exclude

double CheckAnswer(vector<double> &A, double budget) {
  sort(A.begin(), A.end());
  // Calculate the prefix sum for A
  vector<double> prefix_sum;
  partial_sum(A.cbegin(), A.cend(), back_inserter(prefix_sum));
  // costs[i] represents the total payroll if the cap is A[i]
  vector<double> costs;
  for (int i = 0; i < prefix_sum.size(); ++i) {
    costs.emplace_back(prefix_sum[i] + (A.size() - i - 1) * A[i]);
  }

  auto lower = lower_bound(costs.cbegin(), costs.cend(), budget);
  if (lower == costs.cend()) {
    return -1.0;  // no solution since budget is too large
  }

  if (lower == costs.cbegin()) {
    return budget / A.size();
  }
  auto idx = distance(costs.cbegin(), lower) - 1;
  return A[idx] + (budget - costs[idx]) / (A.size() - idx - 1);
}

int main(int argc, char *argv[]) {
  for (int times = 0; times < 10000; ++times) {
    int n;
    vector<double> A;
    double tar;
    if (argc == 2) {
      n = atoi(argv[1]);
      tar = rand() % 100000;
    } else if (argc == 3) {
      n = atoi(argv[1]), tar = atoi(argv[2]);
    } else {
      n = 1 + rand() % 1000;
      tar = rand() % 100000;
    }
    for (int i = 0; i < n; ++i) {
      A.emplace_back(rand() % 10000);
    }
    /*
    cout << "A = ";
    copy(A.begin(), A.end(), ostream_iterator<double>(cout, " "));
    cout << endl;
    cout << "tar = " << tar << endl;
    //*/
    double ret = CompletionSearch(A, tar);
    double ret2 = CheckAnswer(A, tar);
    cout << ret << " " << ret2 << endl;
    assert(fabs(ret - ret2) <= 1.0e-10);
    if (ret != -1) {
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
