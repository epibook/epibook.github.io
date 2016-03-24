// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

// @include
bool CanReachEnd(const vector<int>& max_advance_steps) {
  int furthest_reach_so_far = 0, last_index = max_advance_steps.size() - 1;
  for (int i = 0;
       i <= furthest_reach_so_far && furthest_reach_so_far < last_index;
       ++i) {
    furthest_reach_so_far =
        max(furthest_reach_so_far, max_advance_steps[i] + i);
  }
  return furthest_reach_so_far >= last_index;
}
// @exclude

void SmallTest() {
  vector<int> max_advance_steps = {2, 3, 1, 1, 4};
  assert(CanReachEnd(max_advance_steps));
  max_advance_steps = {3, 2, 1, 0, 4};
  assert(!CanReachEnd(max_advance_steps));
  max_advance_steps = {3, 2, 1, -10, 4};
  assert(!CanReachEnd(max_advance_steps));
  max_advance_steps = {2, 3, -1, -1, 4};
  assert(CanReachEnd(max_advance_steps));
  max_advance_steps = {2, 2, -1, -1, 100};
  assert(!CanReachEnd(max_advance_steps));
}

int main(int argc, char** argv) {
  SmallTest();
  return 0;
  default_random_engine gen((random_device())());
  size_t n;
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    uniform_int_distribution<size_t> dis(1, 1000);
    n = dis(gen);
  }
  uniform_int_distribution<int> A_dis(1, 10);
  vector<int> max_advance_steps;
  generate_n(back_inserter(max_advance_steps), n, [&] { return A_dis(gen); });
  cout << std::boolalpha << CanReachEnd(max_advance_steps) << endl;
  return 0;
}
