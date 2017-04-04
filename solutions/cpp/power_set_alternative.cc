// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <iostream>
#include <memory>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_unique;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

void DirectedPowerSet(const vector<int>&, int, vector<int>*,
                      vector<vector<int>>*);

// @include
vector<vector<int>> GeneratePowerSet(const vector<int>& input_set) {
  vector<vector<int>> power_set;
  DirectedPowerSet(input_set, 0, make_unique<vector<int>>().get(),
                   &power_set);
  return power_set;
}

// Generate all subsets whose intersection with input_set[0], ...,
// input_set[to_be_selected - 1] is exactly selected_so_far.
void DirectedPowerSet(const vector<int>& input_set, int to_be_selected,
                      vector<int>* selected_so_far,
                      vector<vector<int>>* power_set) {
  if (to_be_selected == input_set.size()) {
    power_set->emplace_back(*selected_so_far);
    return;
  }
  // Generate all subsets that contain input_set[to_be_selected].
  selected_so_far->emplace_back(input_set[to_be_selected]);
  DirectedPowerSet(input_set, to_be_selected + 1, selected_so_far, power_set);
  // Generate all subsets that do not contain input_set[to_be_selected].
  selected_so_far->pop_back();
  DirectedPowerSet(input_set, to_be_selected + 1, selected_so_far, power_set);
}
// @exclude

void SimpleTest() {
  vector<vector<int>> golden_result = {{0, 1, 2}, {0, 1}, {0, 2}, {0},
                                       {1, 2},    {1},    {2},    {}};
  auto result = GeneratePowerSet({0, 1, 2});
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end()));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  vector<int> S;
  if (argc >= 2) {
    for (int i = 1; i < argc; ++i) {
      S.emplace_back(atoi(argv[i]));
    }
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(1, 10);
    S.resize(dis(gen));
    iota(S.begin(), S.end(), 0);
  }
  auto power_set = GeneratePowerSet(S);
  for (const vector<int>& one_set : power_set) {
    copy(one_set.begin(), one_set.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
  }
  return 0;
}
