// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

void DirectedCombinations(int, int,  int, vector<int>*, vector<vector<int>>*);

// @include
vector<vector<int>> Combinations(int n, int k) {
  vector<vector<int>> result;
  vector<int> partial_combination;
  DirectedCombinations(n, k, 1, &partial_combination, &result);
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
  // kNumRemaining.
  const int kNumRemaining = k - partial_combination->size();
  for (int i = offset; i <= n && kNumRemaining <= n - i - 1;
       ++i) {
    partial_combination->emplace_back(i);
    DirectedCombinations(n, k, i + 1, partial_combination, result);
    partial_combination->pop_back();
  }
}
// @exclude

int main(int argc, char** argv) {
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
  for (const auto& vec : result) {
    for (int a : vec) {
      cout << a << " ";
    }
    cout << endl;
  }
  return 0;
}
