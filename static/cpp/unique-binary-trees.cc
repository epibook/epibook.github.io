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
using std::string;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

// @include
int num_unique_binary_trees(int n) {
  vector<int> num(n + 1, 0);
  num[0] = 1;
  for (int i = 1; i <= n; ++i) {
    for (int j = 0; j < i; ++j) {
      num[i] += num[j] * num[i - j - 1];
    }
  }
  return num.back();
}
// @exclude

int main(int argc, char** argv) {
  assert(16796 == num_unique_binary_trees(10));
  assert(4862 == num_unique_binary_trees(9));
  assert(2 == num_unique_binary_trees(2));
  assert(5 == num_unique_binary_trees(3));
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 15);
    n = dis(gen);
  }
  cout << "n = " << n << endl;
  cout << num_unique_binary_trees(n) << endl;
  return 0;
}
