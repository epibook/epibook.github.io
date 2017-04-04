// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <limits>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::distance;
using std::endl;
using std::generate_canonical;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::uniform_real_distribution;
using std::vector;

// @include
int NonuniformRandomNumberGeneration(const vector<int>& values,
                                     const vector<double>& probabilities) {
  vector<double> prefix_sums_of_probabilities;
  prefix_sums_of_probabilities.emplace_back(0.0);
  // Creating the endpoints for the intervals corresponding to the
  // probabilities.
  partial_sum(probabilities.cbegin(), probabilities.cend(),
              back_inserter(prefix_sums_of_probabilities));

  default_random_engine seed((random_device())());
  const double uniform_0_1 =
      generate_canonical<double, numeric_limits<double>::digits>(seed);
  // Find the index of the interval that uniform_0_1 lies in, which is the
  // return value of upper_bound() minus 1.
  const int interval_idx =
      distance(
          prefix_sums_of_probabilities.cbegin(),
          upper_bound(prefix_sums_of_probabilities.cbegin(),
                      prefix_sums_of_probabilities.cend(), uniform_0_1)) -
      1;
  return values[interval_idx];
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
  vector<int> T(n);
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
  // Test. Perform the nonuniform random number generation for n * kTimes
  // times
  // and calculate the distribution of each bucket.
  const size_t kTimes = 100000;
  vector<int> counts(n, 0);
  for (size_t i = 0; i < n * kTimes; ++i) {
    int t = NonuniformRandomNumberGeneration(T, P);
    ++counts[t];
  }
  for (size_t i = 0; i < n; ++i) {
    cout << static_cast<double>(counts[i]) / (n * kTimes) << " " << P[i]
         << endl;
    assert(fabs(static_cast<double>(counts[i]) / (n * kTimes) - P[i]) < 0.01);
  }
  return 0;
}
