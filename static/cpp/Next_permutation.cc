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
vector<int> NextPermutation(vector<int> perm) {
  int k = perm.size() - 2;
  while (k >= 0 && perm[k] >= perm[k + 1]) {
    --k;
  }
  if (k == -1) {
    return {};  // perm is the last permutation.
  }

  // Swap the smallest entry after index k that is greater than perm[k]. We
  // exploit the fact that perm[k + 1 : perm.size() - 1] is decreasing so if we
  // search in reverse order, the first entry that is greater than perm[k] is
  // the smallest such entry.
  for (int i = perm.size() - 1; i > k; --i) {
    if (perm[i] > perm[k]) {
      swap(perm[k], perm[i]);
      break;
    }
  }

  // Since perm[k + 1 : perm.size() - 1] is in decreasing order, we can build
  // the smallest dictionary ordering of this subarray by reversing it.
  reverse(perm.begin() + k + 1, perm.end());
  return perm;
}
// @exclude

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    vector<int> perm;
    if (argc > 2) {
      for (size_t i = 1; i < argc; ++i) {
        perm.emplace_back(atoi(argv[i]));
      }
    } else {
      uniform_int_distribution<int> dis(1, 100);
      int n = (argc == 2 ? atoi(argv[1]) : dis(gen));
      uniform_int_distribution<int> n_dis(0, n - 1);
      generate_n(back_inserter(perm), n, [&] { return n_dis(gen); });
    }

    vector<int> ans(NextPermutation(perm));
    // Use built-in function verification.
    bool has_next_one = next_permutation(perm.begin(), perm.end());
    assert((ans.size() == 0 && !has_next_one) ||
           equal(ans.cbegin(), ans.cend(), perm.cbegin()));
  }
  return 0;
}
