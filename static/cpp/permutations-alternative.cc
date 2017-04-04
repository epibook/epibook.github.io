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
using std::stoul;
using std::swap;
using std::uniform_int_distribution;
using std::vector;

void DirectedPermutations(int, vector<int> *, vector<vector<int>> *);

// @include
vector<vector<int>> Permutations(vector<int> A) {
  vector<vector<int>> result;
  DirectedPermutations(0, &A, &result);
  return result;
}

void DirectedPermutations(int i, vector<int> *A_ptr,
                          vector<vector<int>> *result) {
  vector<int> &A = *A_ptr;
  if (i == A.size() - 1) {
    result->emplace_back(A);
    return;
  }

  // Try every possibility for A[i].
  for (int j = i; j < A.size(); ++j) {
    swap(A[i], A[j]);
    // Generate all permutations for A[i + 1 : A.size() - 1].
    DirectedPermutations(i + 1, A_ptr, result);
    swap(A[i], A[j]);
  }
}
// @exclude

void SmallTest() {
  vector<int> A = {0, 1, 2};
  auto result = Permutations(A);
  assert(result.size() == 6);
  vector<vector<int>> golden_result = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2},
                                       {1, 2, 0}, {2, 1, 0}, {2, 0, 1}};
  for (int i = 0; i < 6; ++i) {
    assert(equal(result[i].begin(), result[i].end(), golden_result[i].begin(),
                 golden_result[i].end()));
  }
}

int main(int argc, char **argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 10);
    n = dis(gen);
  }
  vector<int> A(n);
  iota(A.begin(), A.end(), 0);
  auto result = Permutations(A);
  cout << "n = " << n << endl;
  for (const auto &vec : result) {
    for (int a : vec) {
      cout << a << " ";
    }
    cout << endl;
  }
  return 0;
}
