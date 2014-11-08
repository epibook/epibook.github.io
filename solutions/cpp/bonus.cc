// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <queue>
#include <numeric>
#include <random>
#include <string>
#include <vector>
#include <utility>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::pair;
using std::priority_queue;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

void CheckAns(const vector<int>& ratings, const vector<int>& C) {
  for (size_t i = 0; i < ratings.size(); ++i) {
    if (i > 0) {
      assert((ratings[i] > ratings[i - 1] && C[i] > C[i - 1]) ||
             (ratings[i] < ratings[i - 1] && C[i] < C[i - 1]) ||
             ratings[i] == ratings[i - 1]);
    }
    if (i + 1 < ratings.size()) {
      assert((ratings[i] > ratings[i + 1] && C[i] > C[i + 1]) ||
             (ratings[i] < ratings[i + 1] && C[i] < C[i + 1]) ||
             ratings[i] == ratings[i + 1]);
    }
  }
}

// @include
struct Compare {
  bool operator()(const pair<int, int>& lhs, const pair<int, int>& rhs) {
    return lhs.first > rhs.first;
  }
};

vector<int> CalculateBonus(const vector<int>& ratings) {
  priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> H;
  for (int i = 0; i < ratings.size(); ++i) {
    H.emplace(ratings[i], i);
  }

  // T stores the amount of bonus each one is assigned.
  vector<int> T(ratings.size(), 1);
  // Fills T from lowest rating one to topmost rating.
  while (!H.empty()) {
    auto& p = H.top();
    // Handles the left neighbor.
    if (p.second > 0) {
      if (ratings[p.second] > ratings[p.second - 1]) {
        T[p.second] = T[p.second - 1] + 1;
      }
    }
    // Handles the right neighbor.
    if (p.second + 1 < T.size()) {
      if (ratings[p.second] > ratings[p.second + 1]) {
        T[p.second] = max(T[p.second], T[p.second + 1] + 1);
      }
    }
    H.pop();
  }
  return T;
}
// @exclude

template <typename T>
bool EqualVector(const vector<T>& A, const vector<T>& B) {
  return A.size() == B.size() && equal(A.begin(), A.end(), B.begin());
}

void SmallTest() {
  vector<int> A = {1, 2, 2};
  vector<int> golden_A = {1, 2, 1};
  assert(EqualVector(CalculateBonus(A), golden_A));
  A = {1,2,3,2,1};
  golden_A = {1, 2, 3, 2, 1};
  assert(EqualVector(CalculateBonus(A), golden_A));
  A = {300, 400, 500, 200};
  golden_A = {1, 2, 3, 1};
  assert(EqualVector(CalculateBonus(A), golden_A));
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 1000);
      n = dis(gen);
    }
    vector<int> ratings;
    uniform_int_distribution<int> dis(1, 10000);
    for (int i = 0; i < n; ++i) {
      ratings.emplace_back(dis(gen));
    }
    auto T = CalculateBonus(ratings);
    CheckAns(ratings, T);
  }
  return 0;
}
