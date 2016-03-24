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
string zigzag_conversion(const string& s, int row) {
  if (row == 1) {
    return s;
  }

  string ret;
  int idx = 0;
  // Processes the first row.
  while (idx < s.size()) {
    ret += s[idx];
    idx += (row * 2) - 2;
  }
  // Processes the second row to the row before the last row.
  int l_gap = (row * 2) - 4, r_gap = 2;
  for (int i = 1; i < row - 1; ++i) {
    idx = i;
    while (idx < s.size()) {
      ret += s[idx];
      idx += l_gap;
      if (idx >= s.size()) {
        break;
      }
      ret += s[idx];
      idx += r_gap;
    }
    l_gap -= 2, r_gap += 2;
  }
  // Processes the last row.
  idx = row - 1;
  while (idx < s.size()) {
    ret += s[idx];
    idx += (row * 2) - 2;
  }

  return ret;
}
// @exclude

string rand_string(size_t len) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<char> dis('A', 'Z');
  string ret;
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

void small_test() {
  auto result = zigzag_conversion("Hello World", 3);
  assert(!result.compare("Horel ollWd"));
}

int main(int argc, char** argv) {
  small_test();
  default_random_engine gen((random_device())());
  string s;
  int n;
  if (argc == 3) {
    s = argv[1];
    n = stoi(argv[2]);
  } else {
    uniform_int_distribution<int> dis(1, 10);
    n = dis(gen);
    uniform_int_distribution<size_t> dis_s(n, 100);
    s = rand_string(dis_s(gen));
  }
  cout << zigzag_conversion(s, n) << endl;
  return 0;
}
