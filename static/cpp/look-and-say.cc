// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <sstream>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::string;
using std::stringstream;
using std::to_string;
using std::uniform_int_distribution;

string NextNumber(const string& s);

// @include
string LookAndSay(int n) {
  string s = "1";
  for (int i = 1; i < n; ++i) {
    s = NextNumber(s);
  }
  return s;
}

string NextNumber(const string& s) {
  string result;
  for (int i = 0; i < s.size(); ++i) {
    int count = 1;
    while (i + 1 < s.size() && s[i] == s[i + 1]) {
      ++i, ++count;
    }
    result += to_string(count) + s[i];
  }
  return result;
}
// @exclude

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 50);
    n = dis(gen);
  }
  assert(!LookAndSay(1).compare("1"));
  assert(!LookAndSay(2).compare("11"));
  assert(!LookAndSay(3).compare("21"));
  assert(!LookAndSay(4).compare("1211"));
  assert(!LookAndSay(5).compare("111221"));
  assert(!LookAndSay(6).compare("312211"));
  assert(!LookAndSay(7).compare("13112221"));
  assert(!LookAndSay(8).compare("1113213211"));

  cout << "n = " << n << endl;
  cout << LookAndSay(n) << endl;
  return 0;
}
