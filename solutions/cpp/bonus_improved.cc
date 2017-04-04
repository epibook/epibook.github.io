// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <numeric>
#include <random>
#include <string>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::pair;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

void CheckAns(const vector<int>& productivity, const vector<int>& C) {
  for (size_t i = 0; i < productivity.size(); ++i) {
    if (i > 0) {
      assert((productivity[i] > productivity[i - 1] && C[i] > C[i - 1]) ||
             (productivity[i] < productivity[i - 1] && C[i] < C[i - 1]) ||
             productivity[i] == productivity[i - 1]);
    }
    if (i + 1 < productivity.size()) {
      assert((productivity[i] > productivity[i + 1] && C[i] > C[i + 1]) ||
             (productivity[i] < productivity[i + 1] && C[i] < C[i + 1]) ||
             productivity[i] == productivity[i + 1]);
    }
  }
}

// @include
vector<int> CalculateBonus(const vector<int>& productivity) {
  // Initially assigns one ticket to everyone.
  vector<int> tickets(productivity.size(), 1);
  // From left to right.
  for (int i = 1; i < productivity.size(); ++i) {
    if (productivity[i] > productivity[i - 1]) {
      tickets[i] = tickets[i - 1] + 1;
    }
  }
  // From right to left.
  for (int i = productivity.size() - 2; i >= 0; --i) {
    if (productivity[i] > productivity[i + 1]) {
      tickets[i] = max(tickets[i], tickets[i + 1] + 1);
    }
  }
  return tickets;
}
// @exclude

template <typename T>
bool EqualVector(const vector<T>& A, const vector<T>& B) {
  return equal(A.begin(), A.end(), B.begin(), B.end());
}

void SmallTest() {
  vector<int> A = {1, 2, 2};
  vector<int> golden_A = {1, 2, 1};
  assert(EqualVector(CalculateBonus(A), golden_A));
  A = {1, 2, 3, 2, 1};
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
