// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <bitset>
#include <cassert>
#include <fstream>
#include <iostream>
#include <iterator>
#include <map>
#include <queue>
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
using std::greater;
using std::ifstream;
using std::invalid_argument;
using std::ios;
using std::map;
using std::multiset;
using std::ofstream;
using std::priority_queue;
using std::random_device;
using std::stoi;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

int GetTopThreeScoresSum(priority_queue<int, vector<int>, greater<>>);

// @include
string FindStudentWithHighestBestOfThreeScores(ifstream* ifs) {
  // Use a multiset to handle duplicated test scores.
  unordered_map<string, priority_queue<int, vector<int>, greater<>>>
      student_scores;
  string name;
  int score;
  while (*ifs >> name >> score) {
    student_scores[name].emplace(score);
    if (student_scores[name].size() > 3) {
      student_scores[name].pop();  // Only keep the top 3 scores.
    }
  }

  string top_student = "no such student";
  int current_top_three_scores_sum = 0;
  for (const auto& scores : student_scores) {
    if (scores.second.size() == 3) {
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
int GetTopThreeScoresSum(priority_queue<int, vector<int>, greater<>> scores) {
  int sum = 0;
  while (!scores.empty()) {
    sum += scores.top();
    scores.pop();
  }
  return sum;
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis('a', 'z');
  string ret;
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

void SimpleTest() {
  ofstream ofs("scores.txt");
  ofs << "adnan 100";
  ofs << "amit 99";
  ofs << "adnan 98";
  ofs << "thl 90";
  ofs << "adnan 10";
  ofs << "amit 100";
  ofs << "thl 99";
  ofs << "thl 95";
  ofs << "adnan 95";
  ofs.close();
  ifstream ifs("scores.txt");
  string result = FindStudentWithHighestBestOfThreeScores(&ifs);
  cout << "result = " << result << endl;
  assert(result == "adnan");
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 10000);
    n = dis(gen);
  }
  ofstream ofs("/tmp/scores.txt");
  for (int i = 0; i < n; ++i) {
    uniform_int_distribution<int> test_num_dis(0, 20);
    int test_num = test_num_dis(gen);
    uniform_int_distribution<int> len_dis(5, 10);
    string name = RandString(len_dis(gen));
    while (test_num--) {
      uniform_int_distribution<int> score_dis(0, 100);
      ofs << name << " " << score_dis(gen) << endl;
    }
  }
  ofs.close();
  ifstream ifs("/tmp/scores.txt");
  string name = FindStudentWithHighestBestOfThreeScores(&ifs);
  cout << "top student is " << name << endl;
  // Remove file after the execution.
  // remove("score.txt");
  return 0;
}
