// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

int ComputeMaximumRevenueForRange(const vector<int>&, int, int,
                                  vector<vector<int>>*);

// @include
int MaximumRevenue(const vector<int>& coins) {
  vector<vector<int>> maximum_revenue_for_range(coins.size(),
                                                vector<int>(coins.size(), 0));
  return ComputeMaximumRevenueForRange(coins, 0, coins.size() - 1,
                                       &maximum_revenue_for_range);
}

int ComputeMaximumRevenueForRange(
    const vector<int>& coins, int a, int b,
    vector<vector<int>>* maximum_revenue_for_range_ptr) {
  if (a > b) {
    // No coins left.
    return 0;
  }

  vector<vector<int>>& maximum_revenue_for_range =
      *maximum_revenue_for_range_ptr;
  if (maximum_revenue_for_range[a][b] == 0) {
    int max_revenue_a =
        coins[a] +
        min(ComputeMaximumRevenueForRange(coins, a + 2, b,
                                          maximum_revenue_for_range_ptr),
            ComputeMaximumRevenueForRange(coins, a + 1, b - 1,
                                          maximum_revenue_for_range_ptr));
    int max_revenue_b =
        coins[b] +
        min(ComputeMaximumRevenueForRange(coins, a + 1, b - 1,
                                          maximum_revenue_for_range_ptr),
            ComputeMaximumRevenueForRange(coins, a, b - 2,
                                          maximum_revenue_for_range_ptr));
    maximum_revenue_for_range[a][b] = max(max_revenue_a, max_revenue_b);
  }
  return maximum_revenue_for_range[a][b];
}
// @exclude

int MaximumRevenueAlternativeHelper(
    const vector<int>& coins, int a, int b, const vector<int>& prefix_sum,
    vector<vector<int>>* maximum_revenue_for_range_ptr) {
  if (a > b) {
    return 0;
  } else if (a == b) {
    return coins[a];
  }

  vector<vector<int>>& maximum_revenue_for_range =
      *maximum_revenue_for_range_ptr;
  if (maximum_revenue_for_range[a][b] == -1) {
    maximum_revenue_for_range[a][b] = max(
        coins[a] + prefix_sum[b] - (a + 1 > 0 ? prefix_sum[a] : 0) -
            MaximumRevenueAlternativeHelper(coins, a + 1, b, prefix_sum,
                                            maximum_revenue_for_range_ptr),
        coins[b] + prefix_sum[b - 1] - (a > 0 ? prefix_sum[a - 1] : 0) -
            MaximumRevenueAlternativeHelper(coins, a, b - 1, prefix_sum,
                                            maximum_revenue_for_range_ptr));
  }
  return maximum_revenue_for_range[a][b];
}

int MaximumRevenueAlternative(const vector<int>& coins) {
  vector<int> prefix_sum;
  partial_sum(coins.begin(), coins.end(), back_inserter(prefix_sum));
  vector<vector<int>> maximum_revenue_for_range(
      coins.size(), vector<int>(coins.size(), -1));
  return MaximumRevenueAlternativeHelper(
      coins, 0, coins.size() - 1, prefix_sum, &maximum_revenue_for_range);
}

int GreedyHelper(const vector<int>& coins, int start, int end) {
  if (start > end) {
    return 0;
  }

  int gain;
  if (coins[start] > coins[end]) {
    gain = coins[start];
    if (coins[start + 1] > coins[end]) {
      gain += GreedyHelper(coins, start + 2, end);
    } else {
      gain += GreedyHelper(coins, start + 1, end - 1);
    }
  } else {
    gain = coins[end];
    if (coins[start] > coins[end - 1]) {
      gain += GreedyHelper(coins, start + 1, end - 1);
    } else {
      gain += GreedyHelper(coins, start, end - 2);
    }
  }
  return gain;
}

int Greedy(const vector<int>& coins) {
  return GreedyHelper(coins, 0, coins.size() - 1);
}

void SimpleTest() {
  vector<int> coins = {25, 5,  10, 5,  10, 5,  10, 25,
                       1,  25, 1,  25, 1,  25, 5,  10};
  assert(140 == MaximumRevenue(coins));
  assert(MaximumRevenueAlternative(coins) == MaximumRevenue(coins));
  assert(120 == Greedy(coins));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  return 0;
  default_random_engine gen((random_device())());
  vector<int> coins;
  if (argc >= 2) {
    for (int i = 1; i < argc; ++i) {
      coins.emplace_back(atoi(argv[i]));
    }
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    coins.resize(dis(gen));
    for (int i = 0; i < coins.size(); ++i) {
      uniform_int_distribution<int> dis(0, 99);
      coins[i] = dis(gen);
    }
  }
  for (size_t i = 0; i < coins.size(); ++i) {
    cout << coins[i] << ' ';
  }
  cout << endl;
  cout << MaximumRevenue(coins) << endl;
  assert(MaximumRevenue(coins) == MaximumRevenueAlternative(coins));
  return 0;
}
