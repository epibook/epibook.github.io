// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::pair;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

// @include
pair<int, int> search_range(const vector<int>& A, int target) {
  auto result = equal_range(A.cbegin(), A.cend(), target);
  if (*result.first != target) {
    return {-1, -1};
  }
  return {distance(A.begin(), result.first),
          distance(A.begin(), result.second - 1)};
}
// @exclude

pair<int, int> check_ans(const vector<int>& A, int target) {
  size_t a = 0;
  while (a < A.size() && A[a] < target) {
    ++a;
  }
  if (A[a] != target) {
    return {-1, -1};
  }
  int b = A.size() - 1;
  while (b >= 0 && A[b] > target) {
    --b;
  }
  return {a, b};
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    size_t n;
    if (argc == 2) {
      n = stoul(argv[1]);
    } else {
      uniform_int_distribution<size_t> n_dis(0, 100000);
      n = n_dis(gen);
    }
    uniform_int_distribution<int> A_dis(0, static_cast<int>(n));
    vector<int> A;
    generate_n(back_inserter(A), n, [&] { return A_dis(gen); });
    sort(A.begin(), A.end());
    /*
    for (int a : A) {
      cout << a << " ";
    }
    cout << endl;
    */
    int target = A_dis(gen);
    // cout << "target = " << target << endl;
    auto result = search_range(A, target), ans = check_ans(A, target);
    /*
    cout << result.first << ", " << result.second << endl;
    cout << ans.first << ", " << ans.second << endl;
    */
    assert(result == ans);
  }
  return 0;
}
