// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <functional>
#include <iostream>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::greater_equal;
using std::less;
using std::pair;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

struct IndexPair;
template <typename Compare>
IndexPair FindPairUsingCompare(const vector<int>&, int, Compare);
IndexPair FindPositiveNegativePair(const vector<int>&, int);

// @include
struct IndexPair {
  int index_1, index_2;
};

IndexPair FindPairSumK(const vector<int>& A, int k) {
  IndexPair result = FindPositiveNegativePair(A, k);
  if (result.index_1 == -1 && result.index_2 == -1) {
    return k >= 0 ? FindPairUsingCompare(A, k, less<int>())
                  : FindPairUsingCompare(A, k, greater_equal<int>());
  }
  return result;
}

template <typename Compare>
IndexPair FindPairUsingCompare(const vector<int>& A, int k, Compare comp) {
  IndexPair result = IndexPair{0, static_cast<int>(A.size() - 1)};
  while (result.index_1 < result.index_2 && comp(A[result.index_1], 0)) {
    ++result.index_1;
  }
  while (result.index_1 < result.index_2 && comp(A[result.index_2], 0)) {
    --result.index_2;
  }

  while (result.index_1 < result.index_2) {
    if (A[result.index_1] + A[result.index_2] == k) {
      return result;
    } else if (comp(A[result.index_1] + A[result.index_2], k)) {
      do {
        ++result.index_1;
      } while (result.index_1 < result.index_2 && comp(A[result.index_1], 0));
    } else {
      do {
        --result.index_2;
      } while (result.index_1 < result.index_2 && comp(A[result.index_2], 0));
    }
  }
  return {-1, -1};  // No answer.
}

IndexPair FindPositiveNegativePair(const vector<int>& A, int k) {
  // result.index_1 for positive, and result.index_2 for negative.
  IndexPair result = IndexPair{static_cast<int>(A.size() - 1),
                               static_cast<int>(A.size() - 1)};
  // Find the last positive or zero.
  while (result.index_1 >= 0 && A[result.index_1] < 0) {
    --result.index_1;
  }

  // Find the last negative.
  while (result.index_2 >= 0 && A[result.index_2] >= 0) {
    --result.index_2;
  }

  while (result.index_1 >= 0 && result.index_2 >= 0) {
    if (A[result.index_1] + A[result.index_2] == k) {
      return result;
    } else if (A[result.index_1] + A[result.index_2] > k) {
      do {
        --result.index_1;
      } while (result.index_1 >= 0 && A[result.index_1] < 0);
    } else {  // A[result.index_1] + A[result.index_2] < k.
      do {
        --result.index_2;
      } while (result.index_2 >= 0 && A[result.index_2] >= 0);
    }
  }
  return {-1, -1};  // No answer.
}
// @exclude

static void SimpleTest() {
  vector<int> A = {0, 0, -1, 2, -3, -3};
  IndexPair ans = FindPairSumK(A, 2);
  assert(ans.index_1 != -1);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    int n;
    if (argc >= 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> dis(-10000, 10000);
    generate_n(back_inserter(A), n, [&] { return dis(gen); });
    sort(A.begin(), A.end(), [](int x, int y) { return abs(x) < abs(y); });
    int k = dis(gen);
    IndexPair ans = FindPairSumK(A, k);
    if (ans.index_1 != -1 && ans.index_2 != -1) {
      assert(A[ans.index_1] + A[ans.index_2] == k);
      cout << A[ans.index_1] << "+" << A[ans.index_2] << "=" << k << endl;
    } else {
      sort(A.begin(), A.end());
      int l = 0, r = A.size() - 1;
      bool found = false;
      while (l < r) {
        if (A[l] + A[r] == k) {
          cout << A[l] << "+" << A[r] << "=" << k << endl;
          found = true;
          break;
        } else if (A[l] + A[r] < k) {
          ++l;
        } else {
          --r;
        }
      }
      cout << "no answer" << endl;
      assert(!found);
    }
  }
  return 0;
}
