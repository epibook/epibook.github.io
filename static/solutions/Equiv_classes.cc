// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <numeric>
#include <vector>

using std::copy;
using std::cout;
using std::iota;
using std::ostream_iterator;
using std::vector;

int backtrace(const vector<int> &F, int idx);

// @include
/*
 * A and B encode pairwise equivalences on a cardinality N set whose elements
 * are indexed by 0, 1, 2, ..., N-1.
 *
 * For example A[i] = 6 and B[i] = 0 indicates that the 6 and 0 are to be
 * grouped into the same equivalence class.
 *
 * We return the weakest equivalence relation implied by A and B in an array
 * F of length N; F[i] holds the smallest index of all the elements that
 * i is equivalent to.
 */
vector<int> compute_equival_classes(int n, const vector<int> &A,
                                    const vector<int> &B) {
  // Each element maps to itself.
  vector<int> F(n);
  iota(F.begin(), F.end(), 0);

  for (int i = 0; i < A.size(); ++i) {
    int a = backtrace(F, A[i]), b = backtrace(F, B[i]);
    a < b ? F[b] = a : F[a] = b;
  }

  // Generates the weakest equivalence relation.
  for (int &f : F) {
    while (f != F[f]) {
      f = F[f];
    }
  }
  return F;
}

int backtrace(const vector<int> &F, int idx) {
  while (F[idx] != idx) {
    idx = F[idx];
  }
  return idx;
}
// @exclude

int main(int argc, char *argv[]) {
  vector<int> A{1, 5, 3, 6};
  vector<int> B{2, 1, 0, 5};
  vector<int> res = compute_equival_classes(7, A, B);
  copy(res.begin(), res.end(), ostream_iterator<int>(cout, " "));
  assert(res[0] == 0);
  assert(res[1] == 1);
  assert(res[2] == 1);
  assert(res[3] == 0);
  assert(res[4] == 4);
  assert(res[5] == 1);
  assert(res[6] == 1);
  return 0;
}
