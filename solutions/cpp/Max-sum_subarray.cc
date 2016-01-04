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
// Used to represent subarry consisting of elements from index start
// (inclusive) to index end (exclusive)
struct Subarray {
  int start = 0, end = 0;
};

Subarray FindMaximumSubarray(const vector<int>& A) {
  // A[range.start : range.end - 1] will be the maximum subarray.
  Subarray range;
  int min_idx = -1, min_sum = 0, sum = 0, max_sum = 0;
  for (int i = 0; i < A.size(); ++i) {
    sum += A[i];
    if (sum < min_sum) {
      min_sum = sum, min_idx = i;
    }
    if (sum - min_sum > max_sum) {
      max_sum = sum - min_sum, range = {min_idx + 1, i + 1};
    }
  }
  return range;
}
// @exclude

/*
Subarray FindMaximumSubarray(vector<int> &A) {
  int maximum_till = A[0], maximum = A[0];
  Subarray range_max_till(0, 0), range_max(0, 0);
  for (int i = 1; i < A.size(); ++i) {
    if (A[i] > A[i] + maximum_till) {
      maximum_till = A[i], range_max_till = Subarray(i, i);
    } else {
      maximum_till = A[i] + maximum_till, range_max_till.end = i;
    }
    if (maximum_till > maximum) {
      maximum = maximum_till, range_max = range_max_till;
    }
  }
  return range_max;
}
*/

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
void CheckMaxSum(const vector<T>& A, const Subarray& range) {
  T max_sum = 0;
  for (int i = range.start; i < range.end; ++i) {
    max_sum += A[i];
  }
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
  Subarray range = FindMaximumSubarray(B);
  cout << range.start << " " << range.end << endl;
  CheckMaxSum(B, range);
  B = {-5};
  range = FindMaximumSubarray(B);
  cout << range.start << " " << range.end << endl;
  CheckMaxSum(B, range);
  B = {0};
  range = FindMaximumSubarray(B);
  cout << range.start << " " << range.end << endl;
  CheckMaxSum(B, range);
  B = {0, 0};
  range = FindMaximumSubarray(B);
  cout << range.start << " " << range.end << endl;
  CheckMaxSum(B, range);
  B = {0, 0, 0};
  range = FindMaximumSubarray(B);
  cout << range.start << " " << range.end << endl;
  CheckMaxSum(B, range);
  B = {0, -5, 0};
  range = FindMaximumSubarray(B);
  cout << range.start << " " << range.end << endl;
  CheckMaxSum(B, range);
  B = {-2, -1};
  range = FindMaximumSubarray(B);
  cout << range.start << " " << range.end << endl;
  CheckMaxSum(B, range);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
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
    Subarray range = FindMaximumSubarray(A);
    cout << range.start << " " << range.end << endl;
    CheckMaxSum(A, range);
  }
  return 0;
}
