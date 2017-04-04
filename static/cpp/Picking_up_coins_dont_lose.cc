// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
// Return 0 means choosing F (even numbered coins),
// and return 1 means choosing S (odd numbered coins).
int pick_up_coins(const vector<int>& C) {
  int even_sum = 0, odd_sum = 0;
  for (int i = 0; i < C.size(); ++i) {
    if (i % 2) {  // odd.
      odd_sum += C[i];
    } else {  // even.
      even_sum += C[i];
    }
  }
  return even_sum >= odd_sum ? 0 : 1;
}
// @exclude

void check(const vector<int>& C, int choose) {
  int even = 0, odd = 0;
  for (int i = 0; i < C.size(); ++i) {
    if (i % 2) {
      odd += C[i];
    } else {
      even += C[i];
    }
  }
  if (choose == 0) {
    assert(even >= odd);
  } else {
    assert(odd >= even);
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    vector<int> C;
    if (argc >= 2) {
      for (int i = 1; i < argc; ++i) {
        C.emplace_back(atoi(argv[i]));
      }
    } else {
      uniform_int_distribution<int> dis(1, 1000);
      C.resize(dis(gen));
      for (int i = 0; i < C.size(); ++i) {
        uniform_int_distribution<int> dis(0, 99);
        C[i] = dis(gen);
      }
    }

    for (size_t i = 0; i < C.size(); ++i) {
      cout << C[i] << ' ';
    }
    cout << endl;

    int res = pick_up_coins(C);
    cout << res << endl;
    check(C, res);
  }
  return 0;
}
