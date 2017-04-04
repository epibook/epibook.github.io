// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_unique;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

void DirectedCombinations(int, int, int, vector<int>*, vector<vector<int>>*);

// @include
vector<vector<int>> Combinations(int n, int k) {
  vector<vector<int>> result;
  DirectedCombinations(n, k, 1, make_unique<vector<int>>().get(), &result);
  return result;
}

void DirectedCombinations(int n, int k, int offset,
                          vector<int>* partial_combination,
                          vector<vector<int>>* result) {
  if (partial_combination->size() == k) {
    result->emplace_back(*partial_combination);
    return;
  }

  // Generate remaining combinations over {offset, ..., n - 1} of size
  // num_remaining.
  const int num_remaining = k - partial_combination->size();
  for (int i = offset; i <= n && num_remaining <= n - i + 1; ++i) {
    partial_combination->emplace_back(i);
    DirectedCombinations(n, k, i + 1, partial_combination, result);
    partial_combination->pop_back();
  }
}
// @exclude

void SmallTest() {
  auto result = Combinations(4, 2);
  vector<vector<int>> golden_result = {{1, 2}, {1, 3}, {1, 4},
                                       {2, 3}, {2, 4}, {3, 4}};
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end()));
}

int main(int argc, char** argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  int n, k;
  if (argc == 3) {
    n = stoi(argv[1]), k = stoi(argv[2]);
  } else {
    uniform_int_distribution<int> n_dis(1, 10);
    n = n_dis(gen);
    uniform_int_distribution<int> k_dis(0, n);
    k = k_dis(gen);
  }
  auto result = Combinations(n, k);
  cout << "n = " << n << ", k = " << k << endl;
  for (const vector<int>& vec : result) {
    for (int a : vec) {
      cout << a << " ";
    }
    cout << endl;
  }
  return 0;
}
