// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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

bool MatchAllWordsInDict(const string&, const unordered_map<string, int>&,
                         int, int, int);

// @include
vector<int> FindAllSubstrings(const string& s, const vector<string>& words) {
  unordered_map<string, int> word_to_freq;
  for (const string& word : words) {
    ++word_to_freq[word];
  }

  int unit_size = words.front().size();
  vector<int> result;
  for (int i = 0; i + unit_size * words.size() <= s.size(); ++i) {
    if (MatchAllWordsInDict(s, word_to_freq, i, words.size(), unit_size)) {
      result.emplace_back(i);
    }
  }
  return result;
}

bool MatchAllWordsInDict(const string& s,
                         const unordered_map<string, int>& word_to_freq,
                         int start, int num_words, int unit_size) {
  unordered_map<string, int> curr_string_to_freq;
  for (int i = 0; i < num_words; ++i) {
    string curr_word = s.substr(start + i * unit_size, unit_size);
    auto iter = word_to_freq.find(curr_word);
    if (iter == word_to_freq.end()) {
      return false;
    }
    ++curr_string_to_freq[curr_word];
    if (curr_string_to_freq[curr_word] > iter->second) {
      // curr_word occurs too many times for a match to be possible.
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
