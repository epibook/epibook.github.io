// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <bitset>
#include <cassert>
#include <fstream>
#include <iostream>
#include <iterator>
#include <map>
#include <random>
#include <set>
#include <stdexcept>
#include <string>
#include <unordered_map>
#include <vector>

using std::advance;
using std::bitset;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::ifstream;
using std::invalid_argument;
using std::ios;
using std::map;
using std::multiset;
using std::ofstream;
using std::random_device;
using std::stoi;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

int GetTopThreeScoresSum(const multiset<int>& scores);

// @include
string FindStudentWithHighestBestOfThreeScores(ifstream* ifs) {
  unordered_map<string, multiset<int>> student_scores;
  string name;
  int score;
  while (*ifs >> name >> score) {
    student_scores[name].emplace(score);
  }

  string top_student("no such student");
  int current_top_three_scores_sum = 0;
  for (const auto& scores : student_scores) {
    if (scores.second.size() >= 3) {
      int current_scores_sum = GetTopThreeScoresSum(scores.second);
      if (current_scores_sum > current_top_three_scores_sum) {
        current_top_three_scores_sum = current_scores_sum;
        top_student = scores.first;
      }
    }
  }
  return top_student;
}

// Returns the sum of top three scores.
int GetTopThreeScoresSum(const multiset<int>& scores) {
  auto it = scores.rbegin();
  advance(it, 3);
  return accumulate(scores.crbegin(), it, 0);
}
// @exclude

string rand_string(int len) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis('a', 'z');
  string ret;
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 10000);
    n = dis(gen);
  }
  ofstream ofs("scores.txt");
  for (int i = 0; i < n; ++i) {
    uniform_int_distribution<int> test_num_dis(0, 20);
    int test_num = test_num_dis(gen);
    uniform_int_distribution<int> len_dis(5, 10);
    string name = rand_string(len_dis(gen));
    while (test_num--) {
      uniform_int_distribution<int> score_dis(0, 100);
      ofs << name << " " << score_dis(gen) << endl;
    }
  }
  ofs.close();
  ifstream ifs("scores.txt");
  string name = FindStudentWithHighestBestOfThreeScores(&ifs);
  cout << "top student is " << name << endl;
  // Remove file after the execution.
  // remove("score.txt");
  return 0;
}
