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
int jump(const vector<int>& A) {
  int step = 0, last_reach = 0, reach = 0;
  for (int i = 0; i < A.size(); ++i) {
    if (i > last_reach) {
      last_reach = reach, ++step;
    }
    reach = max(reach, i + A[i]);
  }
  return step;
}
// @exclude

void small_test() {
  vector<int> A1 = {2, 3, 1, 1, 4};
  assert(2 == jump(A1));
  vector<int> A2 = {3, 1, 4, 1, 1, 1};
  assert(2 == jump(A2));
}

int main(int argc, char** argv) {
  small_test();
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
  cout << jump(A) << endl;
  return 0;
}
