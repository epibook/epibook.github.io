// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
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
int MinimumWaitingTime(vector<int> service_time) {
  // Sort the query time in increasing order.
  sort(service_time.begin(), service_time.end());

  int waiting = 0;
  for (int i = 0; i < service_time.size(); ++i) {
    waiting += service_time[i] * (service_time.size() - (i + 1));
  }
  return waiting;
}
// @exclude

int main(int argc, char* argv[]) {
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
  cout << MinimumWaitingTime(waiting_time) << endl;
  return 0;
}
