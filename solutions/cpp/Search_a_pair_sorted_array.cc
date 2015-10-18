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

template <typename Compare>
pair<int, int> FindPairUsingCompare(const vector<int>&, int k, Compare);
pair<int, int> FindPositiveNegativePair(const vector<int>&, int);

// @include
pair<int, int> FindPairSumK(const vector<int>& A, int k) {
  pair<int, int> result = FindPositiveNegativePair(A, k);
  if (result.first == -1 && result.second == -1) {
    return k >= 0 ? FindPairUsingCompare(A, k, less<int>())
                  : FindPairUsingCompare(A, k, greater_equal<int>());
  }
  return result;
}

template <typename Compare>
pair<int, int> FindPairUsingCompare(const vector<int>& A, int k,
                                    Compare comp) {
  pair<int, int> result(0, A.size() - 1);
  while (result.first < result.second && comp(A[result.first], 0)) {
    ++result.first;
  }
  while (result.first < result.second && comp(A[result.second], 0)) {
    --result.second;
  }

  while (result.first < result.second) {
    if (A[result.first] + A[result.second] == k) {
      return result;
    } else if (comp(A[result.first] + A[result.second], k)) {
      do {
        ++result.first;
      } while (result.first < result.second && comp(A[result.first], 0));
    } else {
      do {
        --result.second;
      } while (result.first < result.second && comp(A[result.second], 0));
    }
  }
  return {-1, -1};  // No answer.
}

pair<int, int> FindPositiveNegativePair(const vector<int>& A, int k) {
  // result.first for positive, and result.second for negative.
  pair<int, int> result(A.size() - 1, A.size() - 1);
  // Find the last positive or zero.
  while (result.first >= 0 && A[result.first] < 0) {
    --result.first;
  }

  // Find the last negative.
  while (result.second >= 0 && A[result.second] >= 0) {
    --result.second;
  }

  while (result.first >= 0 && result.second >= 0) {
    if (A[result.first] + A[result.second] == k) {
      return result;
    } else if (A[result.first] + A[result.second] > k) {
      do {
        --result.first;
      } while (result.first >= 0 && A[result.first] < 0);
    } else {  // A[result.first] + A[result.second] < k.
      do {
        --result.second;
      } while (result.second >= 0 && A[result.second] >= 0);
    }
  }
  return {-1, -1};  // No answer.
}
// @exclude

static void SimpleTest() {
  vector<int> A = {0, 0, -1, 2, -3, -3};
  pair<int, int> ans = FindPairSumK(A, 2);
  assert(ans.first != -1);
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
    pair<int, int> ans = FindPairSumK(A, k);
    if (ans.first != -1 && ans.second != -1) {
      assert(A[ans.first] + A[ans.second] == k);
      cout << A[ans.first] << "+" << A[ans.second] << "=" << k << endl;
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
