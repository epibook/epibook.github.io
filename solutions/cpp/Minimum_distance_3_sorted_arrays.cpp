// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <queue>
#include <random>
#include <set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::numeric_limits;
using std::random_device;
using std::set;
using std::uniform_int_distribution;
using std::vector;

int Distance(const vector<vector<int>>& sorted_arrays, const vector<int>& idx) {
  int max_val = numeric_limits<int>::min(),
      min_val = numeric_limits<int>::max();
  for (int i = 0; i < idx.size(); ++i) {
    max_val = max(max_val, sorted_arrays[i][idx[i]]);
    min_val = min(min_val, sorted_arrays[i][idx[i]]);
  }
  return max_val - min_val;
}

// @include
struct ArrayData {
  bool operator<(const ArrayData& a) const {
    if (val != a.val) {
      return val < a.val;
    } else {
      return idx < a.idx;
    }
  }

  int idx;
  int val;
};

int FindClosestElementsInSortedArrays(
    const vector<vector<int>>& sorted_arrays) {
  // Indices into each of the arrays. 
  vector<int> idx(sorted_arrays.size(), 0);
  int min_dis = numeric_limits<int>::max();
  set<ArrayData> current_heads;

  // Adds the minimum element of each array in to current_heads.
  for (int i = 0; i < sorted_arrays.size(); ++i) {
    if (idx[i] >= sorted_arrays[i].size()) {
      return min_dis;
    }
    current_heads.emplace(ArrayData{i, sorted_arrays[i][idx[i]]});
  }

  while (true) {
    min_dis = min(min_dis,
                  current_heads.crbegin()->val - current_heads.cbegin()->val);
    int tar = current_heads.cbegin()->idx;
    // Returns if there is no remaining element in one array.
    if (++idx[tar] >= sorted_arrays[tar].size()) {
      return min_dis;
    }
    current_heads.erase(current_heads.begin());
    current_heads.emplace(ArrayData{tar, sorted_arrays[tar][idx[tar]]});
  }
}
// @exclude

void RecGenAnswer(const vector<vector<int>>& sorted_arrays, vector<int>& idx, int level,
                  int* ans) {
  if (level == sorted_arrays.size()) {
    *ans = min(Distance(sorted_arrays, idx), *ans);
    return;
  }
  for (int i = 0; i < sorted_arrays[level].size(); ++i) {
    idx[level] = i;
    RecGenAnswer(sorted_arrays, idx, level + 1, ans);
  }
}

int BruteForceGenAnswer(const vector<vector<int>>& sorted_arrays) {
  int ans = numeric_limits<int>::max();
  vector<int> idx(sorted_arrays.size());
  RecGenAnswer(sorted_arrays, idx, 0, &ans);
  cout << ans << endl;
  return ans;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    vector<vector<int>> sorted_arrays;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 5);
      n = dis(gen);
    }
    sorted_arrays.resize(n);
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 40);
      int size = dis(gen);
      for (int j = 0; j < size; ++j) {
        uniform_int_distribution<int> dis(0, 9999);
        sorted_arrays[i].emplace_back(dis(gen));
      }
      sort(sorted_arrays[i].begin(), sorted_arrays[i].end());
    }
    int ans = FindClosestElementsInSortedArrays(sorted_arrays);
    cout << ans << endl;
    assert(BruteForceGenAnswer(sorted_arrays) == ans);
  }
  return 0;
}
