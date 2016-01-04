// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
  vector<int> primes;
  // is_prime[p] represents if p is prime or not. Initially, set each to true,
  // excepting 0 and 1. Then use sieving to eliminate nonprimes.
  deque<bool> is_prime(n + 1, true);
  is_prime[0] = is_prime[1] = false;
  for (int p = 2; p < n; ++p) {
    if (is_prime[p]) {
      primes.emplace_back(p);
      // Sieve p's multiples.
      for (int j = p; j <= n; j += p) {
        is_prime[j] = false;
      }
    }
  }
  return primes;
}
// @exclude

// Can also test against output of primesieve program:
// unix% primesieve 1000 --count=1 -p1
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
