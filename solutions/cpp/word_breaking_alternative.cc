// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

string rand_string(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

// @include
bool word_breaking_helper(const string& s, const unordered_set<string>& dict,
                          vector<string>* ret) {
  if (s.empty()) {
    return true;
  }

  for (int i = 0; i < s.size(); ++i) {
    string target = s.substr(0, i + 1);
    // Check target is a valid word in dict.
    if (dict.find(target) != dict.cend()) {
      // If the remaining string of s can be assembled, we record the answer
      // in ret and return true.
      if (word_breaking_helper(s.substr(i + 1), dict, ret)) {
        ret->emplace_back(target);
        return true;
      }
    }
  }
  // Return false if s cannot be assembled by words in dict.
  return false;
}

vector<string> word_breaking(const string& s,
                             const unordered_set<string>& dict) {
  vector<string> ret;
  if (word_breaking_helper(s, dict, &ret)) {
    reverse(ret.begin(), ret.end());
  }
  return ret;
}
// @exclude

// Verify the strings in ans can be assembled into s.
void check_ans(const string& s, const vector<string>& ans) {
  string temp;
  cout << s << endl;
  for (int i = 0; i < ans.size(); ++i) {
    cout << ans[i] << ' ';
    temp += ans[i];
  }
  cout << endl;
  assert(!ans.size() || !s.compare(temp));
}

void small_test() {
  string s = "bedbathbeyond";
  unordered_set<string> dic = {"bed", "bath", "and", "beyond", "bat", "hand"};
  auto result = word_breaking(s, dic);
  check_ans(s, result);
  s = "aaa";
  result = word_breaking(s, dic);
  check_ans(s, result);
}

int main(int argc, char* argv[]) {
  small_test();
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
        uniform_int_distribution<int> dis(1, 15);
        dictionary.emplace(rand_string(dis(gen)));
      }
    } else {
      uniform_int_distribution<int> dis(1, 50);
      target = rand_string(dis(gen));
      uniform_int_distribution<int> n_dis(1, 10000);
      int n = n_dis(gen);
      while (n--) {
        uniform_int_distribution<int> dis(1, 15);
        dictionary.emplace(rand_string(dis(gen)));
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
