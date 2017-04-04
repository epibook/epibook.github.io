// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <random>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::uniform_real_distribution;

// @include
// Return the number of failed trials.
int simulate_biased_coin(int n, int trials) {
  default_random_engine gen((random_device())());  // random num generator.
  // Generate random double in [0.0, 1.0].
  uniform_real_distribution<double> dis(0.0, 1.0);
  const double kBias = 0.4;
  int fails = 0;
  for (int i = 0; i < trials; ++i) {
    int biased_num = 0;
    for (int j = 0; j < n; ++j) {
      biased_num += (dis(gen) >= kBias);
    }

    if (biased_num < (n / 2)) {
      ++fails;
    }
  }
  return fails;
}
// @exclude

int main(int argc, char* argv[]) {
  int n, trials;
  default_random_engine gen((random_device())());
  if (argc == 3) {
    n = atoi(argv[1]);
    trials = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    n = dis(gen);
    trials = dis(gen);
  }
  int fails = simulate_biased_coin(n, trials);
  cout << "fails = " << fails << endl;
  cout << "ratio = " << static_cast<double>(fails) / trials << endl;
  return 0;
}
