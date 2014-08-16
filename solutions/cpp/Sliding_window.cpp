// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <queue>
#include <stdexcept>
#include <vector>

#include "./Queue_with_max_using_deque.h"

using std::cout;
using std::endl;
using std::length_error;
using std::queue;
using std::vector;

// @include
struct TrafficElement {
  bool operator<(const TrafficElement& that) const {
    return volume < that.volume;
  }

  bool operator==(const TrafficElement& that) const {
    return time == that.time && volume == that.volume;
  }

  int time, volume;
};

void CalculateTrafficVolumes(const vector<TrafficElement>& A, int w) {
  Queue<TrafficElement> Q;
  for (int i = 0; i < A.size(); ++i) {
    Q.Enqueue(A[i]);
    while (A[i].time - Q.Head().time > w) {
      Q.Dequeue();
    }
    cout << "Max after inserting " << i << " is " << Q.Max().volume << endl;
  }
}
// @exclude

int main(int argc, char* argv[]) {
  int w = 3;
  // It should output 0, 1, 3, 3, 3, 3, 2, 2.
  vector<TrafficElement> A = {TrafficElement{0, 0}, TrafficElement{1, 1},
                              TrafficElement{2, 3}, TrafficElement{3, 1},
                              TrafficElement{4, 0}, TrafficElement{5, 2},
                              TrafficElement{6, 2}, TrafficElement{7, 2}};
  CalculateTrafficVolumes(A, w);
  return 0;
}
