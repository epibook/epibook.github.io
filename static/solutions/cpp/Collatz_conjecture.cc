// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <stdexcept>
#include <unordered_set>

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::overflow_error;
using std::random_device;
using std::to_string;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
bool TestCollatzConjecture(int n) {
  // Stores odd numbers already tested to converge to 1.
  unordered_set<long> verified_numbers;

  // Starts from 3, since hypothesis holds trivially for 1 and 2.
  for (int i = 3; i <= n; i += 2) {
    unordered_set<long> sequence;
    long test_i = i;
    while (test_i >= i) {
      if (!sequence.emplace(test_i).second) {
        // We previously encountered test_i, so the Collatz sequence
        // has fallen into a loop. This disproves the hypothesis, so
        // we short-circuit, returning false.
        return false;
      }

      if (test_i % 2) {  // Odd number.
        if (!verified_numbers.emplace(test_i).second) {
          break;  // test_i has already been verified to converge to 1.
        }
        long next_test_i = 3 * test_i + 1;  // Multiply by 3 and add 1.
        if (next_test_i <= test_i) {
          throw overflow_error("Collatz sequence overflow for " +
                               to_string(i));
        }
        test_i = next_test_i;
      } else {
        test_i /= 2;  // Even number, halve it.
      }
    }
  }
  return true;
}
// @exclude

// Slow check without any pruning.
bool Check(int n) {
  for (int i = 2; i <= n; ++i) {
    int test_i = i;
    while (test_i != 1 && test_i >= i) {
      if (test_i % 2) {
        test_i = test_i * 3 + 1;
      } else {
        test_i >>= 1;
      }
    }
  }
  return true;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 100000);
      n = dis(gen);
    }
    cout << "n = " << n << endl;
    bool res;
    cout << boolalpha << (res = TestCollatzConjecture(n)) << endl;
    assert(res == Check(n));
  }
  return 0;
}
