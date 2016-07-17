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
int FindMaximumSubarray(const vector<int>& A) {
  int min_sum = 0, sum = 0, max_sum = 0;
  for (int i = 0; i < A.size(); ++i) {
    sum += A[i];
    if (sum < min_sum) {
      min_sum = sum;
    }
    if (sum - min_sum > max_sum) {
      max_sum = sum - min_sum;
    }
  }
  return max_sum;
}
// @exclude

template <typename T>
vector<T> RandVector(int len) {
  vector<T> ret;
  default_random_engine gen((random_device())());
  while (len--) {
    uniform_int_distribution<int> dis(-1000, 1000);
    ret.push_back(dis(gen));
  }
  return ret;
}

template <typename T>
void CheckMaxSum(const vector<T>& A, int max_sum) {
  for (int i = 0; i < A.size(); ++i) {
    T sum = 0;
    for (int j = i; j < A.size(); ++j) {
      sum += A[j];
      assert(sum <= max_sum);
    }
  }
}

void SmallTest() {
  vector<int> B = {1};
  int max_sum = FindMaximumSubarray(B);
  CheckMaxSum(B, max_sum);
  B = {-5};
  max_sum = FindMaximumSubarray(B);
  CheckMaxSum(B, max_sum);
  B = {0};
  max_sum = FindMaximumSubarray(B);
  CheckMaxSum(B, max_sum);
  B = {0, 0};
  max_sum = FindMaximumSubarray(B);
  CheckMaxSum(B, max_sum);
  B = {0, 0, 0};
  max_sum = FindMaximumSubarray(B);
  CheckMaxSum(B, max_sum);
  B = {0, -5, 0};
  max_sum = FindMaximumSubarray(B);
  CheckMaxSum(B, max_sum);
  B = {-2, -1};
  max_sum = FindMaximumSubarray(B);
  CheckMaxSum(B, max_sum);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    vector<int> A;
    if (argc == 1) {
      uniform_int_distribution<int> dis(1, 10000);
      A = RandVector<int>(dis(gen));
    } else if (argc == 2) {
      int n = atoi(argv[1]);
      A = RandVector<int>(n);
    } else {
      for (int i = 1; i < argc; ++i) {
        A.push_back(atoi(argv[i]));
      }
    }
    int max_sum = FindMaximumSubarray(A);
    CheckMaxSum(A, max_sum);
  }
  return 0;
}
