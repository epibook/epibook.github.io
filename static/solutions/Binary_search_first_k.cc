// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <limits>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int SearchFirstOfK(const vector<int>& A, int k) {
  int left = 0, right = A.size() - 1, result = -1;
  // [left : right] is the candidate set.
  while (left <= right) {
    int mid = left + ((right - left) / 2);
    if (A[mid] > k) {
      right = mid - 1;
    } else if (A[mid] == k) {
      result = mid;
      // Nothing to the right of mid can be the first occurrence of k.
      right = mid - 1;
    } else {  // A[mid] < k.
      left = mid + 1;
    }
  }
  return result;
}
// @exclude

static void SimpleTest() {
  vector<int> A = {0, 1, 2, 3, 4, 5, 6, 7};
  int k = 4;
  assert(0 == SearchFirstOfK(A, 0));
  assert(1 == SearchFirstOfK(A, 1));
  assert(4 == SearchFirstOfK(A, 4));
  assert(6 == SearchFirstOfK(A, 6));
  assert(7 == SearchFirstOfK(A, 7));
  assert(-1 == SearchFirstOfK(A, 8));
  assert(-1 == SearchFirstOfK(A, numeric_limits<int>::min()));
  A[0] = 1;
  assert(0 == SearchFirstOfK(A, 1));
  A[5] = 4;
  A[6] = 4;
  assert(4 == SearchFirstOfK(A, 4));
  A = {1, 1, 1, 1, 1, 2};
  assert(-1 == SearchFirstOfK(A, 0));
  assert(0 == SearchFirstOfK(A, 1));
  assert(5 == SearchFirstOfK(A, 2));
  A[4] = 2;
  assert(4 == SearchFirstOfK(A, 2));
}

int SearchFirstOfKAlternative(const vector<int>& A, int k) {
  auto result = lower_bound(A.begin(), A.end(), k);
  if (*result == k) {
    return distance(A.begin(), result);
  }
  return -1;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 100000);
      n = dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> k_dis(0, n - 1);
    int k = k_dis(gen);
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(0, n - 1);
      A.emplace_back(dis(gen));
    }
    sort(A.begin(), A.end());
    int ans = SearchFirstOfK(A, k);
    assert(SearchFirstOfKAlternative(A, k) == ans);
    cout << "k = " << k << " locates at " << ans << endl;
    if (ans != -1) {
      cout << "A[k] = " << A[ans] << endl;
    }
    auto it = find(A.cbegin(), A.cend(), k);
    assert((it == A.cend() && ans == -1) ||
           (distance(A.cbegin(), it) == ans));
  }
  return 0;
}
