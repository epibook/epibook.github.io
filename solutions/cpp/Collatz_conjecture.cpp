// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
bool TestCollatzConjecture(int n) {
  // Stores the odd number that converges to 1.
  unordered_set<long> table;

  // Starts from 2 since we don't need to test 1.
  for (int i = 2; i <= n; ++i) {
    unordered_set<long> sequence;
    long test_i = i;
    while (test_i >= i) {
      // We met some number encountered before.
      if (!sequence.emplace(test_i).second) {
        return false;
      }

      if (test_i & 1) {  // Odd number.
        if (!table.emplace(test_i).second) {
          break;  // This number have already be proven to converge to 1.
        }
        long next_test_i = 3 * test_i + 1;  // 3i + 1.
        if (next_test_i <= test_i) {
          throw overflow_error("test process overflow");
        }
        test_i = next_test_i;
      } else {         // Even number.
        test_i >>= 1;  // i / 2.
      }
    }
    table.erase(i);
  }
  return true;
}
// @exclude

// Slow check without any pruning.
bool Check(int n) {
  for (int i = 2; i <= n; ++i) {
    int test_i = i;
    while (test_i != 1 && test_i >= i) {
      if (test_i & 1) {
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
