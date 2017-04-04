// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<vector<int>> Permutations(vector<int> A) {
  vector<vector<int>> result;
  // Generate the first permutation in dictionary order.
  sort(A.begin(), A.end());
  do {
    result.emplace_back(A);
  } while (next_permutation(A.begin(), A.end()));
  return result;
}
// @exclude

void SmallTest() {
  vector<int> A = {1, 2, 3};
  auto result = Permutations(A);
  assert(result.size() == 6);
  vector<vector<int>> golden_result = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3},
                                       {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};
  for (size_t i = 0; i < 6; ++i) {
    assert(equal(result[i].begin(), result[i].end(), golden_result[i].begin(),
                 golden_result[i].end()));
  }
}

int main(int argc, char** argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  size_t n;
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    uniform_int_distribution<size_t> dis(1, 10);
    n = dis(gen);
  }
  vector<int> A(n);
  iota(A.begin(), A.end(), 0);
  auto result = Permutations(A);
  cout << "n = " << n << endl;
  for (const vector<int>& vec : result) {
    for (int a : vec) {
      cout << a << " ";
    }
    cout << endl;
  }
  return 0;
}
