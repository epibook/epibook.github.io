// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int CountCombinations(int total_score, const vector<int>& score_ways) {
  vector<int> combinations(total_score + 1, 0);
  combinations[0] = 1;  // One way to reach 0.
  for (int score : score_ways) {
    for (int j = score; j <= total_score; ++j) {
      combinations[j] += combinations[j - score];
    }
  }
  return combinations[total_score];
}
// @exclude

void SimpleTest() {
  vector<int> score_ways = {2, 3, 7};
  assert(4 == CountCombinations(12, score_ways));
  assert(1 == CountCombinations(5, score_ways));
  assert(3 == CountCombinations(9, score_ways));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  int k;
  vector<int> score_ways;
  if (argc == 1) {
    uniform_int_distribution<int> k_dis(0, 999);
    k = k_dis(gen);
    uniform_int_distribution<int> size_dis(1, 50);
    score_ways.resize(size_dis(gen));
    for (int i = 0; i < score_ways.size(); ++i) {
      uniform_int_distribution<int> score_dis(1, 1000);
      score_ways[i] = score_dis(gen);
    }
  } else if (argc == 2) {
    k = atoi(argv[1]);
    uniform_int_distribution<int> size_dis(1, 50);
    score_ways.resize(size_dis(gen));
    for (int i = 0; i < score_ways.size(); ++i) {
      uniform_int_distribution<int> score_dis(1, 1000);
      score_ways[i] = score_dis(gen);
    }
  } else {
    k = atoi(argv[1]);
    for (int i = 2; i < argc; ++i) {
      score_ways.emplace_back(atoi(argv[i]));
    }
  }
  cout << CountCombinations(k, score_ways) << endl;
  return 0;
}
