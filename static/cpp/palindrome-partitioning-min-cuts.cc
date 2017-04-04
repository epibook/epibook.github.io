// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <numeric>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::iota;
using std::min;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

// @include
int MinCuts(const string& s) {
  vector<deque<bool>> is_palindrome(s.size(), deque<bool>(s.size(), false));
  vector<int> T(s.size() + 1);
  iota(T.rbegin(), T.rend(), -1);
  for (int i = s.size() - 1; i >= 0; --i) {
    for (int j = i; j < s.size(); ++j) {
      if (s[i] == s[j] && (j - i < 2 || is_palindrome[i + 1][j - 1])) {
        is_palindrome[i][j] = true;
        T[i] = min(T[i], 1 + T[j + 1]);
      }
    }
  }
  return T[0];
}
// @exclude

string RandString(size_t len) {
  default_random_engine gen((random_device())());
  string ret;
  uniform_int_distribution<char> dis('a', 'z');
  while (len) {
    --len;
    ret += dis(gen);
  }
  return ret;
}

void small_test() {
  assert(1 == MinCuts("aab"));
  assert(0 == MinCuts("bb"));
  assert(3 == MinCuts("cabababcbc"));
  assert(42 == MinCuts("eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj"));
}

int main(int argc, char** argv) {
  small_test();
  if (argc == 2) {
    string s = argv[1];
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<size_t> dis_len(0, 10);
    string s = RandString(dis_len(gen));
    cout << "times = " << MinCuts(s) << endl;
  }
  return 0;
}
