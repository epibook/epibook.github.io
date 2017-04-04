// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <random>
#include <string>

using std::default_random_engine;
using std::random_device;
using std::string;
using std::uniform_int_distribution;

// @include
bool IsPalindromic(const string& s) {
  for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
    if (s[i] != s[j]) {
      return false;
    }
  }
  return true;
}
// @exclude

bool CheckAnswer(const string& s) {
  string copy(s);
  reverse(copy.begin(), copy.end());
  return s == copy;
}

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<char> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 10000; ++times) {
    uniform_int_distribution<int> dis(1, 1000);
    string s = RandString(dis(gen));
    assert(IsPalindromic(s) == CheckAnswer(s));
  }
  return 0;
}
