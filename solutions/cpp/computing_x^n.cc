// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
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
vector<int> ShortestAdditionChain(int n) {
  if (n == 1) {
    return {1};
  }

  queue<vector<int>> addition_chains;
  // Constructs the initial addition_chain with one node whose value is 1.
  addition_chains.emplace(1, 1);
  while (!addition_chains.empty()) {
    vector<int> candidate_addition_chain = addition_chains.front();
    addition_chains.pop();
    // Tries all possible combinations in candidate_addition_chain.
    for (int a : candidate_addition_chain) {
      int power = a + candidate_addition_chain.back();
      if (power > n) {
        break;  // No possible solution for candidate_addition_chain.
      }
      vector<int> new_addition_chain(candidate_addition_chain);
      new_addition_chain.emplace_back(power);

      if (power == n) {
        return new_addition_chain;
      }
      addition_chains.emplace(new_addition_chain);
    }
  }
  // @exclude
  throw invalid_argument(
      "unknown error");  // This line should never be called.
  // @include
}
// @exclude

void SmallTest() {
  auto res = ShortestAdditionChain(88);
  vector<int> golden_res = {1, 2, 3, 4, 7, 11, 22, 44, 88};
  assert(equal(res.begin(), res.end(), golden_res.begin(), golden_res.end()));
  res = ShortestAdditionChain(67);
  golden_res = {1, 2, 3, 4, 8, 16, 32, 35, 67};
  assert(res.size() == golden_res.size());
  assert(equal(res.begin(), res.end(), golden_res.begin(), golden_res.end()));
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
  auto min_exp = ShortestAdditionChain(n);
  for (int t : min_exp) {
    cout << t << ' ';
  }
  cout << endl;
  cout << "size = " << min_exp.size() << endl;
  return 0;
}
