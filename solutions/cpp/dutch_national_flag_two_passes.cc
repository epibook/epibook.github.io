// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

// @include
void DutchFlagPartition(int pivot_index, vector<int>* A_ptr) {
  vector<int>& A = *A_ptr;
  int pivot = A[pivot_index];
  // First pass: group elements smaller than pivot.
  int smaller = 0;
  for (int i = 0; i < A.size(); ++i) {
    if (A[i] < pivot) {
      swap(A[i], A[smaller++]);
    }
  }
  // Second pass: group elements larger than pivot.
  int larger = A.size() - 1;
  for (int i = A.size() - 1; i >= 0 && A[i] >= pivot; --i) {
    if (A[i] > pivot) {
      swap(A[i], A[larger--]);
    }
  }
}
// @exclude

vector<int> RandVector(int len) {
  default_random_engine gen((random_device())());
  vector<int> ret;
  while (len--) {
    uniform_int_distribution<int> dis(0, 2);
    ret.emplace_back(dis(gen));
  }
  return ret;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 100);
      n = dis(gen);
    }
    vector<int> A(RandVector(n));
    uniform_int_distribution<int> dis(0, A.size() - 1);
    int pivot_index = dis(gen);
    int pivot = A[pivot_index];
    DutchFlagPartition(pivot_index, &A);
    int i = 0;
    while (i < A.size() && A[i] < pivot) {
      cout << A[i] << ' ';
      ++i;
    }
    while (i < A.size() && A[i] == pivot) {
      cout << A[i] << ' ';
      ++i;
    }
    while (i < A.size() && A[i] > pivot) {
      cout << A[i] << ' ';
      ++i;
    }
    cout << endl;
    assert(i == A.size());
  }
  return 0;
}
