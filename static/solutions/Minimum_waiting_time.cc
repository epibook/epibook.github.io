// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int MinimumTotalWaitingTime(vector<int> service_times) {
  // Sort the service times in increasing order.
  sort(service_times.begin(), service_times.end());

  int total_waiting_time = 0;
  for (int i = 0; i < service_times.size(); ++i) {
    int num_remaining_queries = service_times.size() - (i + 1);
    total_waiting_time += service_times[i] * num_remaining_queries;
  }
  return total_waiting_time;
}
// @exclude

void SmallTest() { assert(10 == MinimumTotalWaitingTime({5, 1, 2, 3})); }

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 100);
    n = dis(gen);
  }
  vector<int> waiting_time;
  for (int i = 0; i < n; ++i) {
    uniform_int_distribution<int> dis(0, 999);
    waiting_time.push_back(dis(gen));
  }
  for (int i = 0; i < n; ++i) {
    cout << waiting_time[i] << ' ';
  }
  cout << endl;
  cout << MinimumTotalWaitingTime(waiting_time) << endl;
  return 0;
}
