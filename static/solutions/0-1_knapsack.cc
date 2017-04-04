// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
using std::min;
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

struct Item;
int OptimumSubjectToItemAndCapacity(const vector<Item>&, int, int,
                                    vector<vector<int>>*);

// @include
struct Item {
  int weight, value;
};

int OptimumSubjecToCapacity(const vector<Item>& items, int capacity) {
  // V[i][j] holds the optimum value when we choose from items[0 : i] and have
  // a capacity of j.
  vector<vector<int>> V(items.size(), vector<int>(capacity + 1, -1));
  return OptimumSubjectToItemAndCapacity(items, items.size() - 1, capacity,
                                         &V);
}

// Returns the optimum value when we choose from items[0 : k] and have a
// capacity of available_capacity.
int OptimumSubjectToItemAndCapacity(const vector<Item>& items, int k,
                                    int available_capacity,
                                    vector<vector<int>>* V_ptr) {
  if (k < 0) {
    // No items can be chosen.
    return 0;
  }

  vector<vector<int>>& V = *V_ptr;
  if (V[k][available_capacity] == -1) {
    int without_curr_item = OptimumSubjectToItemAndCapacity(
        items, k - 1, available_capacity, V_ptr);
    int with_curr_item =
        available_capacity < items[k].weight
            ? 0
            : items[k].value + OptimumSubjectToItemAndCapacity(
                                   items, k - 1,
                                   available_capacity - items[k].weight,
                                   V_ptr);
    V[k][available_capacity] = max(without_curr_item, with_curr_item);
  }
  return V[k][available_capacity];
}
// @exclude

void SmallTest() {
  // The example in the book.
  vector<Item> items = {{20, 65},  {8, 35},   {60, 245}, {55, 195},
                        {40, 65},  {70, 150}, {85, 275}, {25, 155},
                        {30, 120}, {65, 320}, {75, 75},  {10, 40},
                        {95, 200}, {50, 100}, {40, 220}, {10, 99}};
  assert(695 == OptimumSubjecToCapacity(items, 130));

  items = {{5, 60}, {3, 50}, {4, 70}, {2, 30}};
  assert(80 == OptimumSubjecToCapacity(items, 5));
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
  cout << "all value = " << OptimumSubjecToCapacity(items, W) << endl;
  return 0;
}
