// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <queue>
#include <stdexcept>
#include <vector>

#include "./Queue_with_max_alternative.h"

using std::cout;
using std::endl;
using std::length_error;
using std::queue;
using std::vector;
using QueueWithMaxAlternative::QueueWithMax;

// @include
struct TrafficElement {
  // Following operators are needed for QueueWithMax with maximum.
  bool operator>(const TrafficElement& that) const {
    return volume > that.volume ||
           (volume == that.volume && time > that.time);
  }

  bool operator==(const TrafficElement& that) const {
    return time == that.time && volume == that.volume;
  }

  bool operator>=(const TrafficElement& that) const {
    return *this > that || *this == that;
  }

  int time;
  double volume;
};

vector<TrafficElement> CalculateTrafficVolumes(
    const vector<TrafficElement>& A, int w) {
  QueueWithMax<TrafficElement> sliding_window;
  vector<TrafficElement> maximum_volumes;
  for (const auto traffic_info : A) {
    sliding_window.Enqueue(traffic_info);
    while (traffic_info.time - sliding_window.Head().time > w) {
      sliding_window.Dequeue();
    }
    maximum_volumes.emplace_back(
        TrafficElement{traffic_info.time, sliding_window.Max().volume});
  }
  return maximum_volumes;
}
// @exclude

int main(int argc, char* argv[]) {
  int w = 3;
  vector<TrafficElement> A = {
      TrafficElement{0, 1.3}, TrafficElement{2, 2.5}, TrafficElement{3, 3.7},
      TrafficElement{5, 1.4}, TrafficElement{6, 2.6}, TrafficElement{8, 2.2},
      TrafficElement{9, 1.7}, TrafficElement{14, 1.1}};
  auto result = CalculateTrafficVolumes(A, w);
  vector<TrafficElement> golden = {
      TrafficElement{0, 1.3}, TrafficElement{2, 2.5}, TrafficElement{3, 3.7},
      TrafficElement{5, 3.7}, TrafficElement{6, 3.7}, TrafficElement{8, 2.6},
      TrafficElement{9, 2.6}, TrafficElement{14, 1.1}};
  assert(equal(golden.begin(), golden.end(), result.begin(), result.end()));
  return 0;
}
