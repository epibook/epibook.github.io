// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <numeric>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::pair;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
// Returns a pair of (duplicate, missing).
pair<int, int> FindDuplicateMissing(const vector<int>& A) {
  // Compute the XOR of all numbers from 0 to |A| - 1 and all entries in A.
  int miss_XOR_dup = 0;
  for (int i = 0; i < A.size(); ++i) {
    miss_XOR_dup ^= i ^ A[i];
  }

  // We need to find a bit that's set to 1 in miss_XOR_dup. Such a bit
  // must exist if there is a single missing number and a single duplicated
  // number in A.
  //
  // The bit-fiddling assignment below sets all of bits in differ_bit to 0
  // except for the least significant bit in miss_XOR_dup that's 1.
  int differ_bit = miss_XOR_dup & (~(miss_XOR_dup - 1));
  int miss_or_dup = 0;
  for (int i = 0; i < A.size(); ++i) {
    // Focus on entries and numbers in which the differ_bit-th bit is 1.
    if (i & differ_bit) {
      miss_or_dup ^= i;
    }
    if (A[i] & differ_bit) {
      miss_or_dup ^= A[i];
    }
  }

  // miss_or_dup is either the missing value or the duplicated entry.
  for (int A_i : A) {
    if (A_i == miss_or_dup) {  // miss_or_dup is the duplicate.
      return {miss_or_dup, miss_or_dup ^ miss_XOR_dup};
    }
  }
  // miss_or_dup is the missing value.
  return {miss_or_dup ^ miss_XOR_dup, miss_or_dup};
}
// @exclude

void SmallTest() {
  vector<int> A = {0, 1, 2, 4, 5, 6, 6};
  auto ans = FindDuplicateMissing(A);
  cout << ans.first << " " << ans.second << endl;
  assert(ans.first == 6 && ans.second == 3);

  A = {0,0,1};
  ans = FindDuplicateMissing(A);
  assert(ans.first == 0 && ans.second == 2);
  
  A = {1,3,2,5,3,4};
  ans = FindDuplicateMissing(A);
  assert(ans.first == 3 && ans.second == 0);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> n_dis(2, 10000);
      n = n_dis(gen);
    }
    vector<int> A(n);
    iota(A.begin(), A.end(), 0);
    uniform_int_distribution<int> dis(0, n - 1);
    int missing_idx = dis(gen);
    int missing = A[missing_idx];
    int dup_idx = dis(gen);
    while (dup_idx == missing_idx) {
      dup_idx = dis(gen);
    }
    int dup = A[dup_idx];
    A[missing_idx] = dup;
    pair<int, int> ans = FindDuplicateMissing(A);
    cout << "times = " << times << endl;
    cout << dup << ' ' << missing << endl;
    cout << ans.first << ' ' << ans.second << endl;
    assert(ans.first == dup && ans.second == missing);
  }
  return 0;
}
