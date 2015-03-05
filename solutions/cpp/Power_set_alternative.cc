// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <iostream>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

void GeneratePowerSetHelper(const vector<int>&, int, vector<int>*,
                            vector<vector<int>>*);

// @include
vector<vector<int>> GeneratePowerSet(const vector<int>& S) {
  vector<vector<int>> power_set;
  vector<int> one_set;
  GeneratePowerSetHelper(S, 0, &one_set, &power_set);
  return power_set;
}

void GeneratePowerSetHelper(const vector<int>& S, int m,
                            vector<int>* one_set, 
                            vector<vector<int>>* power_set) {
  power_set->emplace_back(*one_set);
  for (int i = m; i < S.size(); ++i) {
    one_set->emplace_back(S[i]);
    GeneratePowerSetHelper(S, i + 1, one_set, power_set);
    one_set->pop_back();
  }
}
// @exclude

void SimpleTest() {
  vector<vector<int>> golden_result = {
      {}, {0}, {0, 1}, {0, 1, 2}, {0, 2}, {1}, {1, 2}, {2}};
  auto result = GeneratePowerSet({0, 1, 2});
  assert(result.size() == golden_result.size() &&
         equal(result.begin(), result.end(), golden_result.begin()));
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
  for (const auto& one_set : power_set) {
    copy(one_set.begin(), one_set.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
  }
  return 0;
}
