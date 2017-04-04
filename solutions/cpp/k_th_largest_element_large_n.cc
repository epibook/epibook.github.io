// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <functional>
#include <iostream>
#include <random>
#include <sstream>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::greater;
using std::istringstream;
using std::random_device;
using std::stringstream;
using std::uniform_int_distribution;
using std::vector;

// @include
int FindKthLargestUnknownLength(istringstream* stream, int k) {
  vector<int> candidates;
  int x;
  while (*stream >> x) {
    candidates.emplace_back(x);
    if (candidates.size() == 2 * k - 1) {
      // Reorders elements about median with larger elements appearing before
      // the median.
      nth_element(candidates.begin(), candidates.begin() + k - 1,
                  candidates.end(), greater<int>());
      // Resize to keep just the k largest elements seen so far.
      candidates.resize(k);
    }
  }
  // Finds the k-th largest element in candidates.
  nth_element(candidates.begin(), candidates.begin() + k - 1,
              candidates.end(), greater<int>());
  return candidates[k - 1];
}
// @exclude

static void SimpleTestArray(vector<int> A) {
  stringstream ss;
  for (int a : A) {
    ss << a << ' ';
  }
  for (int i = 0; i < A.size(); ++i) {
    cout << "i = " << i << endl;
    istringstream stream(ss.str());
    int k = i + 1;
    int result = FindKthLargestUnknownLength(&stream, k);
    nth_element(A.begin(), A.begin() + A.size() - k, A.end());
    assert(result == A[A.size() - k]);
  }
}

static void SimpleTest() {
  vector<int> A = {5, 6, 2, 1, 3, 0, 4};
  SimpleTestArray(A);
  A = {5, -1, 2, 1, 3, 1, 4, 2 << 31 - 1, 5};
  SimpleTestArray(A);
  default_random_engine gen((random_device())());
  int N = 1000;
  A = {};
  for (int i = 0; i < N; ++i) {
    uniform_int_distribution<int> dis(0, 10 - 1);
    A.emplace_back(dis(gen));
  }
  SimpleTestArray(A);
  A = {};
  for (int i = 0; i < N; ++i) {
    uniform_int_distribution<int> dis(0, 100000000 - 1);
    A.emplace_back(dis(gen));
  }
  SimpleTestArray(A);
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
      n = atoi(argv[1]), k = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> n_dis(1, 100000);
      n = n_dis(gen);
      uniform_int_distribution<int> k_dis(1, n);
      k = k_dis(gen);
    }
    vector<int> A;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(0, 9999999);
      A.emplace_back(dis(gen));
    }
    stringstream ss;
    for (int a : A) {
      ss << a << ' ';
    }
    istringstream stream(ss.str());
    int result = FindKthLargestUnknownLength(&stream, k);
    nth_element(A.begin(), A.begin() + A.size() - k, A.end());
    cout << result << endl << A[A.size() - k] << endl;
    assert(result == A[A.size() - k]);
  }
  return 0;
}
