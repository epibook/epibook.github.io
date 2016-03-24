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

// O(n^4) checking answer.
double CheckAns(const vector<double>& A) {
  double cap = 0;
  for (int i = 1; i < A.size(); ++i) {
    for (int j = 0; j < i; ++j) {
      cap = max(cap, A[i] - A[j]);
    }
  }
  for (int a = 0; a < A.size(); ++a) {
    for (int b = a + 1; b < A.size(); ++b) {
      double temp = A[b] - A[a];
      for (int c = b + 1; c < A.size(); ++c) {
        for (int d = c + 1; d < A.size(); ++d) {
          double profit = A[d] - A[c] + temp;
          cap = max(cap, profit);
        }
      }
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
      uniform_int_distribution<int> dis(1, 100);
      n = dis(gen);
    }
    vector<double> A;
    uniform_real_distribution<double> dis(0.0, 10000.0);
    for (int i = 0; i < n; ++i) {
      A.emplace_back(dis(gen));
    }
    cout << "n = " << n << endl;
    assert(CheckAns(A) == BuyAndSellStockTwice(A));
  }
  return 0;
}
