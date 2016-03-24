// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <unordered_map>

using std::cout;
using std::endl;
using std::max;
using std::min;
using std::numeric_limits;
using std::unordered_map;

double compute_best_payoff_helper(
    unordered_map<int, unordered_map<int, unordered_map<int, double>>>& cache,
    double upper_bound, int cash, int num_red, int num_cards);

// @include
double compute_best_payoff(int cash) {
  double upper_bound = 9.09 * cash;
  unordered_map<int, unordered_map<int, unordered_map<int, double>>> cache;
  return compute_best_payoff_helper(cache, upper_bound, cash, 26, 52);
}

double compute_best_payoff_helper(
    unordered_map<int, unordered_map<int, unordered_map<int, double>>>& cache,
    double upper_bound, int cash, int num_red, int num_cards) {
  if (cash >= upper_bound) {
    return cash;
  }

  if (num_red == num_cards || num_red == 0) {
    return cash * pow(2, num_cards);
  }

  if (cache[cash][num_red].find(num_cards) == cache[cash][num_red].end()) {
    double best = numeric_limits<double>::min();
    for (int bet = 0; bet <= cash; ++bet) {
      double red_lower_bound =
          min(compute_best_payoff_helper(cache, upper_bound, cash + bet,
                                         num_red - 1, num_cards - 1),
              compute_best_payoff_helper(cache, upper_bound, cash - bet,
                                         num_red, num_cards - 1));

      double black_lower_bound =
          min(compute_best_payoff_helper(cache, upper_bound, cash - bet,
                                         num_red - 1, num_cards - 1),
              compute_best_payoff_helper(cache, upper_bound, cash + bet,
                                         num_red, num_cards - 1));
      best = max({best, red_lower_bound, black_lower_bound});
    }
    cache[cash][num_red][num_cards] = best;
  }
  return cache[cash][num_red][num_cards];
}
// @exclude

int main(int argc, char* argv[]) {
  int ans = compute_best_payoff(100);
  assert(ans == 808);
  cout << "100 cash can get " << ans << endl;
  return 0;
}
