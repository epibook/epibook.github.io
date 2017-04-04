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
  // Find the first entry from the right that is smaller than the entry
  // immediately after it.
  auto inversion_point = is_sorted_until(perm.rbegin(), perm.rend());
  if (inversion_point == perm.rend()) {
    // perm is sorted in decreasing order, so it's the last permutation.
    return {};
  }

  // Swap the entry referenced by inversion_point with smallest entry
  // appearing after inversion_point that is greater than the entry referenced
  // by inversion_point:
  //
  // 1.) Find the smallest entry after inversion_point that's greater than the
  //     entry referenced by inversion_point. Since perm must be sorted in
  //     decreasing order after inversion_point, we can use a fast algorithm
  //     to find this entry.
  auto least_upper_bound =
      upper_bound(perm.rbegin(), inversion_point, *inversion_point);

  // 2.) Perform the swap.
  iter_swap(inversion_point, least_upper_bound);

  // Reverse the subarray that follows inversion_point.
  reverse(perm.rbegin(), inversion_point);
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
           equal(ans.cbegin(), ans.cend(), perm.cbegin(), perm.cend()));
  }
  return 0;
}
