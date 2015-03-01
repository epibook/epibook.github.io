// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::pair;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
size_t FindStartCity(const vector<int>& G, const vector<int>& D) {
  int carry = 0;
  pair<size_t, int> city_carry_pair(0, 0);
  for (size_t i = 1; i < G.size(); ++i) {
    carry += G[i - 1] - D[i - 1];
    if (carry < city_carry_pair.second) {
      city_carry_pair = {i, carry};
    }
  }
  return city_carry_pair.first;
}
// @exclude

void CheckAns(const vector<int>& G, const vector<int>& D, size_t c) {
  size_t s = c;
  int gas = 0;
  do {
    gas += G[s] - D[s];
    assert(gas >= 0);
    s = (s + 1) % G.size();
  } while (s != c);
}

void SmallTest() {
  // Example in the book.
  vector<int> G = {20, 15, 15, 15, 35, 25, 30, 15, 65, 45, 10, 45, 25};
  vector<int> D = {15, 20, 50, 15, 15, 30, 20, 55, 20, 50, 10, 15, 15};
  size_t ans = FindStartCity(G, D);
  assert(ans == 8);
  CheckAns(G, D, ans);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<int> G, D;
    int sum = 0;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 200);
      int x = dis(gen);
      sum += x;
      G.emplace_back(x);
    }
    sum -= n;
    for (int i = 0; i < n; ++i) {
      int x = 0;
      if (sum) {
        uniform_int_distribution<int> dis(1, sum);
        x = dis(gen);
      }
      D.emplace_back(x + 1);
      sum -= x;
    }
    D.back() += sum;
    /*
    for (int i = 0; i < n; ++i) {
      cout << G[i] << ' ';
    }
    cout << endl;
    for (int i = 0; i < n; ++i) {
      cout << D[i] << ' ';
    }
    cout << endl;
    */
    size_t c = FindStartCity(G, D);
    cout << "start city = " << c << endl;
    CheckAns(G, D, c);
  }
  return 0;
}
