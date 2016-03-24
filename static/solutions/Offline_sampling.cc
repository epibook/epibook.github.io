// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./Offline_sampling.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

int main(int argc, char* argv[]) {
  int n, k;
  default_random_engine gen((random_device())());
  if (argc == 2) {
    n = atoi(argv[1]);
    uniform_int_distribution<int> dis(1, n);
    k = dis(gen);
  } else if (argc == 3) {
    n = atoi(argv[1]);
    k = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> n_dis(1, 1000000);
    n = n_dis(gen);
    uniform_int_distribution<int> k_dis(1, n);
    k = k_dis(gen);
  }
  vector<int> A(n);
  iota(A.begin(), A.end(), 0);
  cout << n << ' ' << k << endl;
  RandomSampling(k, &A);
  for (int i = 0; i < A.size(); i++) {
    cout << i << ":" << A[i] << " ";
  }
  cout << endl;
  return 0;
}
