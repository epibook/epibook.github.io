// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

void permutations_unique_helper(const vector<int> &A, deque<bool> *used,
                                vector<int> *ans,
                                vector<vector<int>> *result);

// @include
vector<vector<int>> permutations_unique(vector<int> A) {
  vector<vector<int>> result;
  deque<bool> used(A.size(), false);
  vector<int> ans;

  sort(A.begin(), A.end());
  permutations_unique_helper(A, &used, &ans, &result);
  return result;
}

void permutations_unique_helper(const vector<int> &A, deque<bool> *used,
                                vector<int> *ans,
                                vector<vector<int>> *result) {
  if (ans->size() == A.size()) {
    result->emplace_back(*ans);
    return;
  }

  for (size_t i = 0; i < A.size(); ++i) {
    if ((*used)[i] || (i != 0 && A[i - 1] == A[i] && (*used)[i - 1])) {
      continue;
    }

    (*used)[i] = true;
    ans->emplace_back(A[i]);
    permutations_unique_helper(A, used, ans, result);
    ans->pop_back();
    (*used)[i] = false;
  }
}
// @exclude

void small_test() {
  vector<int> A = {0, 0, 1, 1};
  auto result = permutations_unique(A);
  assert(result.size() == 6);
  vector<vector<int>> golden_result = {{0, 0, 1, 1}, {0, 1, 0, 1},
                                       {0, 1, 1, 0}, {1, 0, 0, 1},
                                       {1, 0, 1, 0}, {1, 1, 0, 0}};
  for (size_t i = 0; i < 6; ++i) {
    assert(equal(result[i].begin(), result[i].end(), golden_result[i].begin(),
                 golden_result[i].end()));
  }
}

int main(int argc, char **argv) {
  small_test();
  default_random_engine gen((random_device())());
  size_t n;
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    uniform_int_distribution<size_t> dis(1, 5);
    n = dis(gen);
  }
  // Creates A with two duplicates per value from 0 to n-1
  vector<int> A;
  for (size_t i = 0; i < (n * 2); ++i) {
    A.emplace_back(static_cast<int>(i)), A.emplace_back(static_cast<int>(i));
  }
  cout << "n = " << n << ", A = ";
  for (int a : A) {
    cout << a << " ";
  }
  cout << endl;
  auto result = permutations_unique(A);
  for (const auto &vec : result) {
    for (int a : vec) {
      cout << a << " ";
    }
    cout << endl;
  }
  return 0;
}
