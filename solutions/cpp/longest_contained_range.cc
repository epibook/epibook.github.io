// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

int CheckAns(vector<int> A) {
  sort(A.begin(), A.end());
  int max_interval_size = 1;
  int pre = A[0], len = 1;
  for (size_t i = 1; i < A.size(); ++i) {
    if (A[i] == pre + 1) {
      ++len;
    } else if (A[i] != pre) {
      max_interval_size = max(max_interval_size, len);
      len = 1;
    }
    pre = A[i];
  }
  max_interval_size = max(max_interval_size, len);
  cout << max_interval_size << endl;
  return max_interval_size;
}

int FindLongestContainedRange(const vector<int>& A) {
  if (A.empty()) {
    return 0;
  }

  unordered_set<int> t;  // records the unique appearance.
  // L[i] stores the upper range for i.
  unordered_map<int, int> L;
  // U[i] stores the lower range for i.
  unordered_map<int, int> U;
  for (size_t i = 0; i < A.size(); ++i) {
    if (t.emplace(A[i]).second) {
      L[A[i]] = U[A[i]] = A[i];
      // Merges with the interval starting on A[i] + 1.
      if (L.find(A[i] + 1) != L.cend()) {
        L[U[A[i]]] = L[A[i] + 1];
        U[L[A[i] + 1]] = U[A[i]];
        L.erase(A[i] + 1);
        U.erase(A[i]);
      }
      // Merges with the interval ending on A[i] - 1.
      if (U.find(A[i] - 1) != U.cend()) {
        U[L[A[i]]] = U[A[i] - 1];
        L[U[A[i] - 1]] = L[A[i]];
        U.erase(A[i] - 1);
        L.erase(A[i]);
      }
    }
  }

  auto m =
      max_element(L.cbegin(), L.cend(), [](const auto& a, const auto& b) {
        return a.second - a.first < b.second - b.first;
      });
  return m->second - m->first + 1;
}

// @include
int LongestContainedRange(const vector<int>& A) {
  // unprocessed_entries records the existence of each entry in A.
  unordered_set<int> unprocessed_entries(A.begin(), A.end());

  int max_interval_size = 0;
  while (!unprocessed_entries.empty()) {
    int a = *unprocessed_entries.begin();
    unprocessed_entries.erase(a);

    // Finds the lower bound of the largest range containing a.
    int lower_bound = a - 1;
    while (unprocessed_entries.count(lower_bound)) {
      unprocessed_entries.erase(lower_bound);
      --lower_bound;
    }

    // Finds the upper bound of the largest range containing a.
    int upper_bound = a + 1;
    while (unprocessed_entries.count(upper_bound)) {
      unprocessed_entries.erase(upper_bound);
      ++upper_bound;
    }

    max_interval_size = max(max_interval_size, upper_bound - lower_bound - 1);
  }
  return max_interval_size;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    size_t n;
    if (argc == 2) {
      n = stoul(argv[1]);
    } else {
      uniform_int_distribution<size_t> dis(0, 10000);
      n = dis(gen);
    }
    vector<int> A;
    uniform_int_distribution<int> dis_n(0, static_cast<int>(n));
    for (size_t i = 0; i < n; ++i) {
      A.emplace_back(dis_n(gen));
    }
    /*
    for (size_t i = 0; i < n; ++i) {
      cout << A[i] << " ";
    }
    cout << endl;
    //*/
    assert(FindLongestContainedRange(A) == CheckAns(A));
    assert(LongestContainedRange(A) == CheckAns(A));
  }
  return 0;
}
