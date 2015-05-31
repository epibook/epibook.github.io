// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::nth_element;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

double Median(vector<int>* A);

// @include
class Comp {
 public:
  explicit Comp(double median) : median_(median){};

  bool operator()(int a, int b) const {
    return fabs(a - median_) < fabs(b - median_);
  }

 private:
  double median_;
};

vector<int> FindKClosestToMedian(vector<int> A, int k) {
  // Reorders A such that A[0 : k - 1] contains the k minimum values of
  // |A[i] - median| across all i.
  nth_element(A.begin(), A.begin() + k - 1, A.end(), Comp{Median(&A)});
  return {A.begin(), A.begin() + k};
}

// Computes the median of A.
double Median(vector<int>* A) {
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

void CheckAns(vector<int>& A, const vector<int>& res, int k) {
  sort(A.begin(), A.end());
  double median = (A.size() & 1)
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
}

void SimpleTest() {
  vector<int> D = {3, 2, 3, 5, 7, 3, 1};
  vector<int> Dexpres = {2, 3, 3};
  vector<int> Dres = FindKClosestToMedian(D, 3);
  CheckAns(D, Dres, 3);
  for (int d : Dres) {
    cout << d << ' ';
  }
  cout << endl;
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n, k;
    if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> k_dis(1, n);
      k = k_dis(gen);
    } else if (argc == 3) {
      n = atoi(argv[1]);
      k = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> n_dis(1, 100000);
      n = n_dis(gen);
      uniform_int_distribution<int> k_dis(1, n);
      k = k_dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> dis(0, (n * 2) - 1);
    for (int i = 0; i < n; ++i) {
      A.emplace_back(dis(gen));
    }
    /*
    for (int a : A) {
      cout << a << ' ';
    }
    cout << endl;
    */
    vector<int> res = FindKClosestToMedian(A, k);
    assert(res.size() == k);
    cout << "n = " << n << ", k = " << k << endl;
    /*
    for (int a : res) {
      cout << a << ' ';
    }
    cout << endl;
    */
    CheckAns(A, res, k);
  }
  return 0;
}
