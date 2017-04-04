// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::uniform_real_distribution;
using std::vector;

// @include
double BuyAndSellStockOnce(const vector<double>& prices) {
  double min_price_so_far = numeric_limits<double>::max(), max_profit = 0;
  for (const double& price : prices) {
    double max_profit_sell_today = price - min_price_so_far;
    max_profit = max(max_profit, max_profit_sell_today);
    min_price_so_far = min(min_price_so_far, price);
  }
  return max_profit;
}
// @exclude

// O(n^2) checking answer.
double CheckAns(const vector<double>& h) {
  double cap = 0;
  for (int i = 1; i < h.size(); ++i) {
    for (int j = 0; j < i; ++j) {
      cap = max(cap, h[i] - h[j]);
    }
  }
  return cap;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<double> A;
    uniform_real_distribution<double> dis(0, n);
    for (int i = 0; i < n; ++i) {
      A.emplace_back(dis(gen));
    }
    cout << BuyAndSellStockOnce(A) << endl;
    assert(CheckAns(A) == BuyAndSellStockOnce(A));
  }
  return 0;
}
