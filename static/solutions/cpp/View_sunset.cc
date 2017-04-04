// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <sstream>
#include <stack>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::istringstream;
using std::random_device;
using std::stack;
using std::stringstream;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> ExamineBuildingsWithSunset(istringstream* sin) {
  int building_idx = 0, building_height;
  struct BuildingWithHeight {
    int id, height;
  };
  stack<BuildingWithHeight> candidates;
  while (*sin >> building_height) {
    while (!candidates.empty() &&
           building_height >= candidates.top().height) {
      candidates.pop();
    }
    candidates.emplace(BuildingWithHeight{building_idx++, building_height});
  }

  vector<int> buildings_with_sunset;
  while (!candidates.empty()) {
    buildings_with_sunset.emplace_back(candidates.top().id);
    candidates.pop();
  }
  return buildings_with_sunset;
}
// @exclude

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
    stringstream ss;
    vector<int> heights;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 2 * n);
      int height = dis(gen);
      ss << height << ' ';
      heights.emplace_back(height);
    }
    istringstream sin(ss.str());
    auto res = ExamineBuildingsWithSunset(&sin);
    cout << res[0] << ' ' << heights[res[0]] << endl;
    for (int i = 1; i < res.size(); ++i) {
      cout << res[i] << ' ' << heights[res[i]] << endl;
      assert(heights[res[i - 1]] < heights[res[i]]);
    }
  }
  return 0;
}
