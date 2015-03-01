// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <cassert>
#include <limits>
#include <queue>
#include <random>
#include <stdexcept>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::invalid_argument;
using std::queue;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> GetShortestStraightLineProgram(int n) {
  if (n == 1) {
    return {1};
  }

  queue<vector<int>> SLPs;  // SLP is acronym for straight line program.
  // Constructs the initial SLP with one node whose value is 1.
  SLPs.emplace(1, 1);
  while (!SLPs.empty()) {
    vector<int> candidate_SLP = SLPs.front();
    SLPs.pop();
    // Tries all possible combinations in candidate_SLP.
    for (const int& a : candidate_SLP) {
      int power = a + candidate_SLP.back();
      if (power > n) {
        break;  // No possible solution for candidate_SLP.
      }
      vector<int> new_SLP(candidate_SLP);
      new_SLP.emplace_back(power);

      if (power == n) {
        return new_SLP;
      }
      SLPs.emplace(new_SLP);
    }
  }
  // @exclude
  throw invalid_argument("unknown error");  // This line should never be called.
  // @include
}
// @exclude

void SmallTest() {
  auto res = GetShortestStraightLineProgram(88);
  vector<int> golden_res = {1, 2, 3, 4, 7, 11, 22, 44, 88};
  assert(res.size() == golden_res.size());
  assert(equal(res.begin(), res.end(), golden_res.begin()));
  res = GetShortestStraightLineProgram(67);
  golden_res = {1, 2, 3, 4, 8, 16, 32, 35, 67};
  assert(res.size() == golden_res.size());
  assert(equal(res.begin(), res.end(), golden_res.begin()));
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> n_dis(1, 100);
    n = n_dis(gen);
  }
  cout << "n = " << n << endl;
  auto min_exp = GetShortestStraightLineProgram(n);
  for (const int& t : min_exp) {
    cout << t << ' ';
  }
  cout << endl;
  cout << "size = " << min_exp.size() << endl;
  return 0;
}
