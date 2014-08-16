// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <functional>
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
int FindKthLargestUnknownLength(istringstream* sin, int k) {
  vector<int> M;
  int x;
  while (*sin >> x) {
    M.emplace_back(x);
    if (M.size() == (k * 2) - 1) {
      // Keeps the k largest elements and discard the smaller ones.
      nth_element(M.begin(), M.begin() + k - 1, M.end(), greater<int>());
      M.resize(k);
    }
  }
  nth_element(M.begin(), M.begin() + k - 1, M.end(), greater<int>());
  return M[k - 1];  // return the k-th largest one.
}
// @exclude

int main(int argc, char* argv[]) {
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
    for (const int& a : A) {
      ss << a << ' ';
    }
    /*
    cout << "n = " << n << ", k = " << k << endl;
    cout << ss.str() << endl;
    */
    istringstream sin(ss.str());
    int result = FindKthLargestUnknownLength(&sin, k);
    nth_element(A.begin(), A.begin() + A.size() - k, A.end());
    cout << result << endl << A[A.size() - k] << endl;
    assert(result == A[A.size() - k]);
  }
  return 0;
}
