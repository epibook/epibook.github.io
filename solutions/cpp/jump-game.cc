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
bool CanReach(const vector<int>& A) {
  int furthest_reach = 0;
  for (int i = 0; i <= furthest_reach && furthest_reach + 1 < A.size(); ++i) {
    furthest_reach = max(furthest_reach, A[i] + i);
  }
  return furthest_reach + 1 >= A.size();
}
// @exclude

void SmallTest() {
  vector<int> A = {2, 3, 1, 1, 4};
  assert(CanReach(A));
  A = {3, 2, 1, 0, 4};
  assert(!CanReach(A));
  A = {3, 2, 1, -10, 4};
  assert(!CanReach(A));
  A = {2, 3, -1, -1, 4};
  assert(CanReach(A));
  A = {2, 2, -1, -1, 100};
  assert(!CanReach(A));
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
  vector<int> A;
  generate_n(back_inserter(A), n, [&] { return A_dis(gen); });
  cout << std::boolalpha << CanReach(A) << endl;
  return 0;
}
