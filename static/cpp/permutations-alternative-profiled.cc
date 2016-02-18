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

void permutations_helper(vector<int> *A, size_t i, vector<vector<int>> *res);

int count=0;

// @include
vector<vector<int>> permutations(vector<int> A) {
  vector<vector<int>> res;
  count = 0;
  permutations_helper(&A, 0, &res);
  cout << "count = " << count << endl;
  return res;
}

void permutations_helper(vector<int> *A, size_t i, vector<vector<int>> *res) {
  count++;
  if (i == A->size()) {
    res->emplace_back(*A);
    return;
  }

  for (size_t j = i; j < A->size(); ++j) {
    swap((*A)[i], (*A)[j]);
    permutations_helper(A, i + 1, res);
    swap((*A)[i], (*A)[j]);
  }
}
// @exclude

template <typename T>
bool equal_vector(const vector<T> &A, const vector<T> &B) {
  return A.size() == B.size() && equal(A.begin(), A.end(), B.begin());
}

void small_test() {
  vector<int> A = {0, 1, 2};
  auto res = permutations(A);
  assert(res.size() == 6);
  vector<vector<int>> golden_res = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 1, 0}, {2, 0, 1}};
  for (size_t i = 0; i < 6; ++i) {
    assert(equal_vector(res[i], golden_res[i]));
  }
}

int main(int argc, char** argv) {
  int n;
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    cout << "Need a value for n" << endl;
  }
  vector<int> A(n);
  iota(A.begin(), A.end(), 0);
  cout << "n = " << n << endl;
  auto res = permutations(A);
  return 0;
}
