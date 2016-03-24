// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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

int ComputeNumberOfWaysToH(int, int, vector<int>*);

// @include
int NumberOfWaysToTop(int top, int maximum_step) {
  vector<int> number_of_ways_to_h(top + 1, 0);
  return ComputeNumberOfWaysToH(top, maximum_step, &number_of_ways_to_h);
}

int ComputeNumberOfWaysToH(int h, int maximum_step,
                           vector<int>* number_of_ways_to_h_ptr) {
  if (h <= 1) {
    return 1;
  }

  vector<int>& number_of_ways_to_h = *number_of_ways_to_h_ptr;
  if (number_of_ways_to_h[h] == 0) {
    for (int i = 1; i <= maximum_step && h - i >= 0; ++i) {
      number_of_ways_to_h[h] += ComputeNumberOfWaysToH(
          h - i, maximum_step, number_of_ways_to_h_ptr);
    }
  }
  return number_of_ways_to_h[h];
}
// @exclude

int main(int argc, char** argv) {
  assert(5 == NumberOfWaysToTop(4, 2));
  assert(1 == NumberOfWaysToTop(1, 2));
  assert(1 == NumberOfWaysToTop(0, 3));
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
  cout << NumberOfWaysToTop(n, k) << endl;
  return 0;
}
