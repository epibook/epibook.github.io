// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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
struct Interval {
  int start, finish;
};

struct Endpoint {
  bool operator<(const Endpoint& e) const {
    return time != e.time ? time < e.time : (isStart && !e.isStart);
  }

  int time;
  bool isStart;
};

int FindMaxConcurrentEvents(const vector<Interval>& A) {
  // Builds the endpoint array.
  vector<Endpoint> E;
  for (const Interval& i : A) {
    E.emplace_back(Endpoint{i.start, true});
    E.emplace_back(Endpoint{i.finish, false});
  }
  // Sorts the endpoint array according to the time.
  sort(E.begin(), E.end());

  // Finds the maximum number of events overlapped.
  int max_count = 0, count = 0;
  for (const Endpoint& e : E) {
    if (e.isStart) {
      max_count = max(++count, max_count);
    } else {
      --count;
    }
  }
  return max_count;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 100000);
    n = dis(gen);
  }
  vector<Interval> A;
  for (int i = 0; i < n; ++i) {
    Interval temp;
    uniform_int_distribution<int> dis1(0, 99999);
    temp.start = dis1(gen);
    uniform_int_distribution<int> dis2(temp.start + 1, temp.start + 10000);
    temp.finish = dis2(gen);
    A.emplace_back(temp);
  }
  int ans = FindMaxConcurrentEvents(A);
  cout << ans << endl;
  return 0;
}
