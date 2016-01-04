// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

void SubsetsUniqueHelper(const vector<int>&, size_t, vector<int>*,
                         vector<vector<int>>*);

// @include
vector<vector<int>> SubsetsUnique(vector<int> A) {
  vector<vector<int>> res;
  vector<int> ans;

  sort(A.begin(), A.end());
  SubsetsUniqueHelper(A, 0, &ans, &res);
  return res;
}

void SubsetsUniqueHelper(const vector<int>& A, size_t start, vector<int>* ans,
                         vector<vector<int>>* res) {
  res->emplace_back(*ans);
  for (size_t i = start; i < A.size(); ++i) {
    if (i != start && A[i - 1] == A[i]) {
      continue;
    }
    ans->emplace_back(A[i]);
    SubsetsUniqueHelper(A, i + 1, ans, res);
    ans->pop_back();
  }
}
// @exclude

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  size_t n;
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    uniform_int_distribution<size_t> dis(1, 5);
    n = dis(gen);
  }
  // Creates A with duplicate elements.
  vector<int> A;
  for (size_t i = 0; i < (n * 2); ++i) {
    A.emplace_back(static_cast<int>(i)), A.emplace_back(static_cast<int>(i));
  }
  cout << "n = " << n << endl;
  auto res = SubsetsUnique(A);
  for (const vector<int>& vec : res) {
    copy(vec.cbegin(), vec.cend(), ostream_iterator<int>(cout, " "));
    cout << endl;
  }
  return 0;
}
