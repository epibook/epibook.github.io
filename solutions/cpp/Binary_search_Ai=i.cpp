// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
int SearchEntryEqualToItsIndex(const vector<int>& A) {
  int left = 0, right = A.size() - 1;
  while (left <= right) {
    int mid = left + ((right - left) / 2);
    int difference = A[mid] - mid;
    // A[mid] == mid iff difference equals to 0.
    if (difference == 0) {
      return mid;
    } else if (difference > 0) {
      right = mid - 1;
    } else {  // difference < 0.
      left = mid + 1;
    }
  }
  return -1;
}
// @exclude

// O(n) way to find ans.
int CheckAns(const vector<int>& A) {
  for (size_t i = 0; i < A.size(); ++i) {
    if (A[i] == static_cast<int>(i)) {
      return static_cast<int>(i);
    }
  }
  return -1;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    vector<int> A;
    unordered_set<int> table;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 1000);
      n = dis(gen);
    }
    for (int i = 0; i < n; ++i) {
      int x;
      unordered_set<int>::iterator iter;
      do {
        uniform_int_distribution<int> pos_or_neg(0, 1);
        uniform_int_distribution<int> dis(0, 999);
        x = ((pos_or_neg(gen) & 1) ? -1 : 1) * dis(gen);
        iter = table.find(x);
      } while (iter != table.cend());
      table.emplace_hint(iter, x);
      A.emplace_back(x);
    }
    sort(A.begin(), A.end());
    int ans = SearchEntryEqualToItsIndex(A);
    if (ans != -1) {
      cout << "A[" << ans << "] = " << A[ans] << endl;
      assert(ans == A[ans]);
    } else {
      cout << "no entry where A[k] = k" << endl;
      assert(ans == CheckAns(A));
    }
  }
  return 0;
}
