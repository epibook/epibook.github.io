// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

bool MatchAllWordsInDict(const string& s,
                         const unordered_map<string, int>& dict, size_t start,
                         size_t num_words, size_t unit_size);

// @include
vector<int> FindAllSubstrings(const string& s, const vector<string>& words) {
  unordered_map<string, int> dict;
  for (const string& word : words) {
    ++dict[word];
  }

  auto unit_size = words.front().size();
  vector<int> result;
  for (size_t i = 0; i + unit_size * words.size() <= s.size(); ++i) {
    if (MatchAllWordsInDict(s, dict, i, words.size(), unit_size)) {
      result.emplace_back(i);
    }
  }
  return result;
}

bool MatchAllWordsInDict(const string& s,
                         const unordered_map<string, int>& dict, size_t start,
                         size_t num_words, size_t unit_size) {
  unordered_map<string, int> curr_dict;
  for (size_t i = 0; i < num_words; ++i) {
    string curr_word = s.substr(start + i * unit_size, unit_size);
    auto iter = dict.find(curr_word);
    if (iter == dict.end()) {
      return false;
    }
    ++curr_dict[curr_word];
    if (curr_dict[curr_word] > iter->second) {
      return false;
    }
  }
  return true;
}
// @exclude

void SmallTest() {
  string s = "barfoothefoobarman";
  auto result = FindAllSubstrings(s, {"foo", "bar"});
  assert(result.size() == 2 && result[0] == 0 && result[1] == 9);
  s = "dcacdabcd";
  result = FindAllSubstrings(s, {"cd", "ab"});
  assert(result.size() == 2 && result[0] == 3 && result[1] == 5);
}

int main(int argc, char** argv) {
  SmallTest();
  return 0;
}
