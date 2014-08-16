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

void PermutationsHelper(size_t i, vector<int> *A, vector<vector<int>> *result);

// @include
vector<vector<int>> Permutations(vector<int> A) {
  vector<vector<int>> result;
  PermutationsHelper(0, &A, &result);
  return result;
}

void PermutationsHelper(size_t i, vector<int> *A,
                        vector<vector<int>> *result) {
  if (i == A->size()) {
    result->emplace_back(*A);
    return;
  }

  for (size_t j = i; j < A->size(); ++j) {
    swap((*A)[i], (*A)[j]);
    PermutationsHelper(i + 1, A, result);
    swap((*A)[i], (*A)[j]);
  }
}
// @exclude

template <typename T>
bool EqualVector(const vector<T> &A, const vector<T> &B) {
  return A.size() == B.size() && equal(A.begin(), A.end(), B.begin());
}

void SmallTest() {
  vector<int> A = {0, 1, 2};
  auto result = Permutations(A);
  assert(result.size() == 6);
  vector<vector<int>> golden_result = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 1, 0}, {2, 0, 1}};
  for (size_t i = 0; i < 6; ++i) {
    assert(EqualVector(result[i], golden_result[i]));
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
  for (const auto& vec : result) {
    for (int a : vec) {
      cout << a << " ";
    }
    cout << endl;
  }
  return 0;
}
