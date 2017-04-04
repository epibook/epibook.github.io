// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <complex>
#include <iostream>
#include <random>
#include <set>
#include <vector>

using std::complex;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::set;
using std::uniform_int_distribution;
using std::vector;

bool is_unit(const complex<int>& z);

// @include
struct ComplexCompare {
  bool operator()(const complex<double>& lhs, const complex<double>& rhs) {
    if (norm(lhs) != norm(rhs)) {
      return norm(lhs) < norm(rhs);
    } else if (lhs.real() != rhs.real()) {
      return lhs.real() < rhs.real();
    } else {
      return lhs.imag() < rhs.imag();
    }
  }
};

vector<complex<int>> generate_Gaussian_primes(int n) {
  set<complex<double>, ComplexCompare> candidates;
  vector<complex<int>> primes;

  // Generate all possible Gaussian prime candidates.
  for (int i = -n; i <= n; ++i) {
    for (int j = -n; j <= n; ++j) {
      if (!is_unit({i, j}) && abs(complex<double>(i, j)) != 0) {
        candidates.emplace(i, j);
      }
    }
  }

  while (!candidates.empty()) {
    complex<double> p = *(candidates.begin());
    candidates.erase(candidates.begin());
    primes.emplace_back(p);
    int max_multiplier = ceil(sqrt(2.0) * n / floor(sqrt(norm(p))));

    // Any Gaussian integer outside the range we're iterating
    // over below has a modulus greater than max_multiplier.
    for (int i = max_multiplier; i >= -max_multiplier; --i) {
      for (int j = max_multiplier; j >= -max_multiplier; --j) {
        complex<double> x = {static_cast<double>(i), static_cast<double>(j)};
        if (floor(sqrt(norm(x))) > max_multiplier) {
          // Skip multipliers whose modulus exceeds max_multiplier.
          continue;
        }
        if (!is_unit(x)) {
          candidates.erase(x * p);
        }
      }
    }
  }
  return primes;
}

bool is_unit(const complex<int>& z) {
  return (z.real() == 1 && z.imag() == 0) ||
         (z.real() == -1 && z.imag() == 0) ||
         (z.real() == 0 && z.imag() == 1) ||
         (z.real() == 0 && z.imag() == -1);
}
// @exclude

vector<complex<int>> generate_Gaussian_primes_canary(int n) {
  set<complex<double>, ComplexCompare> candidates;
  vector<complex<int>> primes;

  // Generate all possible Gaussian prime candidates.
  for (int i = -n; i <= n; ++i) {
    for (int j = -n; j <= n; ++j) {
      if (!is_unit({i, j}) && abs(complex<double>(i, j)) != 0) {
        candidates.emplace(i, j);
      }
    }
  }

  while (!candidates.empty()) {
    complex<double> p = *(candidates.begin());
    candidates.erase(candidates.begin());
    primes.emplace_back(p);
    int max_multiplier = n;

    for (int i = max_multiplier; i >= -max_multiplier; --i) {
      for (int j = max_multiplier; j >= -max_multiplier; --j) {
        complex<double> x = {static_cast<double>(i), static_cast<double>(j)};
        if (!is_unit(x)) {
          candidates.erase(x * p);
        }
      }
    }
  }
  return primes;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int i = 1; i <= 100; ++i) {
    cout << "i = " << i << endl;
    vector<complex<int>> first = generate_Gaussian_primes_canary(i);
    vector<complex<int>> g_primes = generate_Gaussian_primes(i);
    cout << first.size() << " " << g_primes.size() << endl;
    for (int i = 0; i < first.size(); ++i) {
      if (first[i].real() != g_primes[i].real() ||
          first[i].imag() != g_primes[i].imag()) {
        cout << "(" << first[i].real() << "," << first[i].imag() << ") ";
        cout << "(" << g_primes[i].real() << "," << g_primes[i].imag()
             << ") ";
      }
    }
    for (int i = first.size(); i < g_primes.size(); ++i) {
      cout << "(" << g_primes[i].real() << "," << g_primes[i].imag() << ") ";
    }
    assert(first.size() == g_primes.size());
  }
  return 0;
}
