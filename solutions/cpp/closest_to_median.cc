// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <iostream>
#include <limits>
#include <random>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::nth_element;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

double FindMedian(vector<int>*);

// @include
class Comp {
 public:
  explicit Comp(double median) : median_(median){};

  bool operator()(int a, int b) const {
    return fabs(a - median_) < fabs(b - median_);
  }

 private:
  const double median_;
};

vector<int> FindKClosestToMedian(vector<int> A, int k) {
  nth_element(A.begin(), A.begin() + k - 1, A.end(), Comp{FindMedian(&A)});
  // Since nth_element reordered A so that elements closest in absolute value
  // to median have been moved to the front of A, the first k entries are the
  // result.
  return {A.begin(), A.begin() + k};
}

// Promote the return value to double to prevent precision error.
double FindMedian(vector<int>* A) {
  auto target = A->begin() + A->size() / 2;
  nth_element(A->begin(), target, A->end());
  if (A->size() % 2 == 1) {  // A has odd number of elements.
    return *target;
  } else {  // A has even number of elements.
    int x = *target;
    nth_element(A->begin(), target - 1, A->end());
    return (x + *(target - 1)) / 2.0;
  }
}
// @exclude

void CheckAns(vector<int> A, const vector<int>& res, int k) {
  sort(A.begin(), A.end());
  double median = (A.size() % 2)
                      ? A[A.size() / 2]
                      : 0.5 * (A[(A.size() / 2) - 1] + A[A.size() / 2]);
  vector<double> temp;
  for (int a : A) {
    temp.emplace_back(fabs(median - a));
  }
  sort(temp.begin(), temp.end());
  for (int r : res) {
    assert(fabs(r - median) <= temp[k - 1]);
  }

  unordered_set<int> A_set(A.begin(), A.end());
  for (int a : res) {
    assert(A_set.count(a) > 0);
  }
}

void SimpleTest() {
  vector<int> D = {3, 2, 3, 5, 7, 3, 1};
  vector<int> Dres = FindKClosestToMedian(D, 3);
  CheckAns(D, Dres, 3);
  D = {0, 9, 2, 9, 8};
  Dres = FindKClosestToMedian(D, 2);
  CheckAns(D, Dres, 2);
}

void Test(const vector<int>& A, int k) {
  vector<int> res = FindKClosestToMedian(A, k);
  assert(res.size() == k);
  CheckAns(A, res, k);
}

void TestMultipleK(const vector<int>& A, const vector<int>& order) {
  for (int k : order) {
    Test(A, k);
  }
}

void RandomTestFixedN(int N) {
  vector<int> order;
  for (int i = 0; i < 5; ++i) {
    order.emplace_back(min(N, i + 1));
  }
  order.emplace_back(min(N, 7));
  order.emplace_back(min(N, 9));
  order.emplace_back(min(N, 12));
  order.emplace_back(min(N, max(N / 2 - 1, 1)));
  order.emplace_back(min(N, max(N / 2, 1)));
  order.emplace_back(min(N, N / 2 + 1));
  order.emplace_back(min(1, N / 2 + 1));
  order.emplace_back(max(1, N - 1));
  order.emplace_back(N);

  vector<int> A(N);
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis1(0, 9999999);
  for (int i = 0; i < N; ++i) {
    A[i] = dis1(gen);
  }
  TestMultipleK(A, order);

  uniform_int_distribution<int> dis2(0, N - 1);
  for (int i = 0; i < N; ++i) {
    A[i] = dis2(gen);
  }
  TestMultipleK(A, order);

  uniform_int_distribution<int> dis3(0, 2 * N - 1);
  for (int i = 0; i < N; ++i) {
    A[i] = dis3(gen);
  }
  TestMultipleK(A, order);

  uniform_int_distribution<int> dis4(0, max(N / 2, 1));
  for (int i = 0; i < N; ++i) {
    A[i] = dis4(gen);
  }
  TestMultipleK(A, order);
}

void ComplexRandomTest() {
  vector<int> N = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 50, 100};
  for (int i = 0; i < N.size(); ++i) {
    for (int j = 0; j < 100; ++j) {
      RandomTestFixedN(N[i]);
    }
  }
}

int main(int argc, char* argv[]) {
  SimpleTest();
  ComplexRandomTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    int n, k;
    if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> k_dis(1, n);
      k = k_dis(gen);
    } else if (argc == 3) {
      n = atoi(argv[1]);
      k = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> n_dis(1, 10000);
      n = n_dis(gen);
      uniform_int_distribution<int> k_dis(1, n);
      k = k_dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> dis(0, (n * 2) - 1);
    for (int i = 0; i < n; ++i) {
      A.emplace_back(dis(gen));
    }
    vector<int> res = FindKClosestToMedian(A, k);
    assert(res.size() == k);
    cout << "n = " << n << ", k = " << k << endl;
    CheckAns(A, res, k);
  }
  return 0;
}
