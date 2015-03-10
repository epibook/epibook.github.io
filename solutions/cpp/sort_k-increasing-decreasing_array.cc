// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
  vector<vector<int>> sorted_subarrays;
  typedef enum {INCREASING, DECREASING} SubarrayType;
  SubarrayType subarray_type = INCREASING;
  int start_idx = 0;
  for (int i = 1; i <= A.size(); ++i) {
    if (i == A.size() ||  // A is ended. Adds the last subarray.
        (A[i - 1] < A[i] && subarray_type == DECREASING) ||
        (A[i - 1] >= A[i] && subarray_type == INCREASING)) {
      if (subarray_type == INCREASING) {
        sorted_subarrays.emplace_back(A.cbegin() + start_idx, A.cbegin() + i);
      } else {
        sorted_subarrays.emplace_back(A.crbegin() + A.size() - i,
                                      A.crbegin() + A.size() - start_idx);
      }
      start_idx = i;
      subarray_type = (subarray_type == INCREASING ? DECREASING : INCREASING);
    }
  }

  return MergeSortedArrays(sorted_subarrays);
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
    uniform_int_distribution<int> dis(-999999, 999999);
    generate_n(back_inserter(A), n, [&] { return dis(gen); } );
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
