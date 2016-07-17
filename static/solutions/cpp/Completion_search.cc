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
double FindSalaryCap(double target_payroll, vector<double> current_salaries) {
  sort(current_salaries.begin(), current_salaries.end());
  double unadjusted_salary_sum = 0.0;
  for (int i = 0; i < current_salaries.size(); ++i) {
    const double adjusted_salary_sum =
        current_salaries[i] * (current_salaries.size() - i);
    if (unadjusted_salary_sum + adjusted_salary_sum >= target_payroll) {
      return (target_payroll - unadjusted_salary_sum) /
             (current_salaries.size() - i);
    }
    unadjusted_salary_sum += current_salaries[i];
  }
  // No solution, since target_payroll > existing payroll.
  return -1.0;
}
// @exclude

void SmallTest() {
  vector<double> A = {20, 30, 40, 90, 100};
  double T = 210;
  assert(FindSalaryCap(T, A) == 60);
  T = 280;
  assert(FindSalaryCap(T, A) == 100);
  T = 50;
  assert(FindSalaryCap(T, A) == 10);
  T = 281;
  assert(FindSalaryCap(T, A) == -1.0);
}

int main(int argc, char* argv[]) {
  SmallTest();
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
    double ret = FindSalaryCap(tar, A);
    sort(A.begin(), A.end());
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
