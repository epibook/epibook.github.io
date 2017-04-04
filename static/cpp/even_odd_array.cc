// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <set>
#include <vector>

using std::default_random_engine;
using std::multiset;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

// @include
void EvenOdd(vector<int>* A_ptr) {
  vector<int>& A = *A_ptr;
  int next_even = 0, next_odd = A.size() - 1;
  while (next_even < next_odd) {
    if (A[next_even] % 2 == 0) {
      ++next_even;
    } else {
      swap(A[next_even], A[next_odd--]);
    }
  }
}
// @exclude

/*
vector<int> EvenOddStable(vector<int> A) {
  int first_odd = -1;
  for (int i = 0; i < A.size(); ++i) {
    if (A[i] % 2 == 1) {
      if (first_odd == -1) {
        first_odd = i;
      }
    } else if (first_odd != -1) {
      swap(A[first_odd++], A[i]);
    }
  }
  return A;
}
*/

void Test(vector<int> A) {
  multiset<int> even, odd;
  for (int a : A) {
    if (a % 2 == 0) {
      even.emplace(a);
    } else {
      odd.emplace(a);
    }
  }
  EvenOdd(&A);
  bool in_odd = false;
  for (int a : A) {
    if (a % 2 == 0) {
      even.erase(a);
      assert(!in_odd);
    } else {
      odd.erase(a);
      in_odd = true;
    }
  }
  assert(even.empty() && odd.empty());
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    vector<int> A;
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 20);
      n = dis(gen);
    }
    uniform_int_distribution<int> dis(0, 20);
    for (int i = 0; i < n; ++i) {
      A.emplace_back(dis(gen));
    }
    Test(A);
  }
  return 0;
}
