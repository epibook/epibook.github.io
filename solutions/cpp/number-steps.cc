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
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

// @include
int NumberSteps(int n, int k) {
  if (n <= 1) {
    return 1;
  }

  vector<int> steps(k + 1, 0);
  steps[0] = steps[1] = 1;
  for (int i = 2; i <= n; ++i) {
    steps[i % (k + 1)] = 0;
    for (int j = 1; j <= k && i - j >= 0; ++j) {
      steps[i % (k + 1)] += steps[(i - j) % (k + 1)];
    }
  }
  return steps[n % (k + 1)];
}
// @exclude

int main(int argc, char** argv) {
  assert(5 == NumberSteps(4, 2));
  default_random_engine gen((random_device())());
  int n, k;
  if (argc == 3) {
    n = stoi(argv[1]), k = stoi(argv[2]);
  } else {
    uniform_int_distribution<int> dis(1, 20);
    n = dis(gen);
    uniform_int_distribution<int> k_dis(1, n);
    k = k_dis(gen);
  }
  cout << "n = " << n << ", k = " << k << endl;
  cout << NumberSteps(n, k) << endl;
  return 0;
}
