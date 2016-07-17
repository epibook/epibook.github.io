// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <vector>

#include "./Permutation_array1.h"
#include "./Permutation_array2.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

void permute(vector<int> P, vector<int> &A) {
  for (int j = 0; j < int(P.size()); ++j) {
    for (int i = 0; i < int(P.size()); ++i) {
      swap(A[i], A[P[i]]);
      swap(P[i], P[P[i]]);
    }
  }
}

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  int n;
  for (int times = 0; times < 1000; ++times) {
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 100);
      n = dis(gen);
    }
    vector<int> A(n), perm(n);
    iota(A.begin(), A.end(), 0);
    iota(perm.begin(), perm.end(), 0);

    // Knuth shuffle
    random_shuffle(perm.begin(), perm.end());
    copy(perm.begin(), perm.end(), ostream_iterator<int>(cout, " "));
    cout << endl;

    vector<int> B(A);
    ApplyPermutation1::ApplyPermutation(&perm, &B);
    copy(B.begin(), B.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    vector<int> C(A);
    ApplyPermutation2::ApplyPermutation(perm, &C);
    copy(C.begin(), C.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    vector<int> D(A);
    permute(perm, D);
    copy(D.begin(), D.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    // Check answer by comparing the two permutations.
    assert(equal(B.begin(), B.end(), C.begin(), C.end()));
    assert(equal(B.begin(), B.end(), D.begin(), D.end()));
  }
  return 0;
}
