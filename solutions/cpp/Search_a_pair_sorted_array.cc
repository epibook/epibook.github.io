// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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

template <typename Comp>
pair<int, int> FindPairUsingComp(const vector<int>& A, int k, Comp comp);
pair<int, int> FindPositiveNegativePair(const vector<int>& A, int k);

// @include
pair<int, int> FindPairSumK(const vector<int>& A, int k) {
  pair<int, int> ret = FindPositiveNegativePair(A, k);
  if (ret.first == -1 && ret.second == -1) {
    return k >= 0 ? FindPairUsingComp(A, k, less<int>())
                  : FindPairUsingComp(A, k, greater_equal<int>());
  }
  return ret;
}

template <typename Comp>
pair<int, int> FindPairUsingComp(const vector<int>& A, int k, Comp comp) {
  pair<int, int> ret(0, A.size() - 1);
  while (ret.first < ret.second && comp(A[ret.first], 0)) {
    ++ret.first;
  }
  while (ret.first < ret.second && comp(A[ret.second], 0)) {
    --ret.second;
  }

  while (ret.first < ret.second) {
    if (A[ret.first] + A[ret.second] == k) {
      return ret;
    } else if (comp(A[ret.first] + A[ret.second], k)) {
      do {
        ++ret.first;
      } while (ret.first < ret.second && comp(A[ret.first], 0));
    } else {
      do {
        --ret.second;
      } while (ret.first < ret.second && comp(A[ret.second], 0));
    }
  }
  return {-1, -1};  // no answer.
}

pair<int, int> FindPositiveNegativePair(const vector<int>& A, int k) {
  // ret.first for positive, and ret.second for negative.
  pair<int, int> ret(A.size() - 1, A.size() - 1);
  // Find the last positive or zero.
  while (ret.first >= 0 && A[ret.first] < 0) {
    --ret.first;
  }

  // Find the last negative.
  while (ret.second >= 0 && A[ret.second] >= 0) {
    --ret.second;
  }

  while (ret.first >= 0 && ret.second >= 0) {
    if (A[ret.first] + A[ret.second] == k) {
      return ret;
    } else if (A[ret.first] + A[ret.second] > k) {
      do {
        --ret.first;
      } while (ret.first >= 0 && A[ret.first] < 0);
    } else {  // A[ret.first] + A[ret.second] < k.
      do {
        --ret.second;
      } while (ret.second >= 0 && A[ret.second] >= 0);
    }
  }
  return {-1, -1};  // no answer.
}
// @exclude

int main(int argc, char* argv[]) {
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
    uniform_int_distribution<int> pos_or_neg(0, 1);
    uniform_int_distribution<int> dis(0, 10000);
    for (size_t i = 0; i < n; ++i) {
      A.emplace_back(((pos_or_neg(gen)) ? 1 : -1) * (dis(gen)));
    }
    sort(A.begin(), A.end(), [](int x, int y) { return abs(x) < abs(y); });
    int k = ((pos_or_neg(gen)) ? 1 : -1) * (dis(gen));
    /*
    for (const int& a : A) {
      cout << a << " ";
    }
    cout << endl << "k = " << k << endl;
    */
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
