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
  /**
   * Keep the following invariants during partitioning:
   * bottom group: A[0 : smaller - 1].
   * middle group: A[smaller : equal - 1].
   * unclassified group: A[equal : larger - 1].
   * top group: A[larger : A.size() - 1].
   */
  int smaller = 0, equal = 0, larger = A.size();
  // Keep iterating as long as there is an unclassified element.
  while (equal < larger) {
    // A[equal] is the incoming unclassified element.
    if (A[equal] < pivot) {
      swap(A[smaller++], A[equal++]);
    } else if (A[equal] == pivot) {
      ++equal;
    } else {  // A[equal] > pivot.
      swap(A[equal], A[--larger]);
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
