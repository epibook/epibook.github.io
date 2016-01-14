// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
struct Event {
  int start, finish;
};

struct Endpoint {
  bool operator<(const Endpoint& e) const {
    // If times are equal, an endpoint that starts an interval comes first.
    return time != e.time ? time < e.time : (isStart && !e.isStart);
  }

  int time;
  bool isStart;
};

int FindMaxSimultaneousEvents(const vector<Event>& A) {
  // Builds an array of all endpoints.
  vector<Endpoint> E;
  for (const Event& event : A) {
    E.emplace_back(Endpoint{event.start, true});
    E.emplace_back(Endpoint{event.finish, false});
  }
  // Sorts the endpoint array according to the time, breaking ties
  // by putting start times before end times.
  sort(E.begin(), E.end());

  // Track the number of simultaneous events, and record the maximum
  // number of simultaneous events.
  int max_num_simultaneous_events = 0, num_simultaneous_events = 0;
  for (const Endpoint& endpoint : E) {
    if (endpoint.isStart) {
      ++num_simultaneous_events;
      max_num_simultaneous_events =
          max(num_simultaneous_events, max_num_simultaneous_events);
    } else {
      --num_simultaneous_events;
    }
  }
  return max_num_simultaneous_events;
}
// @exclude

void SimpleTest() {
  vector<Event> A = {{1, 5},  {2, 7},   {4, 5},   {6, 10}, {8, 9},
                     {9, 17}, {11, 13}, {12, 15}, {14, 15}};
  assert(FindMaxSimultaneousEvents(A) == 3);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 100000);
    n = dis(gen);
  }
  vector<Event> A;
  for (int i = 0; i < n; ++i) {
    Event temp;
    uniform_int_distribution<int> dis1(0, 99999);
    temp.start = dis1(gen);
    uniform_int_distribution<int> dis2(temp.start + 1, temp.start + 10000);
    temp.finish = dis2(gen);
    A.emplace_back(temp);
  }
  int ans = FindMaxSimultaneousEvents(A);
  cout << ans << endl;
  return 0;
}
