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

int Distance(const vector<vector<int>>& arrs, const vector<int>& idx) {
  int max_val = numeric_limits<int>::min(),
      min_val = numeric_limits<int>::max();
  for (int i = 0; i < idx.size(); ++i) {
    max_val = max(max_val, arrs[i][idx[i]]);
    min_val = min(min_val, arrs[i][idx[i]]);
  }
  return max_val - min_val;
}

// @include
struct ArrData {
  bool operator<(const ArrData& a) const {
    if (val != a.val) {
      return val < a.val;
    } else {
      return idx < a.idx;
    }
  }

  int idx;
  int val;
};

int FindMinDistanceSortedArrays(const vector<vector<int>>& arrs) {
  // Pointers for each of arrs.
  vector<int> idx(arrs.size(), 0);
  int min_dis = numeric_limits<int>::max();
  set<ArrData> current_heads;

  // Each of arrs puts its minimum element into current_heads.
  for (int i = 0; i < arrs.size(); ++i) {
    if (idx[i] >= arrs[i].size()) {
      return min_dis;
    }
    current_heads.emplace(ArrData{i, arrs[i][idx[i]]});
  }

  while (true) {
    min_dis = min(min_dis,
                  current_heads.crbegin()->val - current_heads.cbegin()->val);
    int tar = current_heads.cbegin()->idx;
    // Returns if there is no remaining element in one array.
    if (++idx[tar] >= arrs[tar].size()) {
      return min_dis;
    }
    current_heads.erase(current_heads.begin());
    current_heads.emplace(ArrData{tar, arrs[tar][idx[tar]]});
  }
}
// @exclude

void RecGenAnswer(const vector<vector<int>>& arrs, vector<int>& idx, int level,
                  int* ans) {
  if (level == arrs.size()) {
    *ans = min(Distance(arrs, idx), *ans);
    return;
  }
  for (int i = 0; i < arrs[level].size(); ++i) {
    idx[level] = i;
    RecGenAnswer(arrs, idx, level + 1, ans);
  }
}

int BruteForceGenAnswer(const vector<vector<int>>& arrs) {
  int ans = numeric_limits<int>::max();
  vector<int> idx(arrs.size());
  RecGenAnswer(arrs, idx, 0, &ans);
  cout << ans << endl;
  return ans;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    vector<vector<int>> arrs;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 5);
      n = dis(gen);
    }
    arrs.resize(n);
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 40);
      int size = dis(gen);
      for (int j = 0; j < size; ++j) {
        uniform_int_distribution<int> dis(0, 9999);
        arrs[i].emplace_back(dis(gen));
      }
      sort(arrs[i].begin(), arrs[i].end());
    }
    int ans = FindMinDistanceSortedArrays(arrs);
    cout << ans << endl;
    assert(BruteForceGenAnswer(arrs) == ans);
  }
  return 0;
}
