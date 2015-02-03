// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <limits>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::pair;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<pair<int, int>> TaskAssignment(vector<int> task_durations) {
  sort(task_durations.begin(), task_durations.end());
  vector<pair<int, int>> task_pairings;
  for (int i = 0, j = task_durations.size() - 1; i < j; ++i, --j) {
    task_pairings.emplace_back(task_durations[i], task_durations[j]);
  }
  return task_pairings;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 10000);
    n = dis(gen);
  }
  vector<int> A;
  for (size_t i = 0; i < n; ++i) {
    uniform_int_distribution<int> dis(0, 999);
    A.emplace_back(dis(gen));
  }
  vector<pair<int, int>> P(TaskAssignment(A));
  int max = numeric_limits<int>::min();
  for (size_t i = 0; i < P.size(); ++i) {
    if (P[i].first + P[i].second > max) {
      max = P[i].first + P[i].second;
    }
  }
  cout << max << endl;
  return 0;
}
