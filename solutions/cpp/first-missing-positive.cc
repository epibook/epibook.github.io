// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::ostream_iterator;
using std::random_device;
using std::stoul;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

int CheckAns(vector<int> A) {
  sort(A.begin(), A.end());
  int target = 1;
  for (int a : A) {
    if (a > 0) {
      if (a == target) {
        ++target;
      } else if (a > target) {
        return target;
      }
    }
  }
  return target;
}

// @include
// A is passed by value argument, since we change it.
int FindFirstMissingPositive(vector<int> A) {
  // Record which values are present by writing A[i] to index A[i] - 1 if A[i]
  // is between 1 and A.size(), inclusive. We save the value at index
  // A[i] - 1 by swapping it with the entry at i. If A[i] is negative or
  // greater than n, we just advance i.
  size_t i = 0;
  while (i < A.size()) {
    if (A[i] > 0 && A[i] <= A.size() && A[A[i] - 1] != A[i]) {
      swap(A[i], A[A[i] - 1]);
    } else {
      ++i;
    }
  }

  // Second pass through A to search for the first index i such that
  // A[i] != i+1, indicating that i + 1 is absent. If all numbers between 1
  // and A.size() are present, the smallest missing positive is A.size() + 1.
  for (size_t i = 0; i < A.size(); ++i) {
    if (A[i] != i + 1) {
      return i + 1;
    }
  }
  return A.size() + 1;
}
// @exclude

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    size_t n;
    if (argc == 2) {
      n = stoul(argv[1]);
    } else {
      uniform_int_distribution<size_t> dis(0, 500000);
      n = dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> dis_n(0, static_cast<int>(n));
    generate_n(back_inserter(A), n, [&] { return dis_n(gen); });
    cout << "n = " << n << endl;
    /*
    copy(A.cbegin(), A.cend(), ostream_iterator<int>(cout, " "));
    cout << endl;
    cout << FindFirstMissingPositive(A) << " " << CheckAns(A) << endl;
    */
    assert(FindFirstMissingPositive(A) == CheckAns(A));
  }
  return 0;
}
