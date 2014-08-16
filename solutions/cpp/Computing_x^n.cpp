// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <cassert>
#include <limits>
#include <list>
#include <queue>
#include <random>
#include <stdexcept>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::invalid_argument;
using std::list;
using std::queue;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
list<int> GetMinimumExpression(int n) {
  if (n == 1) {
    return {1};
  }

  queue<list<int>> exp_lists;
  // Constructs the initial list with one node whose value is 1.
  exp_lists.emplace(1, 1);
  while (exp_lists.empty() == false) {
    list<int> exp = exp_lists.front();
    exp_lists.pop();
    // Tries all possible combinations in list exp.
    for (const int& a : exp) {
      int sum = a + exp.back();
      if (sum > n) {
        break;  // No possible solution.
      }

      list<int> new_exp(exp);
      new_exp.emplace_back(sum);
      if (sum == n) {
        return new_exp;
      }
      exp_lists.emplace(new_exp);
    }
  }
  // @exclude
  throw invalid_argument("unknown error");  // This line should never be called.
  // @include
}
// @exclude

void small_test() {
  auto res = GetMinimumExpression(88);
  list<int> golden_res = {1, 2, 3, 4, 7, 11, 22, 44, 88};
  assert(res.size() == golden_res.size());
  assert(equal(res.begin(), res.end(), golden_res.begin()));
  res = GetMinimumExpression(67);
  golden_res = {1, 2, 3, 4, 8, 16, 32, 35, 67};
  assert(res.size() == golden_res.size());
  assert(equal(res.begin(), res.end(), golden_res.begin()));
}

int main(int argc, char* argv[]) {
  small_test();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> n_dis(1, 100);
    n = n_dis(gen);
  }
  cout << "n = " << n << endl;
  list<int> min_exp = GetMinimumExpression(n);
  for (const int& t : min_exp) {
    cout << t << ' ';
  }
  cout << endl;
  cout << "size = " << min_exp.size() << endl;
  return 0;
}
