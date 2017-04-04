// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <array>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

using std::array;
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
double BuyAndSellStockTwice(const vector<double>& prices) {
  double max_total_profit = 0;
  vector<double> first_buy_sell_profits(prices.size(), 0);
  double min_price_so_far = numeric_limits<double>::max();

  // Forward phase. For each day, we record maximum profit if we
  // sell on that day.
  for (int i = 0; i < prices.size(); ++i) {
    min_price_so_far = min(min_price_so_far, prices[i]);
    max_total_profit = max(max_total_profit, prices[i] - min_price_so_far);
    first_buy_sell_profits[i] = max_total_profit;
  }

  // Backward phase. For each day, find the maximum profit if we make
  // the second buy on that day.
  double max_price_so_far = numeric_limits<double>::min();
  for (int i = prices.size() - 1; i > 0; --i) {
    max_price_so_far = max(max_price_so_far, prices[i]);
    max_total_profit =
        max(max_total_profit,
            max_price_so_far - prices[i] + first_buy_sell_profits[i - 1]);
  }
  return max_total_profit;
}
// @exclude

double BuyAndSellStockTwiceConstantSpace(const vector<double>& prices) {
  array<double, 2> min_prices = {numeric_limits<double>::max(),
                                 numeric_limits<double>::max()},
                   max_profits = {0.0, 0.0};
  for (const double& price : prices) {
    for (int i = 1; i >= 0; --i) {
      max_profits[i] = max(max_profits[i], price - min_prices[i]);
      min_prices[i] =
          min(min_prices[i], price - (i - 1 >= 0 ? max_profits[i - 1] : 0.0));
    }
  }
  return max_profits[1];
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 100);
      n = dis(gen);
    }
    vector<double> A;
    uniform_real_distribution<double> dis(0.0, 10000.0);
    for (int i = 0; i < n; ++i) {
      A.emplace_back(dis(gen));
    }
    assert(fabs(BuyAndSellStockTwiceConstantSpace(A) -
                BuyAndSellStockTwice(A)) < 1.0e-8);
  }
  return 0;
}
