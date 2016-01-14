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
typedef enum { RED, WHITE, BLUE } Color;

void DutchFlagPartition(int pivot_index, vector<Color>* A_ptr) {
  vector<Color>& A = *A_ptr;
  Color pivot = A[pivot_index];
  // First pass: group elements smaller than pivot.
  for (int i = 0; i < A.size(); ++i) {
    // Look for a smaller element.
    for (int j = i + 1; j < A.size(); ++j) {
      if (A[j] < pivot) {
        swap(A[i], A[j]);
        break;
      }
    }
  }
  // Second pass: group elements larger than pivot.
  for (int i = A.size() - 1; i >= 0 && A[i] >= pivot; --i) {
    // Look for a larger element. Stop when we reach an element less
    // than pivot, since first pass has moved them to the start of A.
    for (int j = i - 1; j >= 0 && A[j] >= pivot; --j) {
      if (A[j] > pivot) {
        swap(A[i], A[j]);
        break;
      }
    }
  }
}
// @exclude

vector<Color> RandVector(int len) {
  default_random_engine gen((random_device())());
  vector<Color> ret;
  while (len--) {
    uniform_int_distribution<int> dis(0, 2);
    ret.push_back(static_cast<Color>(dis(gen)));
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
    vector<Color> A(RandVector(n));
    uniform_int_distribution<int> dis(0, A.size() - 1);
    int pivot_index = dis(gen);
    Color pivot = A[pivot_index];
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
