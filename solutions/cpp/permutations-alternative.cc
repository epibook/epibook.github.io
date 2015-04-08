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
using std::swap;
using std::uniform_int_distribution;
using std::vector;

vector<vector<int>> PermutationsHelper(int i, vector<int> *A);

// @include
vector<vector<int>> Permutations(vector<int> A) {
  return PermutationsHelper(0, &A);
}

vector<vector<int>> PermutationsHelper(int i, vector<int> *A) {
  if (i == A->size() - 1) {
    return {*A};
  }

  vector<vector<int>> result;
  for (int j = i; j < A->size(); ++j) {
    swap((*A)[i], (*A)[j]);
    vector<vector<int>> new_result = PermutationsHelper(i + 1, A);
    result.insert(result.end(), new_result.begin(), new_result.end());
    swap((*A)[i], (*A)[j]);
  }
  return result;
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
  for (int i = 0; i < 6; ++i) {
    assert(EqualVector(result[i], golden_result[i]));
  }
}

int main(int argc, char** argv) {
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
  for (const auto& vec : result) {
    for (int a : vec) {
      cout << a << " ";
    }
    cout << endl;
  }
  return 0;
}
