// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
int NumCombinationsForFinalScore(int final_score,
                                 const vector<int>& individual_play_scores) {
  vector<vector<int>> num_combinations_for_score(
      individual_play_scores.size(), vector<int>(final_score + 1, 0));
  for (int i = 0; i < individual_play_scores.size(); ++i) {
    num_combinations_for_score[i][0] = 1;  // One way to reach 0.
    for (int j = 1; j <= final_score; ++j) {
      int without_this_play =
          i >= 1 ? num_combinations_for_score[i - 1][j] : 0;
      int with_this_play =
          j >= individual_play_scores[i]
              ? num_combinations_for_score[i][j - individual_play_scores[i]]
              : 0;
      num_combinations_for_score[i][j] = without_this_play + with_this_play;
    }
  }
  return num_combinations_for_score.back().back();
}
// @exclude

void SimpleTest() {
  vector<int> individual_play_scores = {2, 3, 7};
  assert(4 == NumCombinationsForFinalScore(12, individual_play_scores));
  assert(1 == NumCombinationsForFinalScore(5, individual_play_scores));
  assert(3 == NumCombinationsForFinalScore(9, individual_play_scores));
}

int CheckAnswer(int total_score, const vector<int>& score_ways) {
  vector<int> combinations(total_score + 1, 0);
  combinations[0] = 1;  // One way to reach 0.
  for (int score : score_ways) {
    for (int j = score; j <= total_score; ++j) {
      combinations[j] += combinations[j - score];
    }
  }
  return combinations[total_score];
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  int k;
  vector<int> individual_play_scores;
  if (argc == 1) {
    uniform_int_distribution<int> k_dis(0, 999);
    k = k_dis(gen);
    uniform_int_distribution<int> size_dis(1, 50);
    individual_play_scores.resize(size_dis(gen));
    for (int i = 0; i < individual_play_scores.size(); ++i) {
      uniform_int_distribution<int> score_dis(1, 1000);
      individual_play_scores[i] = score_dis(gen);
    }
  } else if (argc == 2) {
    k = atoi(argv[1]);
    uniform_int_distribution<int> size_dis(1, 50);
    individual_play_scores.resize(size_dis(gen));
    for (int i = 0; i < individual_play_scores.size(); ++i) {
      uniform_int_distribution<int> score_dis(1, 1000);
      individual_play_scores[i] = score_dis(gen);
    }
  } else {
    k = atoi(argv[1]);
    for (int i = 2; i < argc; ++i) {
      individual_play_scores.emplace_back(atoi(argv[i]));
    }
  }
  cout << NumCombinationsForFinalScore(k, individual_play_scores) << endl;
  assert(NumCombinationsForFinalScore(k, individual_play_scores) ==
         CheckAnswer(k, individual_play_scores));
  return 0;
}
