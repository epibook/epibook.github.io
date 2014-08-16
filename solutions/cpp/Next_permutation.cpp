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

  // Find the smallest entry after index k that is bigger than p[k].
  int l;
  for (int i = k + 1; i < p.size(); ++i) {
    if (p[i] > p[k]) {
      l = i;
    } else {
      // Since p[k+1], p[k+2], ... is decreasing, p[l] must be the result.
      break;
    }
  }
  swap(p[k], p[l]);

  // Reversing this subarray sorts it in increasing order.
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
    assert((ans.size() == 0 && !has_next_one) || equal(ans.cbegin(), ans.cend(), p.cbegin()));
  }
  return 0;
}
