// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <deque>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
// Given n, return all primes up to and including n.
vector<int> GeneratePrimes(int n) {
  const int size = floor(0.5 * (n - 3)) + 1;
  vector<int> primes;
  primes.emplace_back(2);
  // is_prime[i] represents whether (2i + 3) is prime or not.
  // Initially, set each to true. Then use sieving to eliminate nonprimes.
  deque<bool> is_prime(size, true);
  for (int i = 0; i < size; ++i) {
    if (is_prime[i]) {
      int p = (i * 2) + 3;
      primes.emplace_back(p);
      // Sieving from p^2, whose value is (4i^2 + 12i + 9). The index in
      // is_prime is (2i^2 + 6i + 3) because is_prime[i] represents 2i + 3.
      //
      // Note that we need to use long for j because p^2 might overflow.
      for (long j = ((static_cast<long>(i) * static_cast<long>(i)) * 2) +
                    6 * i + 3;
           j < size; j += p) {
        is_prime[j] = false;
      }
    }
  }
  return primes;
}
// @exclude

int main(int argc, char* argv[]) {
  if (argc == 2) {
    int n = atoi(argv[1]);
    cout << "n = " << n << endl;
    vector<int> primes = GeneratePrimes(n);
    for (int p : primes) {
      cout << p << endl;
    }
    for (size_t i = 0; i < primes.size(); ++i) {
      for (int j = 2; j < primes[i]; ++j) {
        assert(primes[i] % j);
      }
    }
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 100; ++times) {
      uniform_int_distribution<int> dis(2, 100000);
      int n = dis(gen);
      cout << "n = " << n << endl;
      vector<int> primes = GeneratePrimes(n);
      for (size_t i = 0; i < primes.size(); ++i) {
        for (int j = 2; j < primes[i]; ++j) {
          assert(primes[i] % j);
        }
      }
    }
  }
  return 0;
}
