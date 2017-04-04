// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <string>
#include <unordered_map>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::numeric_limits;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret = "";
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

// @include
int FindNearestRepetition(const vector<string>& paragraph) {
  unordered_map<string, int> word_to_latest_index;
  int nearest_repeated_distance = numeric_limits<int>::max();
  for (int i = 0; i < paragraph.size(); ++i) {
    auto latest_equal_word = word_to_latest_index.find(paragraph[i]);
    if (latest_equal_word != word_to_latest_index.end()) {
      nearest_repeated_distance =
          min(nearest_repeated_distance, i - latest_equal_word->second);
    }
    word_to_latest_index[paragraph[i]] = i;
  }
  return nearest_repeated_distance;
}
// @exclude

// O(n^2) checking
int CheckAnswer(const vector<string>& s) {
  int nearest_repeated_distance = numeric_limits<int>::max();
  for (int i = 0; i < s.size(); ++i) {
    for (int j = i + 1; j < s.size(); ++j) {
      if (s[i] == s[j]) {
        nearest_repeated_distance = min(nearest_repeated_distance, j - i);
      }
    }
  }
  return nearest_repeated_distance;
}

int main(int argc, char* argv[]) {
  vector<string> A = {"foo",    "bar",    "widget", "foo",
                      "widget", "widget", "adnan"};
  assert(CheckAnswer(A) == FindNearestRepetition(A));
  A = {"foo", "bar", "widget", "foo", "xyz", "widget", "bar", "adnan"};
  assert(CheckAnswer(A) == FindNearestRepetition(A));
  A = {"foo", "bar", "widget", "adnan"};
  assert(CheckAnswer(A) == FindNearestRepetition(A));
  A = {};
  assert(CheckAnswer(A) == FindNearestRepetition(A));
  A = {"foo", "foo", "foo"};
  assert(CheckAnswer(A) == FindNearestRepetition(A));
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<string> s;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 10);
      s.emplace_back(RandString(dis(gen)));
    }
    assert(CheckAnswer(s) == FindNearestRepetition(s));
  }
  return 0;
}
