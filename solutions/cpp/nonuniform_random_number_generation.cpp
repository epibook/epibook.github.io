// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::distance;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::uniform_real_distribution;
using std::vector;

// @include
double NonuniformRandomNumberGeneration(const vector<double>& T,
                                        const vector<double>& P) {
  vector<double> prefix_P;
  prefix_P.emplace_back(0);
  partial_sum(P.cbegin(), P.cend(), back_inserter(prefix_P));
  // gen object is used to generate random numbers.
  default_random_engine gen((random_device())());
  // Generate a random number in [0.0, 1.0].
  uniform_real_distribution<double> dis(0.0, 1.0);
  // upper_bound uses binary search to returns an iterator pointing to the
  // first element in (prefix_P.cbegin(), prefix_P.cend()) which compares
  // greater than dis(gen) which itself is a uniform random number in
  // [0.0, 1.0].
  auto it = upper_bound(prefix_P.cbegin(), prefix_P.cend(), dis(gen));
  return T[distance(prefix_P.cbegin(), it) - 1];
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  size_t n;
  if (argc == 2) {
    n = static_cast<size_t>(atoi(argv[1]));
  } else {
    uniform_int_distribution<size_t> dis(1, 50);
    n = dis(gen);
  }
  vector<double> T(n);
  iota(T.begin(), T.end(), 0);
  vector<double> P;
  double full_prob = 1.0;
  for (size_t i = 0; i < n - 1; ++i) {
    uniform_real_distribution<double> dis(0.0, full_prob);
    double pi = dis(gen);
    P.emplace_back(pi);
    full_prob -= pi;
  }
  P.emplace_back(full_prob);
  for (size_t i = 0; i < T.size(); ++i) {
    cout << T[i] << ' ';
  }
  cout << endl;
  for (size_t i = 0; i < P.size(); ++i) {
    cout << P[i] << ' ';
  }
  cout << endl;
  cout << NonuniformRandomNumberGeneration(T, P) << endl;
  // Test. Perform the nonuniform random number generation for n * kTimes times
  // and calculate the distribution of each bucket.
  const size_t kTimes = 100000;
  vector<int> counts(n, 0);
  for (size_t i = 0; i < n * kTimes; ++i) {
    double t = NonuniformRandomNumberGeneration(T, P);
    ++counts[static_cast<int>(t)];
  }
  for (size_t i = 0; i < n; ++i) {
    cout << static_cast<double>(counts[i]) / (n * kTimes) << " " << P[i]
         << endl;
    assert(fabs(static_cast<double>(counts[i]) / (n * kTimes) - P[i]) < 0.01);
  }
  return 0;
}
