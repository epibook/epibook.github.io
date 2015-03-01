// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> NextPermutation(vector<int> p) {
  int k = p.size() - 2;
  while (k >= 0 && p[k] >= p[k + 1]) {
    --k;
  }
  if (k == -1) {
    return {};  // p is the last permutation.
  }

  // Swap the smallest entry after index k that is greater than p[k].
  // We exploit the fact that p[k + 1 : p.size() - 1] is decreasing so if we
  // search in reverse order, the first entry that is greater than p[k] is
  // the smallest such entry.
  for (int i = p.size() - 1; i > k; --i) {
    if (p[i] > p[k]) {
      swap(p[k], p[i]);
      break;
    }
  }

  // Since p[k + 1 : p.size() - 1] is in decreasing order, we can build the
  // smallest dictionary ordering of this subarray by reversing it.
  reverse(p.begin() + k + 1, p.end());
  return p;
}
// @exclude

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    vector<int> p;
    if (argc > 2) {
      for (size_t i = 1; i < argc; ++i) {
        p.emplace_back(atoi(argv[i]));
      }
    } else {
      uniform_int_distribution<int> dis(1, 100);
      int n = (argc == 2 ? atoi(argv[1]) : dis(gen));
      uniform_int_distribution<int> n_dis(0, n - 1);
      generate_n(back_inserter(p), n, [&] { return n_dis(gen); });
    }

    vector<int> ans(NextPermutation(p));
    // Use built-in function verification.
    bool has_next_one = next_permutation(p.begin(), p.end());
    assert((ans.size() == 0 && !has_next_one) ||
           equal(ans.cbegin(), ans.cend(), p.cbegin()));
  }
  return 0;
}
