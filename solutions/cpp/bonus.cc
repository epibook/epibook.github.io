// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <functional>
#include <iostream>
#include <numeric>
#include <queue>
#include <random>
#include <string>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::function;
using std::max;
using std::pair;
using std::priority_queue;
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
  struct EmployeeData {
    int productivity, index;
  };
  priority_queue<EmployeeData, vector<EmployeeData>,
                 function<bool(EmployeeData, EmployeeData)>>
  min_heap([](const EmployeeData& lhs, const EmployeeData& rhs) {
    return lhs.productivity > rhs.productivity;
  });
  for (int i = 0; i < productivity.size(); ++i) {
    min_heap.emplace(EmployeeData{productivity[i], i});
  }

  // Initially assigns one ticket to everyone.
  vector<int> tickets(productivity.size(), 1);
  // Fills tickets from lowest rating to highest rating.
  while (!min_heap.empty()) {
    int next_dev = min_heap.top().index;
    // Handles the left neighbor.
    if (next_dev > 0 && productivity[next_dev] > productivity[next_dev - 1]) {
      tickets[next_dev] = tickets[next_dev - 1] + 1;
    }
    // Handles the right neighbor.
    if (next_dev + 1 < tickets.size() &&
        productivity[next_dev] > productivity[next_dev + 1]) {
      tickets[next_dev] = max(tickets[next_dev], tickets[next_dev + 1] + 1);
    }
    min_heap.pop();
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
