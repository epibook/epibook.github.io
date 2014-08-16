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
// Given n, return the primes from 1 to n.
vector<int> GeneratePrimesFrom1ToN(int n) {
  const int kSize = floor(0.5 * (n - 3)) + 1;
  vector<int> primes;  // Stores the primes from 1 to n.
  primes.emplace_back(2);
  // is_prime[i] represents (2i + 3) is prime or not. Initially assuming
  // everyone is prime (by setting as true).
  deque<bool> is_prime(kSize, true);
  for (long i = 0; i < kSize; ++i) {
    if (is_prime[i]) {
      int p = (i * 2) + 3;
      primes.emplace_back(p);
      // Sieving from p^2, where p^2 = 4i^2 + 12i + 9 whose index in is_prime
      // is 2i^2 + 6i + 3 because is_prime[i] represents 2i + 3.
      for (long j = ((i * i) * 2) + 6 * i + 3; j < kSize; j += p) {
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
    vector<int> primes = GeneratePrimesFrom1ToN(n);
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
      vector<int> primes = GeneratePrimesFrom1ToN(n);
      for (size_t i = 0; i < primes.size(); ++i) {
        for (int j = 2; j < primes[i]; ++j) {
          assert(primes[i] % j);
        }
      }
    }
  }
  return 0;
}
