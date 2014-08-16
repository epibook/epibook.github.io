// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::string;
using std::uniform_int_distribution;

// @include
string SnakeString(const string& s) {
  string ret;
  // Outputs the first row, i.e., s[1], s[5], s[9], ...
  size_t idx = 1;
  while (idx < s.size()) {
    ret += s[idx];
    idx += 4;
  }
  // Outputs the second row, i.e., s[0], s[2], s[4], ...
  idx = 0;
  while (idx < s.size()) {
    ret += s[idx];
    idx += 2;
  }
  // Outputs the third row, i.e., s[3], s[7], s[11], ...
  idx = 3;
  while (idx < s.size()) {
    ret += s[idx];
    idx += 4;
  }

  return ret;
}
// @exclude

string RandString(size_t len) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<char> dis('A', 'Z');
  string ret;
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

void SmallTest() {
  assert(!SnakeString("Hello World!").compare("e lHloWrdlo!"));
}

int main(int argc, char** argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  string s;
  if (argc == 2) {
    s = argv[1];
  } else {
    uniform_int_distribution<size_t> dis_s(1, 100);
    s = RandString(dis_s(gen));
  }
  cout << SnakeString(s) << endl;
  return 0;
}
