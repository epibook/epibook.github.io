// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
struct PairedTasks {
  int task_1, task_2;
};

vector<PairedTasks> OptimumTaskAssignment(vector<int> task_durations) {
  sort(task_durations.begin(), task_durations.end());
  vector<PairedTasks> optimum_assignments;
  for (int i = 0, j = task_durations.size() - 1; i < j; ++i, --j) {
    optimum_assignments.emplace_back(
        PairedTasks{task_durations[i], task_durations[j]});
  }
  return optimum_assignments;
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
  vector<PairedTasks> P(OptimumTaskAssignment(A));
  int max = numeric_limits<int>::min();
  for (size_t i = 0; i < P.size(); ++i) {
    if (P[i].task_1 + P[i].task_2 > max) {
      max = P[i].task_1 + P[i].task_2;
    }
  }
  cout << max << endl;
  return 0;
}
