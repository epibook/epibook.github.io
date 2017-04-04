// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::random_device;
using std::string;
using std::stoi;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

// @include
int substring_len_without_repeating_chars(const string& s) {
  // Record the last occurrence of each char.
  unordered_map<char, size_t> last_occurrence;
  size_t starting_idx = 0, ans = 0;
  for (size_t i = 0; i < s.size(); ++i) {
    auto it(last_occurrence.find(s[i]));
    if (it == last_occurrence.cend()) {
      last_occurrence.emplace_hint(it, s[i], i);
    } else {  // s[i] appeared before. Check its validity.
      if (it->second >= starting_idx) {
        ans = max(ans, i - starting_idx);
        starting_idx = it->second + 1;
      }
      it->second = i;
    }
  }
  ans = max(ans, s.size() - starting_idx);
  return ans;
}
// @exclude

string rand_string(size_t len) {
  string ret;
  default_random_engine gen((random_device())());
  uniform_int_distribution<char> dis('a', 'z');
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

// O(n^2) checking solution.
void check_ans(const string& s, int ans) {
  size_t len = 0;
  for (size_t i = 0; i < s.size(); ++i) {
    unordered_set<char> table;
    size_t j;
    for (j = i; j < s.size(); ++j) {
      auto it(table.find(s[j]));
      if (it == table.cend()) {
        table.emplace_hint(it, s[j]);
      } else {
        break;
      }
    }
    len = max(len, j - i);
  }
  assert(ans == len);
}

int main(int argc, char** argv) {
  if (argc == 2) {
    int ans = substring_len_without_repeating_chars(argv[1]);
    check_ans(argv[1], ans);
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<size_t> dis(0, 100);
    for (int times = 0; times < 1000; ++times) {
      string s = rand_string(dis(gen));
      cout << "s = " << s << endl;
      int ans = substring_len_without_repeating_chars(s);
      check_ans(s, ans);
    }
  }
  return 0;
}
