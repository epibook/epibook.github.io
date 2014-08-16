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
using std::max;
using std::pair;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

vector<int> RandVector(int len) {
  vector<int> ret;
  default_random_engine gen((random_device())());
  while (len--) {
    uniform_int_distribution<int> dis(0, 99);
    ret.emplace_back(dis(gen));
  }
  return ret;
}

// @include
struct Item {
  int weight, value;
};

int Knapsack(int w, const vector<Item>& items) {
  vector<int> V(w + 1, 0);
  for (int i = 0; i < items.size(); ++i) {
    for (int j = w; j >= items[i].weight; --j) {
      V[j] = max(V[j], V[j - items[i].weight] + items[i].value);
    }
  }
  return V[w];
}
// @exclude

void SmallTest() {
  // The example in the book.
  vector<Item> items = {{20, 65},
                        {8, 35},
                        {60, 245},
                        {55, 195},
                        {40, 65},
                        {70, 150},
                        {85, 275},
                        {25, 155},
                        {30, 120},
                        {65, 320},
                        {75, 75},
                        {10, 40},
                        {95, 200},
                        {50, 100},
                        {40, 220},
                        {10, 99}};
  assert(695 == Knapsack(130, items));
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  vector<int> weight, value;
  int n, W;
  if (argc == 1) {
    uniform_int_distribution<int> n_dis(1, 100);
    n = n_dis(gen);
    uniform_int_distribution<int> W_dis(1, 1000);
    W = W_dis(gen);
    weight = RandVector(n), value = RandVector(n);
  } else if (argc == 2) {
    n = atoi(argv[1]);
    uniform_int_distribution<int> W_dis(1, 1000);
    W = W_dis(gen);
    weight = RandVector(n), value = RandVector(n);
  } else {
    n = atoi(argv[1]);
    W = atoi(argv[2]);
    for (int i = 0; i < n; ++i) {
      weight.emplace_back(atoi(argv[3 + i]));
    }
    for (int i = 0; i < n; ++i) {
      value.emplace_back(atoi(argv[3 + i + n]));
    }
  }
  cout << "Weight: ";
  for (int i = 0; i < n; ++i) {
    cout << weight[i] << ' ';
  }
  cout << endl << "Value: ";
  for (int i = 0; i < n; ++i) {
    cout << value[i] << ' ';
  }
  cout << endl;
  vector<Item> items;
  for (int i = 0; i < weight.size(); ++i) {
    items.emplace_back(Item{weight[i], value[i]});
  }
  cout << "Knapsack size = " << W << endl;
  cout << "all value = " << Knapsack(W, items) << endl;
  return 0;
}
