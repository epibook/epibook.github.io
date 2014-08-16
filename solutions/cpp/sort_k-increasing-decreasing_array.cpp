// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./Merge_sorted_arrays.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> SortKIncreasingDecreasingArray(const vector<int>& A) {
  // Decomposes A into a set of sorted arrays.
  vector<vector<int>> S;
  bool is_increasing = true;  // The trend we are looking for.
  int start_idx = 0;
  for (int i = 1; i < A.size(); ++i) {
    if ((A[i - 1] < A[i] && !is_increasing) ||
        (A[i - 1] >= A[i] && is_increasing)) {
      if (is_increasing) {
        S.emplace_back(A.cbegin() + start_idx, A.cbegin() + i);
      } else {
        S.emplace_back(A.crbegin() + A.size() - i,
                       A.crbegin() + A.size() - start_idx);
      }
      start_idx = i;
      is_increasing = !is_increasing;  // Inverses the trend.
    }
  }
  if (start_idx < A.size()) {
    if (is_increasing) {
      S.emplace_back(A.cbegin() + start_idx, A.cend());
    } else {
      S.emplace_back(A.crbegin(), A.crbegin() + A.size() - start_idx);
    }
  }

  return MergeArrays(S);
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<int> A;
    cout << "n = " << n << endl;
    uniform_int_distribution<int> pos_or_neg(0, 1);
    uniform_int_distribution<int> dis(0, 999999);
    for (size_t i = 0; i < n; ++i) {
      A.emplace_back(((pos_or_neg(gen)) ? 1 : -1) * dis(gen));
    }
    vector<int> ans = SortKIncreasingDecreasingArray(A);
    /*
    copy(A.begin(), A.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    */
    assert(ans.size() == A.size());
    assert(is_sorted(ans.cbegin(), ans.cend()));
  }
  return 0;
}
