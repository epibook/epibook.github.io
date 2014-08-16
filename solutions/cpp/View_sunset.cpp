// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <sstream>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::istringstream;
using std::pair;
using std::random_device;
using std::stringstream;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<pair<int, int>> ExamineBuildingsWithSunset(istringstream* sin) {
  int idx = 0;  // Building's index.
  int height;
  // Stores (building_idx, building_height) pair with sunset views.
  vector<pair<int, int>> buildings_with_sunset;
  while (*sin >> height) {
    while (!buildings_with_sunset.empty() &&
           height >= buildings_with_sunset.back().second) {
      buildings_with_sunset.pop_back();
    }
    buildings_with_sunset.emplace_back(idx++, height);
  }

  // Returns buildings with its index and height.
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
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 2 * n);
      int height = dis(gen);
      ss << height << ' ';
    }
    istringstream sin(ss.str());
    vector<pair<int, int>> res = ExamineBuildingsWithSunset(&sin);
    cout << res[0].first << ' ' << res[0].second << endl;
    for (int i = 1; i < res.size(); ++i) {
      cout << res[i].first << ' ' << res[i].second << endl;
      assert(res[i - 1].first < res[i].first);
      assert(res[i - 1].second > res[i].second);
    }
  }
  return 0;
}
