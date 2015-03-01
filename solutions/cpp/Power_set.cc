// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cmath>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
void GeneratePowerSet(const vector<int>& S) {
  for (int i = 0; i < (1 << S.size()); ++i) {
    int x = i;
    while (x) {
      int tar = log2(x & ~(x - 1));
      cout << S[tar];
      if (x &= x - 1) {
        cout << ',';
      }
    }
    cout << endl;
  }
}
// @exclude

int main(int argc, char* argv[]) {
  vector<int> S;
  if (argc >= 2) {
    for (int i = 1; i < argc; ++i) {
      S.emplace_back(atoi(argv[i]));
    }
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(1, 10);
    S.resize(dis(gen));
    for (int i = 0; i < S.size(); ++i) {
      S[i] = i;
    }
  }
  GeneratePowerSet(S);
  return 0;
}
