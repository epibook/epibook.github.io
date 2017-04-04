// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <set>
#include <sstream>
#include <string>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::set;
using std::string;
using std::stringstream;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

void generate_results(const string& s, const unordered_set<string>& dict,
                      size_t idx, const deque<bool>& table,
                      const vector<size_t>& lengths, vector<string>* result,
                      vector<string>* results);
string join_with_space(const vector<string>& str);

// @include
vector<string> word_breaking(const string& s,
                             const unordered_set<string>& dict) {
  if (s.empty()) {
    return {};
  }

  // Gets all possible candidate lengths in sorted manner.
  set<size_t> temp_lengths;
  for (const string& x : dict) {
    temp_lengths.emplace(x.size());
  }
  vector<size_t> lengths(temp_lengths.cbegin(), temp_lengths.cend());

  // Builds DP table which records possible breaking points.
  deque<bool> table(s.size() + 1, false);
  table[0] = true;
  for (size_t i = 0; i < s.size(); ++i) {
    if (table[i]) {
      for (size_t x : lengths) {
        if (i + x > s.size()) {
          break;
        }
        if (dict.find(s.substr(i, x)) != dict.end()) {
          table[i + x] = true;
        }
      }
    }
  }

  vector<string> results;
  if (table.back()) {  // has answer.
    vector<string> result;
    generate_results(s, dict, 0, table, lengths, &result, &results);
  }
  return results;
}

void generate_results(const string& s, const unordered_set<string>& dict,
                      size_t idx, const deque<bool>& table,
                      const vector<size_t>& lengths, vector<string>* result,
                      vector<string>* results) {
  if (idx == s.size()) {
    results->emplace_back(join_with_space(*result));
    return;
  }

  // Tries all possible combinations of strings in dict and stores the result
  // into results.
  for (size_t x : lengths) {
    if (idx + x > s.size()) {
      break;
    }
    string target = s.substr(idx, x);
    if (table[idx + x] && dict.find(target) != dict.end()) {
      result->emplace_back(target);
      generate_results(s, dict, idx + x, table, lengths, result, results);
      result->pop_back();
    }
  }
}

// Helper function to combine vector of string by using space as delimiter.
string join_with_space(const vector<string>& str) {
  stringstream ss;
  for (size_t i = 0; i < str.size(); ++i) {
    if (i != 0) {
      ss << " ";
    }
    ss << str[i];
  }
  return ss.str();
}
// @exclude

// Verify the strings in ans can be assembled into s.
void check_ans(const string& s, const vector<string>& ans) {
  cout << s << endl;
  for (const string& x : ans) {
    string temp = x;
    temp.erase(remove_if(temp.begin(), temp.end(), isspace), temp.end());
    assert(!s.compare(temp));
  }
}

string rand_string(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += static_cast<char>(dis(gen));
  }
  return ret;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    unordered_set<string> dictionary;
    string target;
    if (argc >= 3) {
      target = argv[1];
      for (int i = 3; i < argc; ++i) {
        dictionary.emplace(argv[i]);
      }
    } else if (argc == 2) {
      target = argv[1];
      uniform_int_distribution<int> dis(1, 10000);
      int n = dis(gen);
      while (n--) {
        uniform_int_distribution<int> dis2(1, 15);
        dictionary.emplace(rand_string(dis2(gen)));
      }
    } else {
      uniform_int_distribution<int> dis(1, 50);
      target = rand_string(dis(gen));
      uniform_int_distribution<int> n_dis(1, 10000);
      int n = n_dis(gen);
      while (n--) {
        uniform_int_distribution<int> dis2(1, 15);
        dictionary.emplace(rand_string(dis2(gen)));
      }
    }
    vector<string> ans(word_breaking(target, dictionary));
    check_ans(target, ans);
    if (argc == 3) {
      break;
    }
  }
  return 0;
}
