// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

// @include
vector<vector<string>> FindAnagrams(const vector<string>& dictionary) {
  unordered_map<string, vector<string>> sorted_string_to_anagrams;
  for (const string& s : dictionary) {
    // Sorts the string, uses it as a key, and then appends
    // the original string as another value into hash table.
    string sorted_str(s);
    sort(sorted_str.begin(), sorted_str.end());
    sorted_string_to_anagrams[sorted_str].emplace_back(s);
  }

  vector<vector<string>> anagram_groups;
  for (const auto& p : sorted_string_to_anagrams) {
    if (p.second.size() >= 2) {  // Found anagrams.
      anagram_groups.emplace_back(p.second);
    }
  }
  return anagram_groups;
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret.push_back(static_cast<char>(dis(gen)));
  }
  return ret;
}

void SmallTest() {
  vector<string> D = {"debit card",
                      "bad credit",
                      "the morse code",
                      "here come dots",
                      "the eyes",
                      "they see",
                      "THL"};
  auto result = FindAnagrams(D);
  // 3 nontrivial groups:
  // {"debit card", "bad credit"},
  // {"the morse code", "here come dots"}
  // {"the eyes",   "they see"
  // Since the string "THL" has no anagrams in D, the result
  // contains 3 entries
  assert(result.size() == 3);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  vector<string> dictionary;
  uniform_int_distribution<int> n_dis(0, 99999);
  int n = n_dis(gen);
  unordered_set<string> table;
  for (int i = 0; i < n; ++i) {
    uniform_int_distribution<int> dis(1, 12);
    table.emplace(RandString(dis(gen)));
  }
  for (const string& s : table) {
    dictionary.emplace_back(s);
  }
  FindAnagrams(dictionary);
  return 0;
}
