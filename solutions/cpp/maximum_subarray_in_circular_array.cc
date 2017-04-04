// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

int FindMaxSubarray(const vector<int>& A);
int FindCircularMaxSubarray(const vector<int>& A);

// @include
int MaxSubarraySumInCircular(const vector<int>& A) {
  return max(FindMaxSubarray(A), FindCircularMaxSubarray(A));
}

// Calculates the non-circular solution.
int FindMaxSubarray(const vector<int>& A) {
  int maximum_till = 0, maximum = 0;
  for (int a : A) {
    maximum_till = max(a, a + maximum_till);
    maximum = max(maximum, maximum_till);
  }
  return maximum;
}

vector<int> ComputeRunningMaximum(const vector<int>& A) {
  vector<int> running_maximum;
  int sum = A.front();
  running_maximum.emplace_back(sum);
  for (int i = 1; i < A.size(); ++i) {
    sum += A[i];
    running_maximum.emplace_back(max(running_maximum.back(), sum));
  }
  return running_maximum;
}

// Calculates the solution which is circular.
int FindCircularMaxSubarray(const vector<int>& A) {
  // Maximum subarray sum starts at index 0 and ends at or before index i.
  vector<int> maximum_begin = ComputeRunningMaximum(A);

  // Maximum subarray sum starts at index i + 1 and ends at the last element.
  vector<int> maximum_end = ComputeRunningMaximum({A.crbegin(), A.crend()});
  reverse(begin(maximum_end), end(maximum_end));

  // Calculates the maximum subarray which is circular.
  int circular_max = 0;
  for (int i = 0; i < A.size(); ++i) {
    circular_max = max(circular_max, maximum_begin[i] + maximum_end[i]);
  }
  return circular_max;
}
// @exclude

// O(n^2) solution.
int CheckAns(const vector<int>& A) {
  int ans = 0;
  for (int i = 0; i < A.size(); ++i) {
    int sum = 0;
    for (int j = 0; j < A.size(); ++j) {
      sum += A[(i + j) % A.size()];
      ans = max(ans, sum);
    }
  }
  cout << "correct answer = " << ans << endl;
  return ans;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    vector<int> A;
    if (argc > 2) {
      for (int i = 1; i < argc; ++i) {
        A.emplace_back(atoi(argv[i]));
      }
    } else {
      if (argc == 2) {
        n = atoi(argv[1]);
      } else {
        uniform_int_distribution<int> dis(1, 10000);
        n = dis(gen);
      }
      while (n--) {
        uniform_int_distribution<int> dis(-10000, 10000);
        A.emplace_back(dis(gen));
      }
    }
    int ans = MaxSubarraySumInCircular(A);
    /*
    for (size_t i = 0; i < A.size(); ++i) {
      cout << A[i] << ' ';
    }
    */
    cout << endl << "maximum sum = " << ans << endl;
    assert(ans == CheckAns(A));
  }
  return 0;
}
